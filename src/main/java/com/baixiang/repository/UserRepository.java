package com.baixiang.repository;

import com.baixiang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shenjj on 2017/7/12.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getFirstByUserName(String userName);

    User getById(Long id);

    List<User> findAll();
}
