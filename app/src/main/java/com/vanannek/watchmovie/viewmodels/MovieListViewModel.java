package com.vanannek.watchmovie.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vanannek.watchmovie.models.Movie;
import com.vanannek.watchmovie.repository.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    // this class is used for VIEWMODEL

    private MovieRepository movieRepository;

    // Constructor
    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieRepository.getMovies();
    }
}
