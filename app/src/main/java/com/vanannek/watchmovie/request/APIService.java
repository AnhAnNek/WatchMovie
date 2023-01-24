package com.vanannek.watchmovie.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vanannek.watchmovie.utils.Credentials;
import com.vanannek.watchmovie.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {

    private static APIService instance = null;
    private MovieApi movieApi;

    private APIService() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Credentials.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        movieApi = retrofit.create(MovieApi.class);
    }

    public static APIService getInstance() {
        if (instance == null) {
            synchronized (APIService.class) {
                if (instance == null) {
                    instance = new APIService();
                }
            }
        }
        return instance;
    }

    public MovieApi getMovieApi() {
        return movieApi;
    }
}
