package com.vanannek.watchmovie.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vanannek.watchmovie.models.Movie;

// This class is for single movie request
public class MovieResponse {
    // 1 - Finding the Movie Object
    @SerializedName("results")
    @Expose()
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
