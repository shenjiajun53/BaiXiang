package com.baixiang.repository;

import com.baixiang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by shenjj on 2017/7/12.
 */

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUserName(String userName);

    User getById(Long id);

    List<User> findAll();

    User save(User user);

}
