package com.vanannek.watchmovie.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanannek.watchmovie.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // Widgets
    TextView title, releaseDate, duration;
    ImageView imageView;
    RatingBar ratingBar;

    // Click listener
    OnMovieListener onMovieListener;

    public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);

        this.onMovieListener = onMovieListener;

        title = itemView.findViewById(R.id.movie_title);
        releaseDate = itemView.findViewById(R.id.movie_category);
        duration = itemView.findViewById(R.id.movie_duration);

        imageView = itemView.findViewById(R.id.movie_img);
        ratingBar = itemView.findViewById(R.id.rating_bar);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onMovieListener.onMovieClick(getAbsoluteAdapterPosition());
    }
}
