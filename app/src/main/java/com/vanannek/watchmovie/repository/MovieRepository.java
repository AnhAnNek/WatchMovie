package com.vanannek.watchmovie.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vanannek.watchmovie.models.Movie;
import com.vanannek.watchmovie.request.MovieAPIClient;

import java.util.List;

public class MovieRepository {

    // This class is acting as repositories

    private static MovieRepository instance;
    private MovieAPIClient movieAPIClient;

    public static MovieRepository getInstance() {
        if (instance == null) {
            synchronized (MovieRepository.class) {
                if (instance == null) {
                    instance = new MovieRepository();
                }
            }
        }
        return instance;
    }

    private MovieRepository() {
        movieAPIClient = MovieAPIClient.getInstance();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieAPIClient.getMovies();
    }
}