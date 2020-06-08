package com.glo.app.di;

import com.glo.app.model.repositories.MovieRepository;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppRetrofitModule.class, AppRetrofitMovieDataServiceModule.class})
public interface AppDataServiceComponent {
    void injectDataService(MovieRepository movieRepository);
}
