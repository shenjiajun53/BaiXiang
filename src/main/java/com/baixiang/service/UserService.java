package com.baixiang.service;

import com.baixiang.model.User;
import com.baixiang.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

    public User getById(Long userId) {
        return userRepository.getById(userId);
    }

    public User getUserBySession() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return null;
        }
        long userId = (long) subject.getPrincipal();
        User user = userRepository.getById(userId);
        if (null != user) {
            System.out.printf("session user=" + user.toString() + "/n ");
        }
        return user;
    }
}
