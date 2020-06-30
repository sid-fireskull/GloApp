package com.glo.app.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import com.glo.app.model.entities.MovieInfo;
import com.glo.app.model.repositories.MovieRepository;


public class MovieListViewModel extends ViewModel  {
    private MovieRepository movieRepository;

    public MovieListViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public LiveData<PagedList<MovieInfo>> getMoviesPageList() {
        return movieRepository.getMoviesPageList();
    }

    public LiveData<PagedList<MovieInfo>> getMoviesPageListFromLocal() {
        return movieRepository.getMoviesFromLocalPageList();
    }

    public void removeMovieFromWishlist(MovieInfo movie)
    {
        movieRepository.deleteMovieInWishlist(movie);
    }

}