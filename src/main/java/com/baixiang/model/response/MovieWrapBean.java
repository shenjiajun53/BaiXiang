package com.baixiang.model.response;

import com.baixiang.model.jpa.Movie;
import com.baixiang.model.mongo.DoubanMovieBean;

public class MovieWrapBean {
    private Movie baseInfo;
    private DoubanMovieBean doubanInfo;

    public MovieWrapBean() {
    }

    public MovieWrapBean(Movie baseInfo, DoubanMovieBean doubanInfo) {
        this.baseInfo = baseInfo;
        this.doubanInfo = doubanInfo;
    }

    public Movie getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(Movie baseInfo) {
        this.baseInfo = baseInfo;
    }

    public DoubanMovieBean getDoubanInfo() {
        return doubanInfo;
    }

    public void setDoubanInfo(DoubanMovieBean doubanInfo) {
        this.doubanInfo = doubanInfo;
    }

    @Override
    public String toString() {
        return "MovieWrapBean{" +
                "baseInfo=" + baseInfo +
                ", doubanInfo=" + doubanInfo +
                '}';
    }
}
