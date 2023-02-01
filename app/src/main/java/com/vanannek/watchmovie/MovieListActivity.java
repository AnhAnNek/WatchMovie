package com.vanannek.watchmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vanannek.watchmovie.adapters.MovieRecyclerView;
import com.vanannek.watchmovie.adapters.OnMovieListener;
import com.vanannek.watchmovie.models.Movie;
import com.vanannek.watchmovie.request.APIService;
import com.vanannek.watchmovie.response.MovieSearchResponse;
import com.vanannek.watchmovie.utils.Credentials;
import com.vanannek.watchmovie.utils.MovieApi;
import com.vanannek.watchmovie.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    private static final String TAG = "MovieListActivity";

    // Before we run our app, we need to add the Network Security config

    // RecyclerView
    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerAdapter;

    // ViewModel
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // SearchView
        setupSearchView();

        recyclerView = findViewById(R.id.recyclerView);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        observeAnyChange();
        configureRecyclerView();
    }

    // Get data from search view & query the api to get the results (Movies)
    private void setupSearchView() {
        final SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMoviesAPI(
                        // The Search string got from search view
                        query,
                        1
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    // Observing any data change
    private void observeAnyChange() {
        movieListViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                // Observing for any data change
                if (movies == null) return;
                movieRecyclerAdapter.setMovies(movies);
                for (Movie movie : movies) {
                    // Getting the data in log
                    Log.v(TAG, "onChanged: " + movie.getTitle());
                }
            }
        });
    }

    // 4 - Calling method in Main Activity
    private void searchMoviesAPI(String query, int pageNumber) {
        movieListViewModel.searchMoviesAPI(query, pageNumber);
    }

    // 5 - Initializing recyclerView & adding data to it
    private void configureRecyclerView() {
        // Live data can't be passed via the constructor
        movieRecyclerAdapter = new MovieRecyclerView(this);

        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getRetrofitResponse() {
        MovieApi movieApi = APIService.getInstance().getMovieAPI();

        Call<MovieSearchResponse> responseCall = movieApi.searchMovies(
                Credentials.API_KEY,
                "Action",
                1);

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    List<Movie> movies = new ArrayList<>(response.body().getMovies());

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
        MovieApi movieApi = APIService.getInstance().getMovieAPI();
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

    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}