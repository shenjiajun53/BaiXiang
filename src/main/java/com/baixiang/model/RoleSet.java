package com.baixiang.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Created by shenjj on 2017/5/17.
 */

@Document(collection = "RoleSets")
public class RoleSet {

//    Set<Role> roleSet;

    @Id
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public Set<Role> getRoleSet() {
//        return roleSet;
//    }
//
//    public void setRoleSet(Set<Role> roleSet) {
//        this.roleSet = roleSet;
//    }
}
