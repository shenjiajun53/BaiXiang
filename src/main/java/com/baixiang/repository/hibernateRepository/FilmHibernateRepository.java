package com.baixiang.repository.hibernateRepository;

import com.baixiang.model.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/7/1.
 */

@Repository
@Transactional
public class FilmHibernateRepository {
//    private static final Logger logger = LoggerFactory.getLogger(FilmRepository.class);
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    private Session getSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
//    public void save(Film film) {
//        getSession().save(film);
//        return;
//    }
//
//
//    public void update(Film film) {
//        getSession().update(film);
//        return;
//    }
}
