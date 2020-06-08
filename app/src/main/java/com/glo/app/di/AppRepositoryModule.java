package com.glo.app.di;

import android.app.Application;
import com.glo.app.model.repositories.MovieRepository;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppRepositoryModule {

    @Singleton
    @Provides
    public MovieRepository getMovieRepository(Application application) {
        return new MovieRepository(application);
    }
}
