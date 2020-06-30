package com.glo.app.modules.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glo.app.R;
import com.glo.app.base.App;
import com.glo.app.databinding.ActivityWishlistBinding;
import com.glo.app.model.entities.MovieInfo;
import com.glo.app.modules.adapters.WishlistDataAdapter;
import com.glo.app.modules.shared.MovieRecyclerviewClickListener;
import com.glo.app.viewmodel.MovieListViewModel;
import com.glo.app.viewmodel.MovieListViewModelFactory;

import javax.inject.Inject;

public class WishlistActivity extends AppCompatActivity implements MovieRecyclerviewClickListener {

    @Inject
    public MovieListViewModelFactory movieListViewModelFactory;
    private ActivityWishlistBinding wishlistBinding;
    private MovieListViewModel movieListViewModel;
    private RecyclerView wishlistRecyclerview;
    private WishlistDataAdapter wishlistDataAdapter;
    private PagedList<MovieInfo> moviePageList;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wishlistBinding = DataBindingUtil.setContentView(this, R.layout.activity_wishlist);
        App.getApp().getMovieComponent().injectWishlistActivity(this);
        movieListViewModel = new ViewModelProvider(this, movieListViewModelFactory).get(MovieListViewModel.class);
        initializeToolbar();
        movieListViewModel.getMoviesPageListFromLocal().observe(this, new Observer<PagedList<MovieInfo>>() {
            @Override
            public void onChanged(PagedList<MovieInfo> moviePages) {
                moviePageList = moviePages;
                initializeRecyclerview();
            }
        });
    }

    private void initializeRecyclerview() {
        wishlistRecyclerview = wishlistBinding.wishlistRV;
        wishlistDataAdapter = new WishlistDataAdapter(this);
        wishlistDataAdapter.submitList(moviePageList);
        wishlistRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        wishlistRecyclerview.setAdapter(wishlistDataAdapter);
        wishlistDataAdapter.setClickListener(this);
    }

    private void initializeToolbar() {
        mToolbar = wishlistBinding.myToolbar.myCustomiseToolbar;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setTitle("My WishList");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onTap(MovieInfo movie) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Movie")
                .setMessage("Are you sure you want to delete this movie?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        movieListViewModel.removeMovieFromWishlist(movie);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }
}