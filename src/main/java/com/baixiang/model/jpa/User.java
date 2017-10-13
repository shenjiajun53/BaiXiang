package com.baixiang.model.jpa;


import com.baixiang.model.mongo.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shenjiajun on 2017/4/3.
 */

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"userName", "id"}))
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true)
    private String userName;

    private String pass;

    private String avatarPath;

    private String userIntro;

    private String sex;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role_types", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_type")
    private Set<Integer> roleTypeSet = new HashSet<>();

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


    public Set<Integer> getRoleTypeSet() {
        return roleTypeSet;
    }

    public void setRoleTypeSet(Set<Integer> roleTypeSet) {
        this.roleTypeSet = roleTypeSet;
    }

    public void addRole(Role role) {
        if (!this.roleTypeSet.contains(role.getType())) {
            this.roleTypeSet.add(role.getType());
        }
    }

    public void removeRole(Role role) {
        this.roleTypeSet.remove(role.getType());
    }

    public HashSet<Role> getRoleSet() {
        HashSet<Role> roleHashSet = new HashSet<>();
        for (int roleType : roleTypeSet) {
            Role role = Role.getRole(roleType);
            roleHashSet.add(role);
        }
        return roleHashSet;
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
