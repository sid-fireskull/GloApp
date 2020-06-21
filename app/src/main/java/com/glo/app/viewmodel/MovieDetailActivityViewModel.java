package com.glo.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.glo.app.model.entities.MovieInfo;
import com.glo.app.model.repositories.MovieRepository;

public class MovieDetailActivityViewModel extends ViewModel {
    private MovieInfo movie;
    private MovieRepository movieRepository;
    private LiveData<MovieInfo> movieLiveData;

    public MovieDetailActivityViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void setMovie(MovieInfo movie) {
        this.movie = movie;
    }

    public void saveMovieAsWishList(MovieInfo movie) {
        movieRepository.insertMovieInWishlist(movie);
    }

    public void removeMovieAsWishList(MovieInfo movie) {
        movieRepository.deleteMovieInWishlist(movie);
    }

    public LiveData<MovieInfo> isExistMovieLocally(int id) {
        return movieRepository.getMovieById(id);
    }
}
