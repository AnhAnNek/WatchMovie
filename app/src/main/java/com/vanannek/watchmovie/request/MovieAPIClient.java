package com.vanannek.watchmovie.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vanannek.watchmovie.AppExecutors;
import com.vanannek.watchmovie.models.Movie;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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

    public void searchMoviesAPI() {
        final Future mHandler = AppExecutors.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {
                // Retrieve Data from API
            }
        });

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                mHandler.cancel(true);
            }
        }, 5000, TimeUnit.MICROSECONDS);
    }
}
