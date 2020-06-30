package com.glo.app.model.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.glo.app.base.App;
import com.glo.app.model.databaseHelper.MovieDB;
import com.glo.app.model.databaseHelper.MovieDao;
import com.glo.app.model.entities.MovieInfo;
import com.glo.app.model.services.MovieDataService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;


public class MovieRepository {

    @Inject
    public MovieDataService movieDataService;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private LiveData<PagedList<MovieInfo>> moviesPageList;
    private Application application;
    private MovieDao movieDao;


    public MovieRepository(Application application) {
        this.application = application;
        App.getApp().getDataServiceComponent().injectDataService(this);
        MovieDB movieDB = MovieDB.getInstance(application);
        movieDao = movieDB.getMovieDao();
    }


    // Get data through Api
    public LiveData<PagedList<MovieInfo>> getMoviesPageList() {

        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieDataService, application);
        movieDataSourceLiveData = factory.getMovieDataSourceMutableLiveData();
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(8)
                .setPageSize(12)
                .setPrefetchDistance(4)
                .build();
        Executor executor = Executors.newFixedThreadPool(5);
        moviesPageList = (new LivePagedListBuilder<Integer, MovieInfo>(factory, config)).setFetchExecutor(executor).build();
        return moviesPageList;
    }

    // Get data from local Database
    public LiveData<PagedList<MovieInfo>> getMoviesFromLocalPageList() {
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(8)
                .setPageSize(12)
                .setPrefetchDistance(4)
                .build();
        Executor executor = Executors.newFixedThreadPool(5);
        moviesPageList = (new LivePagedListBuilder<Integer, MovieInfo>(movieDao.getMovieList(), config)).setFetchExecutor(executor).build();
        return moviesPageList;
    }


    // Get Movie By Id
    public LiveData<MovieInfo> getMovieById(int id) {
        return movieDao.getMovieById(id);
    }


    // Add a new Movie to Wish List
    public void insertMovieInWishlist(final MovieInfo movie) {

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                movieDao.addMovieInformation(movie);
            }
        });

    }

    // Delete A new Movie in the Wish List
    public void deleteMovieInWishlist(final MovieInfo movie) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                movieDao.deleteMovieInformation(movie);
            }
        });
    }
}
