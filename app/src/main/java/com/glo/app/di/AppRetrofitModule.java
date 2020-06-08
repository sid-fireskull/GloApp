package com.glo.app.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppRetrofitModule {
    private final static String BASE_URL = "https://api.themoviedb.org/3/";

    @Provides
    @Singleton
    public static Retrofit getRetrofitInstance() {
        return new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
