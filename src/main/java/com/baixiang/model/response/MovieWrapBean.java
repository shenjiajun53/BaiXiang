package com.baixiang.model.response;

import com.baixiang.model.jpa.*;
import com.baixiang.model.mongo.DoubanMovieBean;
import com.baixiang.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;

@Component
public class MovieWrapBean {


    private Movie baseInfo;
    private DoubanMovieBean doubanInfo;

    private List<Image> screenshotList = new ArrayList<>();

    public MovieWrapBean() {
    }

    public MovieWrapBean(Movie baseInfo) {
        this.baseInfo = baseInfo;
        initBaseInfo(baseInfo);
    }

    public MovieWrapBean(Movie baseInfo, DoubanMovieBean doubanInfo) {
        this.doubanInfo = doubanInfo;
        this.baseInfo = baseInfo;
        initBaseInfo(baseInfo);
    }

    public Movie getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(Movie baseInfo) {
        this.baseInfo = baseInfo;
        initBaseInfo(baseInfo);
    }

    public DoubanMovieBean getDoubanInfo() {
        return doubanInfo;
    }

    public void setDoubanInfo(DoubanMovieBean doubanInfo) {
        this.doubanInfo = doubanInfo;
    }

    public List<Image> getScreenshotList() {
        return screenshotList;
    }

    public void setScreenshotList(List<Image> screenshotList) {
        this.screenshotList = screenshotList;
    }

    private void initBaseInfo(Movie movie) {
//        for (Image movieImage : movie.getScreenShots()) {
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

    public void initScreenshotList(ImageService imageService) {
        if (null != baseInfo) {
            for (long id : baseInfo.getScreenshotIdSet()) {
                if (null != imageService) {
                    Image image = imageService.findById(id);
                    if (null != image) {
                        screenshotList.add(image);
                    }
                }
            }
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
