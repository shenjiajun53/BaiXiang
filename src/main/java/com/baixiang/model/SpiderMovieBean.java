package com.baixiang.model;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;

public class SpiderMovieBean {
    private String movieName;
    private String movieInfo;
    private String poster;
    private String doubanUrl;
    private long doubanId;
    private String imdbUrl;
    private ArrayList<String> tagList;
    private ArrayList<String> actorList;
    private String releaseDate;//上映日期

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(String movieInfo) {
        this.movieInfo = movieInfo;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDoubanUrl() {
        return doubanUrl;
    }

    public void setDoubanUrl(String doubanUrl) {
        this.doubanUrl = doubanUrl;
    }

    public long getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(long doubanId) {
        this.doubanId = doubanId;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }

    public void setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
    }

    public ArrayList<String> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<String> tagList) {
        this.tagList = tagList;
    }

    public ArrayList<String> getActorList() {
        return actorList;
    }

    public void setActorList(ArrayList<String> actorList) {
        this.actorList = actorList;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "SpiderMovieBean{" +
                "movieName='" + movieName + '\'' +
                ", movieInfo='" + movieInfo + '\'' +
                ", poster='" + poster + '\'' +
                ", doubanUrl='" + doubanUrl + '\'' +
                ", doubanId=" + doubanId +
                ", imdbUrl='" + imdbUrl + '\'' +
                ", tagList=" + tagList +
                ", actorList=" + actorList +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
