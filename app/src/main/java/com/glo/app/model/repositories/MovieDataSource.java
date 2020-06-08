package com.glo.app.model.repositories;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.glo.app.R;
import com.glo.app.model.entities.DBResponse;
import com.glo.app.model.entities.MovieInfo;
import com.glo.app.model.services.BaseApiClient;
import com.glo.app.model.services.MovieDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, MovieInfo> {
    private MovieDataService movieDataService;
    private Application application;

    public MovieDataSource(Application application, MovieDataService movieDataService) {
        this.application = application;
        this.movieDataService = movieDataService;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, MovieInfo> callback) {
        movieDataService = BaseApiClient.getRetrofitInstance();
        Call<DBResponse> movieCall = movieDataService.getPopularMoviesByPaging(application.getApplicationContext().getString(R.string.api_key), 1);
        movieCall.enqueue(new Callback<DBResponse>() {
            @Override
            public void onResponse(Call<DBResponse> call, Response<DBResponse> response) {
                DBResponse dbResponse = response.body();
                if (dbResponse != null && dbResponse.getResults() != null) {
                    List<MovieInfo> movies = dbResponse.getResults();
                    callback.onResult(movies, null, 2);
                }

            }

            @Override
            public void onFailure(Call<DBResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieInfo> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieInfo> callback) {
        movieDataService = BaseApiClient.getRetrofitInstance();
        Call<DBResponse> movieCall = movieDataService.getPopularMoviesByPaging(application.getApplicationContext().getString(R.string.api_key), params.key + 1);
        movieCall.enqueue(new Callback<DBResponse>() {
            @Override
            public void onResponse(Call<DBResponse> call, Response<DBResponse> response) {
                DBResponse dbResponse = response.body();
                if (dbResponse != null && dbResponse.getResults() != null) {
                    List<MovieInfo> movies = dbResponse.getResults();
                    Log.d("Page Number", params.key.toString());
                    callback.onResult(movies, params.key + 1);
                }

            }

            @Override
            public void onFailure(Call<DBResponse> call, Throwable t) {

            }
        });
    }
}
