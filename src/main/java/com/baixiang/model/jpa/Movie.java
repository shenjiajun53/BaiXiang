package com.baixiang.model.jpa;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;

/**
 * Created by shenjj on 2017/5/12.
 */

@Entity
@Table(name = "movies", uniqueConstraints = @UniqueConstraint(columnNames = {"movieName", "id"}))
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private int version;

    private String movieName;

    @Column(columnDefinition = "LONGTEXT")
    private String movieInfo;

    private long posterId;
    private String posterUrl;

    //@Temporal注释用来指定java.util.Date 或java.util.Calendar 属性与数据库类型date,time 或timestamp 中的那一种类型进行映射
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createDate;//创建日期

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updateDate;//更新日期

    private String releaseDate;//上映日期

    private Long viewTimes;

    private String doubanUrl;
    private String doubanId;
    private String imdbUrl;

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

    //    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(value = "id ASC")//注释指明加载OrderItem时按id的升序排序
    private Set<MovieTorrent> movieTorrents = new HashSet<>();

    //    @JsonIgnore
    @ManyToMany(cascade = {MERGE, REMOVE, REFRESH, DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "movies_tags",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<MovieTag> movieTagSet = new HashSet<>();

    //    @JsonIgnore
    @ManyToMany(cascade = {MERGE, REMOVE, REFRESH, DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
    @OrderBy(value = "id asc")
    private Set<Actor> actorSet = new HashSet<>();


    @ElementCollection
    @CollectionTable(name = "screenshot_ids", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "id")
    private Set<Long> screenshotIdSet = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "screenshot_urls", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "url")
    private Set<String> screenshotUrlSet = new HashSet<>();

    public Movie() {
        setCreateDate(new Date());
    }

    public Movie(String movieName, String movieInfo) {
        this.movieName = movieName;
        this.movieInfo = movieInfo;
        setCreateDate(new Date());
    }

    public void cleanScreenshotId() {
        this.screenshotIdSet.clear();
    }

    public void addScreenshotId(long screenshotId) {
        if (!this.screenshotIdSet.contains(screenshotId)) {
            this.screenshotIdSet.add(screenshotId);
        }
    }

    public void cleanScreenshotUrl() {
        this.screenshotUrlSet.clear();
    }

    public void addScreenshotUrl(String screenshotUrl) {
        if (!this.screenshotUrlSet.contains(screenshotUrl)) {
            this.screenshotUrlSet.add(screenshotUrl);
        }
    }

    public void addScreenShot(Image screenShot) {
        if (!this.screenshotIdSet.contains(screenShot.getId())) {
            this.screenshotIdSet.add(screenShot.getId());
        }
        if (!this.screenshotUrlSet.contains(screenShot.getUrl())) {
            this.screenshotUrlSet.add(screenShot.getUrl());
        }
    }

    public void removeScreenShot(Image screenShot) {
        this.screenshotIdSet.remove(screenShot.getId());
        this.screenshotUrlSet.remove(screenShot.getUrl());
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

    public void addTag(MovieTag tag) {
        for (MovieTag movieTag : movieTagSet) {
            if (movieTag.getTagName().equals(tag.getTagName())) {
                return;
            }
        }
        movieTagSet.add(tag);
    }

    public void removeTag(MovieTag tag) {
        movieTagSet.remove(tag);
    }

//    public void addTag(String tag) {
//        if (!movieTagSet.contains(tag)) {
//            movieTagSet.add(tag);
//        }
//    }
//
//    public void removeTag(String tag) {
//        movieTagSet.remove(tag);
//    }

    public void addActor(Actor actor) {
        for (Actor existActor : actorSet) {
            if (existActor.getActorName().equals(actor.getActorName())) {
                return;
            }
        }
        actorSet.add(actor);

    }

    public void removeActor(Actor actor) {
        actorSet.remove(actor);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public long getPosterId() {
        return posterId;
    }

    public void setPosterId(long posterId) {
        this.posterId = posterId;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Set<Long> getScreenshotIdSet() {
        return screenshotIdSet;
    }

    public void setScreenshotIdSet(Set<Long> screenshotIdSet) {
        this.screenshotIdSet = screenshotIdSet;
    }

    public Set<String> getScreenshotUrlSet() {
        return screenshotUrlSet;
    }

    public void setScreenshotUrlSet(Set<String> screenshotUrlSet) {
        this.screenshotUrlSet = screenshotUrlSet;
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

//    public Set<String> getMovieTagSet() {
//        return movieTagSet;
//    }
//
//    public void setMovieTagSet(Set<String> movieTagSet) {
//        this.movieTagSet = movieTagSet;
//    }


    public Set<MovieTag> getMovieTagSet() {
        return movieTagSet;
    }

    public void setMovieTagSet(Set<MovieTag> movieTagSet) {
        this.movieTagSet = movieTagSet;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
        if (null == getCreateDate()) {
            setCreateDate(updateDate);
        }
    }

    public Long getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(Long viewTimes) {
        this.viewTimes = viewTimes;
    }

    public Set<Actor> getActorSet() {
        return actorSet;
    }

    public void setActorSet(Set<Actor> actorSet) {
        this.actorSet = actorSet;
    }

    public String getDoubanUrl() {
        return doubanUrl;
    }

    public void setDoubanUrl(String doubanUrl) {
        this.doubanUrl = doubanUrl;
    }

    public String getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(String doubanId) {
        this.doubanId = doubanId;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }

    public void setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", version=" + version +
                ", movieName='" + movieName + '\'' +
                ", movieInfo='" + movieInfo + '\'' +
                ", posterId=" + posterId +
                ", posterUrl='" + posterUrl + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", releaseDate='" + releaseDate + '\'' +
                ", viewTimes=" + viewTimes +
                ", doubanUrl='" + doubanUrl + '\'' +
                ", doubanId='" + doubanId + '\'' +
                ", imdbUrl='" + imdbUrl + '\'' +
                ", movieTorrents=" + movieTorrents +
                ", movieTagSet=" + movieTagSet +
                ", actorSet=" + actorSet +
                ", screenshotIdSet=" + screenshotIdSet +
                ", screenshotUrlSet=" + screenshotUrlSet +
                '}';
    }
}
