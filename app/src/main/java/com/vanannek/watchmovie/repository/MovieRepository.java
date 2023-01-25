package com.vanannek.watchmovie.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vanannek.watchmovie.models.Movie;

import java.util.List;

public class MovieRepository {

    // This class is acting as repositories

    // LiveData
    private MutableLiveData<List<Movie>> mMovies = new MutableLiveData<>();

    private static MovieRepository instance;

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
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }
}