package com.vanannek.watchmovie.utils;

import com.vanannek.watchmovie.models.Movie;
import com.vanannek.watchmovie.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    // Search for movies
    // https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovies(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page);

    // Making search with id
    // https://api.themoviedb.org/3/movie/550?api_key=39d1fb64e09c85b37d981f3f9d10131a
    // Remember that movie_id=550 is for Jack Reacher movie
    @GET("/3/movie/{movie_id}?")
    Call<Movie> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String key);
}
