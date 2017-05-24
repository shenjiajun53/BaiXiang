package com.baixiang.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by shenjj on 2017/5/9.
 */


public enum Role {
    BangZhu(1, "BangZhu", "BangZhu", new ArrayList<String>(Arrays.asList("all", "edit_movie", "up_grade_role", "delete_role"))),
    ZhangLao(2, "ZhangLao", "ZhangLao", new ArrayList<String>(Arrays.asList("edit_movie", "delete_role"))),
    XiaoDi(3, "XiaoDi", "XiaoDi", new ArrayList<String>(Arrays.asList("edit_movie"))),
    BaiXing(4, "BaiXing", "BaiXing", new ArrayList<String>(Arrays.asList("edit_movie")));


    private int type;

    private String name;

    private String description;


    private ArrayList<String> permissions = new ArrayList<>();

    private Role() {
    }

    private Role(int type, String name, String description, ArrayList<String> permissions) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    public static Role getRole(int type) {
        switch (type) {
            case 1:
                return BangZhu;
            case 2:
                return ZhangLao;
            case 3:
                return XiaoDi;
            default:
                return BaiXing;
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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


    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +


                '}';
    }
}
