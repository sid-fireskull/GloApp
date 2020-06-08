package com.glo.app.di;

import com.glo.app.model.services.MovieDataService;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppRetrofitMovieDataServiceModule {

    @Singleton
    @Provides
    public MovieDataService getMovieDataService(Retrofit retrofit) {
        return retrofit.create(MovieDataService.class);
    }
}
