package com.glo.app.modules.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.glo.app.R;
import com.glo.app.base.App;
import com.glo.app.databinding.ActivityMovieDetailBinding;
import com.glo.app.model.entities.MovieInfo;
import com.glo.app.viewmodel.MainActivityViewModel;
import com.glo.app.viewmodel.MovieDetailActivityViewModel;
import com.glo.app.viewmodel.MovieDetailsActivityViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

public class MovieDetailActivity extends AppCompatActivity {
    private ActivityMovieDetailBinding movieDetailBinding;
    @Inject
    public MovieDetailsActivityViewModelFactory movieDetailsActivityViewModelFactory;
    private MovieDetailActivityViewModel movieDetailActivityViewModel;
    private FloatingActionButton addWishlistFab;
    private Toolbar mToolbar;
    private MovieInfo movie;
    private boolean inWishList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        App.getApp().getMovieComponent().injectMovieDetailsActivity(this);
        movieDetailActivityViewModel = new ViewModelProvider(this, movieDetailsActivityViewModelFactory).get(MovieDetailActivityViewModel.class);
        App.getApp().getMovieComponent().injectMovieDetailsActivity(this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            movie = getIntent().getParcelableExtra("movie");
            Log.d("Movie", movie.getTitle());
            movieDetailBinding.setMovie(movie);
        }
        initToolbar();
        initWishlistFab();
        movieDetailActivityViewModel.isExistMovieLocally(movie.getId()).observe(this, new Observer<MovieInfo>() {
            @Override
            public void onChanged(MovieInfo movieInfo) {
                if (movieInfo != null) {
                    if (movieInfo.getId() == movie.getId()) {
                        addWishlistFab.setImageDrawable(getDrawable(R.drawable.ic_filled_fav));
                        inWishList = true;
                    }
                } else {
                    addWishlistFab.setImageDrawable(getDrawable(R.drawable.ic_outline_fav));
                    inWishList = false;
                }
            }
        });
    }

    private void initToolbar() {
        mToolbar = movieDetailBinding.mToolbar;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initWishlistFab() {
        addWishlistFab = movieDetailBinding.saveWishlistFab;
        addWishlistFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movie != null) {
                    if (!inWishList) {
                        movieDetailActivityViewModel.saveMovieAsWishList(movie);
                        Toast.makeText(MovieDetailActivity.this, "Movie Added To Wish List", Toast.LENGTH_SHORT).show();
                    } else {
                        movieDetailActivityViewModel.removeMovieAsWishList(movie);
                        Toast.makeText(MovieDetailActivity.this, "Movie Removed From Wish List", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}