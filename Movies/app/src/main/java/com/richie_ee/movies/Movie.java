package com.richie_ee.movies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Richie on 10/20/2016.
 */
public class Movie implements Parcelable{

    private String posterPath;
    private String title;
    private String plot;
    private String rating;
    private String releaseDate;

    public Movie(String posterPath, String title, String plot, String rating, String releaseDate){
        this.posterPath= posterPath;
        this.title= title;
        this.plot = plot;
        this.rating= rating;
        this.releaseDate = releaseDate;
    }

    public String getPosterPath(){
        return posterPath;
    }

    public String getPlot() {
        return plot;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    //*******************Parcelable**********************************************//


    private Movie(Parcel in){
        String[]data = new String[5];

        in.readStringArray(data);
        this.posterPath = data[0];
        this.title = data[1];
        this.plot = data[2];
        this.rating = data[3];
        this.releaseDate = data[4];

    }
    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeStringArray(new String[]{
            this.posterPath,
            this.title,
            this.plot,
            this.rating,
            this.releaseDate});
        }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>(){

        @Override
        public Movie createFromParcel(Parcel in){
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size){
            return new Movie[size];
        }
    };





}
