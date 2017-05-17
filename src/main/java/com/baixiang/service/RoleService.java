package com.baixiang.service;

import com.baixiang.model.Role;
import com.baixiang.model.RoleSet;
import com.baixiang.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by shenjj on 2017/5/17.
 */

@Service
public class RoleService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private RoleRepository roleRepository;

    public RoleSet findRoleById(String id) {
        return roleRepository.findRoleSetById(id);
    }
}
