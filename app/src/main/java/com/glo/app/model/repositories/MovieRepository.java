package com.glo.app.model.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.glo.app.R;
import com.glo.app.base.App;
import com.glo.app.di.DaggerMovieComponent;
import com.glo.app.model.databaseHelper.MovieDB;
import com.glo.app.model.databaseHelper.MovieDao;
import com.glo.app.model.entities.DBResponse;
import com.glo.app.model.entities.MovieInfo;
import com.glo.app.model.services.BaseApiClient;
import com.glo.app.model.services.MovieDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private MovieDao movieDao;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private LiveData<PagedList<MovieInfo>> moviesPageList;
    private Application application;
    private List<MovieInfo> movieList = new ArrayList<>();
    private MutableLiveData<List<MovieInfo>> movieLiveData;
    @Inject
    public MovieDataService movieDataService;


    public MovieRepository(Application application) {
        this.application = application;
        App.getApp().getDataServiceComponent().injectDataService(this);
        MovieDB movieDB = MovieDB.getInstance(application);
        movieDao = movieDB.getMovieDao();
    }

    public MutableLiveData<List<MovieInfo>> getPopularMovies() {
        movieDataService = BaseApiClient.getRetrofitInstance();

        Call<DBResponse> responseCall = movieDataService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
        responseCall.enqueue(new Callback<DBResponse>() {
            @Override
            public void onResponse(Call<DBResponse> call, Response<DBResponse> response) {
                if (response.isSuccessful()) {
                    DBResponse dbResponse = response.body();
                    if (dbResponse != null && dbResponse.getResults() != null) {
                        movieList = dbResponse.getResults();
                        movieLiveData.setValue(movieList);
                    }
                }
            }

            @Override
            public void onFailure(Call<DBResponse> call, Throwable t) {
                Log.i("", "An error occurred while connecting to the server");
            }
        });
        return movieLiveData;
    }

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
