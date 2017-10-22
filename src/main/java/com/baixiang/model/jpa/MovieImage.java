package com.baixiang.model.jpa;

import com.baixiang.model.jpa.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shenjj on 2017/5/19.
 */

@Entity
@Table(name = "movie_images")
public class MovieImage implements Serializable {
    @Id
    @Column(name = "id")
    private long id;

    private String url;

    private String imageName;

    public MovieImage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    @Override
    public String toString() {
        return "MovieImage{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", imageName='" + imageName + '\'' +
//                ", movie=" + movie +
                '}';
    }
}
