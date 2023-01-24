package com.vanannek.watchmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vanannek.watchmovie.models.Movie;
import com.vanannek.watchmovie.request.APIService;
import com.vanannek.watchmovie.response.MovieSearchResponse;
import com.vanannek.watchmovie.utils.Credentials;
import com.vanannek.watchmovie.utils.MovieApi;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListActivity extends AppCompatActivity {

    private static final String TAG = "MovieListActivity";

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> {
//            getRetrofitResponse();
            getRetrofitResponseAccordingToID();
        });
    }

    private void getRetrofitResponse() {
        MovieApi movieApi = APIService.getInstance().getMovieApi();

        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(
                        Credentials.API_KEY,
                        "Action",
                        1);

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    Log.v(TAG, "the response" + response.body().toString());
                    List<Movie> movies = new ArrayList<>(response.body().getMovies());
                    for (Movie movie : movies) {
//                        Log.v(TAG, "Title: " + movie.getTitle());
                        Log.d(TAG, new Gson().toJson(movie));
//                        Log.v(TAG, "The release date" + movie.getReleaseDate());
                    }
                    return;
                } else {
                    try {
                        Log.e(TAG, "Error: " + response.code() + "\n" +
                                "Error Body: " + response.errorBody().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Toast.makeText(MovieListActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRetrofitResponseAccordingToID() {
        MovieApi movieApi = APIService.getInstance().getMovieApi();
        Call<Movie> responseCall = movieApi.getMovie(
                343611,
                Credentials.API_KEY);

        responseCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.code() ==  200) {
                    Movie movie = response.body();
                    Log.v(TAG, "The response: title is " + movie.getTitle());
                } else {
                    try {
                        Log.e(TAG, "Error: " + response.code() + "\n" +
                                "Error Body: " + response.errorBody().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
}