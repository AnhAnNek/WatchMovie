package com.vanannek.watchmovie.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vanannek.watchmovie.models.Movie;

import java.util.List;

public class MovieAPIClient {

    // LiveData
    private MutableLiveData<List<Movie>> mMovies = new MutableLiveData<>();

    private static MovieAPIClient instance;

    public static MovieAPIClient getInstance() {
        if (instance == null) {
            synchronized (MovieAPIClient.class) {
                if (instance == null) {
                    instance = new MovieAPIClient();
                }
            }
        }
        return instance;
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }
}
