package com.vanannek.watchmovie.request;

import com.vanannek.watchmovie.utils.Credentials;
import com.vanannek.watchmovie.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    private Service() {
    }

    public static MovieApi getMovieApi() {
        return movieApi;
    }
}
