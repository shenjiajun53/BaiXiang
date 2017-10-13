package com.baixiang.model.jpa;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/1.
 */

@Entity
@Table(name = "films", uniqueConstraints = @UniqueConstraint(columnNames = {"filmTitle", "id"}))
public class Film {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String filmTitle;

    @Column(columnDefinition = "LONGTEXT")
    private String filmInfo;

    @Column(columnDefinition = "LONGTEXT")
    private String filmUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getFilmInfo() {
        return filmInfo;
    }

    public void setFilmInfo(String filmInfo) {
        this.filmInfo = filmInfo;
    }

    public String getFilmUrl() {
        return filmUrl;
    }

    public void setFilmUrl(String filmUrl) {
        this.filmUrl = filmUrl;
    }
}
