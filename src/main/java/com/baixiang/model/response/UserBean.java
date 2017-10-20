package com.baixiang.model.response;

import com.baixiang.model.jpa.User;

public class UserBean {
    private long id;
    private String userName;
    private String avatarPath;
    private String userIntro;
    private String sex;

    public UserBean() {
    }

    public UserBean(User user) {
        setId(user.getId());
        setUserName(user.getUserName());
        setAvatarPath(user.getAvatarPath());
        setSex(user.getSex());
        setUserIntro(user.getUserIntro());
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
}
