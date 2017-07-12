package com.baixiang.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shenjj on 2017/5/19.
 */

@Entity
@Table(name = "movie_images")
public class MovieImage implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String url;

    private String imageName;


     /*
     * @ManyToOne指明OrderItem和Order之间为多对一关系，多个OrderItem实例关联的都是同一个Order对象。
     * 其中的属性和@OneToMany基本一样，但@ManyToOne注释的fetch属性默认值是FetchType.EAGER。
     *
     * optional 属性是定义该关联类对是否必须存在，值为false时，关联类双方都必须存在，如果关系被维护端不存在，查询的结果为null。
     * 值为true 时, 关系被维护端可以不存在，查询的结果仍然会返回关系维护端，在关系维护端中指向关系被维护端的属性为null。
     * optional 属性的默认值是true。举个例：某项订单(Order)中没有订单项(OrderItem)，如果optional 属性设置为false，
     * 获取该项订单（Order）时，得到的结果为null，如果optional 属性设置为true，仍然可以获取该项订单，但订单中指向订单项的属性为null。
     * 实际上在解释Order 与OrderItem的关系成SQL时，optional 属性指定了他们的联接关系optional=false联接关系为inner join,
     * optional=true联接关系为left join。
     *
     * @JoinColumn:指明了被维护端（OrderItem）的外键字段为order_id，它和维护端的主键(orderid)连接,unique= true 指明order_id列的值不可重复。
     */

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "movie_id",referencedColumnName="id")
    private Movie movie;

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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MovieImage{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", imageName='" + imageName + '\'' +
                ", movie=" + movie +
                '}';
    }
}
