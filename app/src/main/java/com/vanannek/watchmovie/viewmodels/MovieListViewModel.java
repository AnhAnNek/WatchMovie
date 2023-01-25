package com.vanannek.watchmovie.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vanannek.watchmovie.models.Movie;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    // this class is used for VIEWMODEL

    // LiveData
    private MutableLiveData<List<Movie>> mMovies = new MutableLiveData<>();

    // Contructor
    public MovieListViewModel() {
    }

    public MovieListViewModel(MutableLiveData<List<Movie>> mMovies) {
        this.mMovies = mMovies;
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }
}
