package com.baixiang.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shenjj on 2017/6/28.
 */

@Entity
@Table(name = "actors", uniqueConstraints = @UniqueConstraint(columnNames = {"actorName", "id"}))
public class Actor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true)
    private String actorName;

    private int age;

    private String nation;

    @Column(columnDefinition = "LONGTEXT")
    private String actorInfo;

    @JsonIgnore
    @ManyToMany(mappedBy = "actorSet")
    private Set<Movie> movieSet=new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getActorInfo() {
        return actorInfo;
    }

    public void setActorInfo(String actorInfo) {
        this.actorInfo = actorInfo;
    }

    public Set<Movie> getMovieSet() {
        return movieSet;
    }

    public void setMovieSet(Set<Movie> movieSet) {
        this.movieSet = movieSet;
    }
}
