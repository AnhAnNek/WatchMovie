package com.vanannek.watchmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    // model class for our movies

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("id")
    private int movieId;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("overview")
    private String overview;

    public Movie(String title, String posterPath, String releaseDate,
                 int movieId, float voteAverage, String overview) {
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.movieId = movieId;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        movieId = in.readInt();
        voteAverage = in.readFloat();
        overview = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getMovieId() {
        return movieId;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(releaseDate);
        parcel.writeInt(movieId);
        parcel.writeFloat(voteAverage);
        parcel.writeString(overview);
    }
}
