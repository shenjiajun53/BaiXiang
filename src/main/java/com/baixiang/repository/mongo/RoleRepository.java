package com.baixiang.repository.mongo;

import com.baixiang.model.mongo.RoleSet;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by shenjj on 2017/5/17.
 */

//@Repository
public interface RoleRepository
//        extends MongoRepository<RoleSet, String>
{
    RoleSet findRoleSetById(@Param("id") String id);

}
