package com.baixiang.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by shenjiajun on 2017/4/3.
 */

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"userName", "id"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true)
    private String userName;
    private String pass;
    private String avatarPath;
    private String userIntro;
    private String sex;

    private String roleSetId;

    public User() {
    }

    public User(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoleSetId() {
        return roleSetId;
    }

    public void setRoleSetId(String roleSetId) {
        this.roleSetId = roleSetId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", pass='" + pass + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", userIntro='" + userIntro + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
