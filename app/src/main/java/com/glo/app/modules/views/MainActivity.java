package com.glo.app.modules.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
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
import com.glo.app.databinding.ActivityMainBinding;
import com.glo.app.model.entities.MovieInfo;
import com.glo.app.modules.adapters.MovieDataAdapter;
import com.glo.app.modules.shared.MovieRecyclerviewClickListener;
import com.glo.app.viewmodel.MovieListViewModel;
import com.glo.app.viewmodel.MovieListViewModelFactory;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MovieRecyclerviewClickListener {
    @Inject
    public MovieListViewModelFactory movieListViewModelFactory;
    private ActivityMainBinding activityMainBinding;
    private RecyclerView recyclerView;
    private MovieDataAdapter movieDataAdapter;
    private MovieListViewModel mainActivityViewModel;
    private PagedList<MovieInfo> moviePageList;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        App.getApp().getMovieComponent().injectMainActivity(this);
        mainActivityViewModel = new ViewModelProvider(this, movieListViewModelFactory).get(MovieListViewModel.class);
        initializeToolbar();
        //    mainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        mainActivityViewModel.getMoviesPageList().observe(this, new Observer<PagedList<MovieInfo>>() {
            @Override
            public void onChanged(PagedList<MovieInfo> moviesLiveData) {
                moviePageList = moviesLiveData;
                initializeRecyclerview();
            }
        });
    }

    private void initializeRecyclerview() {
        recyclerView = activityMainBinding.movieRV;
        movieDataAdapter = new MovieDataAdapter(this);
        movieDataAdapter.submitList(moviePageList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(movieDataAdapter);
        movieDataAdapter.setClickListener(this);
    }

    private void initializeToolbar() {
        mToolbar = activityMainBinding.myToolbar.myCustomiseToolbar;
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_wishlist) {
            Intent intent = new Intent(MainActivity.this, WishlistActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTap(MovieInfo movie) {
        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
