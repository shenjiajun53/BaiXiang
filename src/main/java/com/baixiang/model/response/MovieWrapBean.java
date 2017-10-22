package com.baixiang.model.response;

import com.baixiang.model.jpa.*;
import com.baixiang.model.mongo.DoubanMovieBean;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;

public class MovieWrapBean {

    private Movie baseInfo;
    private DoubanMovieBean doubanInfo;

    public MovieWrapBean() {
    }

    public MovieWrapBean(Movie baseInfo, DoubanMovieBean doubanInfo) {
        this.doubanInfo = doubanInfo;
        this.baseInfo = baseInfo;
        clearRecycleMovie(baseInfo);
    }

    public Movie getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(Movie baseInfo) {
        this.baseInfo = baseInfo;
        clearRecycleMovie(baseInfo);
    }

    public DoubanMovieBean getDoubanInfo() {
        return doubanInfo;
    }

    public void setDoubanInfo(DoubanMovieBean doubanInfo) {
        this.doubanInfo = doubanInfo;
    }

    private void clearRecycleMovie(Movie movie) {
//        for (MovieImage movieImage : movie.getScreenShots()) {
//            movieImage.setMovie(null);
//        }
        for (MovieTorrent movieTorrent : movie.getMovieTorrents()) {
            movieTorrent.setMovie(null);
        }
        for (MovieTag movieTag : movie.getMovieTagSet()) {
            movieTag.setMovieSet(null);
        }
        for (Actor actor : movie.getActorSet()) {
            actor.setMovieSet(null);
        }
    }

//    private void initMovie(Movie movie) {
//        setId(movie.getId());
//        setVersion(movie.getVersion());
//        setMovieName(movie.getMovieName());
//        setMovieInfo(movie.getMovieInfo());
//        setPoster(movie.getPoster());
//        setCreateDate(movie.getCreateDate());
//        setUpdateDate(movie.getUpdateDate());
//        setReleaseDate(movie.getReleaseDate());
//        setViewTimes(movie.getViewTimes());
//        setDoubanUrl(movie.getDoubanUrl());
//        setDoubanId(movie.getDoubanId());
//        setImdbUrl(movie.getImdbUrl());
//        setScreenShots(movie.getScreenShots());
//        setMovieTorrents(movie.getMovieTorrents());
//        setMovieTagSet(movie.getMovieTagSet());
//        setActorSet(movie.getActorSet());
//    }


}
