package com.vanannek.watchmovie.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vanannek.watchmovie.models.Movie;

import java.util.List;

// This class is for getting multiple movies (Movies list) - popular movies
public class MovieSearchResponse {
    @SerializedName("total_results")
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose()
    private List<Movie> movies;

    public int getTotal_count() {
        return total_count;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_count=" + total_count +
                ", movies=" + movies +
                '}';
    }
}
