package com.glo.app.modules.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
import com.glo.app.viewmodel.MainActivityViewModel;
import com.glo.app.viewmodel.MainActivityViewModelFactory;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private RecyclerView recyclerView;
    private MovieDataAdapter movieDataAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private PagedList<MovieInfo> moviePageList;

    @Inject
    public MainActivityViewModelFactory mainActivityViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        App.getApp().getMovieComponent().inject(this);
        mainActivityViewModel = new ViewModelProvider(this, mainActivityViewModelFactory).get(MainActivityViewModel.class);
        //    mainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

 /*       mainActivityViewModel.getPopularMovies().observe(this, new Observer<List<MovieInfo>>() {
            @Override
            public void onChanged(List<MovieInfo> movies) {
                movieDataAdapter.setMovieList(movies);
            }
        });*/

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
    }
}
