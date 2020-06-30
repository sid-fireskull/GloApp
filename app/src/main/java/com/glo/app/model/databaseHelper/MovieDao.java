package com.glo.app.model.databaseHelper;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.glo.app.model.entities.MovieInfo;
import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    public long addMovieInformation(MovieInfo Movie);

    @Update
    public void updateMovieInformation(MovieInfo Movie);

    @Delete
    public void deleteMovieInformation(MovieInfo Movie);

    @Query("SELECT * FROM wishlist")
    public DataSource.Factory<Integer,MovieInfo> getMovieList();

    @Query("SELECT * FROM wishlist WHERE id = :id")
    public LiveData<MovieInfo> getMovieById(Integer id);
}
