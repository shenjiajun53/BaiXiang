package com.baixiang.repository;

import com.baixiang.model.Role;
import com.baixiang.model.RoleSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by shenjj on 2017/5/17.
 */

@Repository
public interface RoleRepository extends MongoRepository<RoleSet, String> {
    RoleSet findRoleSetById(@Param("id") String id);

}
