package com.vanannek.watchmovie.request;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vanannek.watchmovie.AppExecutors;
import com.vanannek.watchmovie.MovieListActivity;
import com.vanannek.watchmovie.models.Movie;
import com.vanannek.watchmovie.response.MovieSearchResponse;
import com.vanannek.watchmovie.utils.Credentials;
import com.vanannek.watchmovie.utils.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieAPIClient {

    private static String TAG = "MovieAPIClient";

    // LiveData
    private MutableLiveData<List<Movie>> mMovies = new MutableLiveData<>();
    private static MovieAPIClient instance;

    // making global RUNNABLE
    private RetrieveMoviesRunnable retrieveMoviesRunnable;

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

    // 1 - This method that we are going to call through the classes
    public void searchMoviesAPI(String query, int pageNumber) {
        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future mHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                mHandler.cancel(true);
            }
        }, 5000, TimeUnit.MICROSECONDS);
    }

    // Retrieving data from RESTAPI by runnable class
    // we have 2 types of Queries: the ID & search Queries
    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            getRetrofitResponse(query, pageNumber);
            // Getting the response objects
//            try {
//                Response response = getMovies(query, pageNumber).execute();
//                if (cancelRequest) {
//                    return;
//                }
//                if (response.code() == 200) {
//                    List<Movie> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());
//                    if (pageNumber == 1) {
//                        // Sending data to LiveData
//                        // PostValue: used for background thread
//                        // setValue: not for background thread
//                        mMovies.postValue(list);
//                    } else {
//                        List<Movie> currentMovies = mMovies.getValue();
//                        currentMovies.addAll(list);
//                        mMovies.postValue(currentMovies);
//                    }
//                } else {
//                    String error = response.errorBody().string();
//                    Log.v(TAG, "Error" + error);
//                    mMovies.postValue(null);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                mMovies.postValue(null);
//            }
        }

        // Method Temp
        private void getRetrofitResponse(String query, int pageNumber) {
            MovieApi movieApi = APIService.getInstance().getMovieAPI();

            Call<MovieSearchResponse> responseCall = movieApi
                    .searchMovies(Credentials.API_KEY, query, pageNumber);

            responseCall.enqueue(new Callback<MovieSearchResponse>() {
                @Override
                public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                    if (response.code() == 200) {
                        List<Movie> movies = new ArrayList<>(response.body().getMovies());
                        mMovies.postValue(movies);
                    } else {
                        try {
                            Log.e(TAG, "Error: " + response.code() + "\n" +
                                    "Error Body: " + response.errorBody().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            mMovies.postValue(null);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                }
            });
        }

        // Search method/ query
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber) {
            return APIService.getInstance().getMovieAPI().searchMovies(
                    Credentials.API_KEY,
                    query,
                    pageNumber);
        }

        // Search
        private void cancelRequest() {
            Log.v(TAG, "Cancelling Search Request");
            cancelRequest = true;
        }
    }
}
