package com.glo.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.glo.app.model.repositories.MovieRepository;

import javax.inject.Inject;

public class MovieListViewModelFactory implements ViewModelProvider.Factory {
    private final MovieRepository movieRepository;

    @Inject
    public MovieListViewModelFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieListViewModel(movieRepository);
    }
}
