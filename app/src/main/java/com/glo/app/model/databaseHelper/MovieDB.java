package com.glo.app.model.databaseHelper;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.glo.app.model.entities.MovieInfo;

@Database(entities = {MovieInfo.class}, version = 1)
public abstract class MovieDB extends RoomDatabase {
    private static MovieDB movieDBInstance;

    public static synchronized MovieDB getInstance(Context context) {
        if (movieDBInstance == null) {
            movieDBInstance = Room.databaseBuilder(context.getApplicationContext(), MovieDB.class, "movie_wishlist_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return movieDBInstance;
    }

    public abstract MovieDao getMovieDao();
}
