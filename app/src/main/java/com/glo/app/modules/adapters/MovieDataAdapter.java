package com.glo.app.modules.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.glo.app.R;
import com.glo.app.databinding.MovieItemBinding;
import com.glo.app.model.entities.MovieInfo;

public class MovieDataAdapter extends PagedListAdapter<MovieInfo, MovieDataAdapter.MovieViewHolder> {
    private Context context;

    public MovieDataAdapter(Context context) {
        super(MovieInfo.diffCallBack);
        this.context = context;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);
        return new MovieViewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieInfo movie = getItem(position);
        Log.d("Movie", "id: " + movie.getId() + ", Movie Title: " + movie.getTitle());
        holder.movieItemBinding.setMovie(movie);
    }


    class MovieViewHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding movieItemBinding;

        public MovieViewHolder(@NonNull MovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
        }
    }
}
