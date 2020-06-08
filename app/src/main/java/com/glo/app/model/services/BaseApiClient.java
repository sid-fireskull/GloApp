package com.glo.app.model.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApiClient {
    private static Retrofit retrofitInstance;
    private static String BASE_URL = "https://api.themoviedb.org/3/";

    public static MovieDataService getRetrofitInstance() {
        if (retrofitInstance == null) {

            retrofitInstance = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofitInstance.create(MovieDataService.class);
    }
}
