package com.baixiang.model.jpa;

import com.baixiang.model.jpa.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shenjj on 2017/5/19.
 */

@Entity
@Table(name = "images")
public class Image implements Serializable {
    public static final String TYPE_POSTER = "poster";
    public static final String TYPE_AVATAR = "avatar";
    public static final String TYPE_SCREENSHOT = "screenshot";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String url;

    private String imageName;

    private String type;

    public Image() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", imageName='" + imageName + '\'' +
//                ", movie=" + movie +
                '}';
    }
}
