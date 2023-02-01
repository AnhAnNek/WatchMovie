package com.vanannek.watchmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    // model class for our movies

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

    @SerializedName("original_language")
    private String originalLanguage;

    // For purpose of simplicity, I will use the release_date instead of category
    // genres in nested json object, we will learn it later on this series

    public Movie(String title, String posterPath, String releaseDate,
                 int movieId, float voteAverage, String overview, String originalLanguage) {
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.movieId = movieId;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.originalLanguage = originalLanguage;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        movieId = in.readInt();
        voteAverage = in.readFloat();
        overview = in.readString();
        originalLanguage = in.readString();
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

    public String getOriginalLanguage() {
        return originalLanguage;
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
        parcel.writeString(originalLanguage);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", movieId=" + movieId +
                ", voteAverage=" + voteAverage +
                ", overview='" + overview + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                '}';
    }
}
