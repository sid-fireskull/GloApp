package com.glo.app.di;

import com.glo.app.modules.views.MainActivity;
import com.glo.app.modules.views.MovieDetailActivity;
import com.glo.app.modules.views.WishlistActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, AppRepositoryModule.class})
public interface MovieComponent {

    void injectMainActivity(MainActivity mainActivity);

    void injectMovieDetailsActivity(MovieDetailActivity movieDetailActivity);

    void injectWishlistActivity(WishlistActivity wishlistActivity);

}
