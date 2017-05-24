package com.baixiang.model;

import javax.persistence.*;

/**
 * Created by shenjj on 2017/5/24.
 */

public class RolePermission {


    private long id;

    private String name;

    private String description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
