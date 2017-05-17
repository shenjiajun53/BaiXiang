package com.baixiang.service;

import com.baixiang.model.User;
import com.baixiang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shenjj on 2017/5/17.
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getByName(String userName) {
        return userRepository.getByName(userName);
    }
}
