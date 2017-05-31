package com.baixiang.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shenjj on 2017/5/12.
 */

@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String movieName;
    private String movieInfo;
    private String poster;
    private Date createDate;//订单创建日期

    private String releaseDate;

    private Long viewTimes;

    /*
* @OneToMany: 指明Order 与OrderItem关联关系为一对多关系
*
* mappedBy: 定义类之间的双向关系。如果类之间是单向关系，不需要提供定义，如果类和类之间形成双向关系，我们就需要使用这个属性进行定义，
* 否则可能引起数据一致性的问题。
*
* cascade: CascadeType[]类型。该属性定义类和类之间的级联关系。定义的级联关系将被容器视为对当前类对象及其关联类对象采取相同的操作，
* 而且这种关系是递归调用的。举个例子：Order 和OrderItem有级联关系，那么删除Order 时将同时删除它所对应的OrderItem对象。
* 而如果OrderItem还和其他的对象之间有级联关系，那么这样的操作会一直递归执行下去。cascade的值只能从CascadeType.PERSIST（级联新建）、
* CascadeType.REMOVE（级联删除）、CascadeType.REFRESH（级联刷新）、CascadeType.MERGE（级联更新）中选择一个或多个。
* 还有一个选择是使用CascadeType.ALL，表示选择全部四项。
*
* fatch: 可选择项包括：FetchType.EAGER 和FetchType.LAZY。前者表示关系类(本例是OrderItem类)在主类(本例是Order类)加载的时候
* 同时加载;后者表示关系类在被访问时才加载,默认值是FetchType. LAZY。
*
*/
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(value = "id ASC")//注释指明加载OrderItem时按id的升序排序
    @JsonIgnore
    private Set<MovieImage> screenShots = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(value = "id ASC")//注释指明加载OrderItem时按id的升序排序
    @JsonIgnore
    private Set<MovieTorrent> movieTorrents = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "movie_tags", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "movie_tag")
    private Set<String> movieTagSet = new HashSet<>();

    public Movie() {
    }

    public Movie(String movieName, String movieInfo) {
        this.movieName = movieName;
        this.movieInfo = movieInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<MovieImage> getScreenShots() {
        return screenShots;
    }

    public void setScreenShots(Set<MovieImage> screenShots) {
        this.screenShots = screenShots;
    }

    public Set<MovieTorrent> getMovieTorrents() {
        return movieTorrents;
    }

    public void setMovieTorrents(Set<MovieTorrent> movieTorrents) {
        this.movieTorrents = movieTorrents;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<String> getMovieTagSet() {
        return movieTagSet;
    }

    public void setMovieTagSet(Set<String> movieTagSet) {
        this.movieTagSet = movieTagSet;
    }

    //@Temporal注释用来指定java.util.Date 或java.util.Calendar 属性与数据库类型date,time 或timestamp 中的那一种类型进行映射
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(Long viewTimes) {
        this.viewTimes = viewTimes;
    }

    public void addScreenShot(MovieImage screenShot) {
        if (!this.screenShots.contains(screenShot)) {
            this.screenShots.add(screenShot);
            screenShot.setMovie(this);
        }
    }

    public void removeScreenShot(MovieImage screenShot) {
        screenShot.setMovie(null);
        this.screenShots.remove(screenShot);
    }

    public void addTorrent(MovieTorrent movieTorrent) {
        if (!this.movieTorrents.contains(movieTorrent)) {
            this.movieTorrents.add(movieTorrent);
            movieTorrent.setMovie(this);
        }
    }

    public void removTorrent(MovieTorrent movieTorrent) {
        movieTorrent.setMovie(null);
        this.movieTorrents.remove(movieTorrent);
    }


    public void addTag(String tag) {
        if (!movieTagSet.contains(tag)) {
            movieTagSet.add(tag);
        }
    }

    public void removeTag(String tag) {
        movieTagSet.remove(tag);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", movieInfo='" + movieInfo + '\'' +
                ", poster='" + poster + '\'' +
                ", createDate=" + createDate +

                '}';
    }
}
