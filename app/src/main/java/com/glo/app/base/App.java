package com.glo.app.base;

import android.app.Application;

import com.glo.app.di.AppDataServiceComponent;
import com.glo.app.di.AppModule;
import com.glo.app.di.DaggerAppDataServiceComponent;
import com.glo.app.di.DaggerMovieComponent;
import com.glo.app.di.MovieComponent;

public class App extends Application {
    private static App app;
    private AppDataServiceComponent dataServiceComponent;
    private MovieComponent movieComponent;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        movieComponent = DaggerMovieComponent.builder()
                .appModule(new AppModule(app))
                .build();
        dataServiceComponent = DaggerAppDataServiceComponent.builder().build();
    }

    public AppDataServiceComponent getDataServiceComponent() {
        return dataServiceComponent;
    }


    public MovieComponent getMovieComponent() {
        return movieComponent;
    }
}
