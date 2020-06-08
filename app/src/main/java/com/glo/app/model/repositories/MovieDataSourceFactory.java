package com.glo.app.model.repositories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.glo.app.model.services.MovieDataService;

public class MovieDataSourceFactory extends DataSource.Factory {
    private MovieDataSource movieDataSource;
    private MovieDataService movieDataService;
    private Application application;
    private MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData;

    public MovieDataSourceFactory(MovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
        movieDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(application,movieDataService);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
