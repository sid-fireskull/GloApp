package com.glo.app.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.glo.app.model.entities.MovieInfo;
import com.glo.app.model.repositories.MovieRepository;
import java.util.List;


public class MainActivityViewModel extends ViewModel  {
    private MovieRepository movieRepository;

    public MainActivityViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MutableLiveData<List<MovieInfo>> getPopularMovies() {
        return movieRepository.getPopularMovies();
    }

    public LiveData<PagedList<MovieInfo>> getMoviesPageList() {
        return movieRepository.getMoviesPageList();
    }


}