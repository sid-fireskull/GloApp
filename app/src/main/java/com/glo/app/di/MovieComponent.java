package com.glo.app.di;

import com.glo.app.modules.views.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, AppRepositoryModule.class})
public interface MovieComponent {

    void inject(MainActivity mainActivity);

}
