package com.baixiang.service;

import com.baixiang.model.jpa.User;
import com.baixiang.model.response.UserBean;
import com.baixiang.repository.hibernateRepository.UserHibernateRepository;
import com.baixiang.repository.jpa.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by shenjj on 2017/5/17.
 */

@Service
public class UserService {

    @Autowired
    UserHibernateRepository userHibernateRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getByName(String userName) {
        return userRepository.getFirstByUserName(userName);
    }

    public User getById(Long userId) {
        return userRepository.getById(userId);
    }

    public User save(User user) {
        userRepository.saveAndFlush(user);
        return user;
    }

    public void delete(User user) {
        userRepository.delete(user);
        return;
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

    public UserBean getUserBeanBySession() {
        User user = getUserBySession();
        if (null == user) {
            return null;
        }
        return new UserBean(user);
    }
}
