package com.glo.app.model.services;

import com.glo.app.model.entities.DBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("movie/popular")
    Call<DBResponse> getPopularMovies(@Query("api_key")String apiKey);

    @GET("movie/popular")
    Call<DBResponse> getPopularMoviesByPaging(@Query("api_key")String apiKey, @Query("page")int pageNo);

}
