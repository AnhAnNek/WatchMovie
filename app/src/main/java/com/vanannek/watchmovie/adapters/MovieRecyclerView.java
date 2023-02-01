package com.vanannek.watchmovie.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vanannek.watchmovie.R;
import com.vanannek.watchmovie.models.Movie;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> mMovies;
    private OnMovieListener onMovieListener;

    public MovieRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.container_movie_item,
                parent, false);
        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder) holder).title.setText(mMovies.get(position).getTitle());
        ((MovieViewHolder) holder).releaseDate.setText(mMovies.get(position).getReleaseDate());
        ((MovieViewHolder) holder).duration.setText(mMovies.get(position).getOriginalLanguage());

        // vote average is over 10, and our rating bar is over 5 stars: diving by 2
        ((MovieViewHolder) holder).ratingBar.setRating(mMovies.get(position).getVoteAverage() / 2);

        // ImageView: Using Glide Library
        Glide.with(((MovieViewHolder) holder).imageView.getContext())
                .load("https://image.tmdb.org/t/p/w500/"
                        + mMovies.get(position).getPosterPath())
                .into((((MovieViewHolder) holder).imageView));
    }

    @Override
    public int getItemCount() {
        if (mMovies == null) {
            return 0;
        }
        return mMovies.size();
    }

    public void setMovies(List<Movie> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }
}