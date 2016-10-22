package com.richie_ee.movies;

/**
 * Created by Richie on 10/20/2016.
 */
public class Movie {

    private String posterPath;
    private String title;
    private String plot;
    private String rating;
    private String releaseDate;

    public Movie(String[] movieInfo){
        this.posterPath=movieInfo[0];
        this.title= movieInfo[1];
        this.plot = movieInfo[2];
        this.rating= movieInfo[3];
        this.releaseDate = movieInfo[4];
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

}
