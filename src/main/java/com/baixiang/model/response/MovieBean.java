package com.baixiang.model.response;

import com.baixiang.model.jpa.Movie;
import com.baixiang.model.mongo.DoubanMovie;

public class MovieBean {
    private Movie movie;
    private DoubanMovie doubanMovie;

    public MovieBean() {
    }

    public MovieBean(Movie movie, DoubanMovie doubanMovie) {
        this.movie = movie;
        this.doubanMovie = doubanMovie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public DoubanMovie getDoubanMovie() {
        return doubanMovie;
    }

    public void setDoubanMovie(DoubanMovie doubanMovie) {
        this.doubanMovie = doubanMovie;
    }

    @Override
    public String toString() {
        return "MovieBean{" +
                "movie=" + movie +
                ", doubanMovie=" + doubanMovie +
                '}';
    }
}
