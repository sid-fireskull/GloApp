package com.glo.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.glo.app.model.repositories.MovieRepository;

import javax.inject.Inject;

public class MovieDetailsActivityViewModelFactory implements ViewModelProvider.Factory {
    private final MovieRepository movieRepository;

    @Inject
    public MovieDetailsActivityViewModelFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new MovieDetailActivityViewModel(movieRepository);
    }
}
