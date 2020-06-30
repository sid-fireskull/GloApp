package com.glo.app.modules.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.glo.app.R;
import com.glo.app.databinding.MovieItemBinding;
import com.glo.app.model.entities.MovieInfo;
import com.glo.app.modules.shared.MovieRecyclerviewClickListener;

public class WishlistDataAdapter extends PagedListAdapter<MovieInfo, WishlistDataAdapter.WishListViewHolder> {
    private Context context;
    private MovieRecyclerviewClickListener clickListener;

    public WishlistDataAdapter(Context context) {
        super(MovieInfo.diffCallBack);
        this.context = context;
    }

    public void setClickListener(MovieRecyclerviewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public WishlistDataAdapter.WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);
        return new WishListViewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListViewHolder holder, int position) {
        MovieInfo movie = getItem(position);
        if (movie != null) {
            holder.movieItemBinding.setMovie(movie);
        }
    }


    class WishListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private MovieItemBinding movieItemBinding;

        public WishListViewHolder(@NonNull MovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                MovieInfo movie = getItem(getAdapterPosition());
                Log.d("ClickEvent", movie.getTitle());
                clickListener.onTap(movie);
            }
        }
    }
}