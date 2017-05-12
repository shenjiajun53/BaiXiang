package com.baixiang.model;


import javax.persistence.*;

/**
 * Created by shenjj on 2017/5/12.
 */

@Entity
@Table(name = "Movie")
public class MovieBean {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    private String filmName;
    private String filmInfo;
    private String poster;
    private String screenShot;

    public MovieBean(String filmName, String filmInfo) {
        this.filmName = filmName;
        this.filmInfo = filmInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmInfo() {
        return filmInfo;
    }

    public void setFilmInfo(String filmInfo) {
        this.filmInfo = filmInfo;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getScreenShot() {
        return screenShot;
    }

    public void setScreenShot(String screenShot) {
        this.screenShot = screenShot;
    }
}
