package com.baixiang.repository;

import com.baixiang.model.MovieBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by shenjj on 2017/5/12.
 */

@Repository
@Transactional
public class MovieRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(MovieBean movie) {
        getSession().save(movie);
        return;
    }

    public void delete(MovieBean movie) {
        getSession().delete(movie);
        return;
    }

    @SuppressWarnings("unchecked")
    public List<MovieBean> getAll() {
        return getSession().createQuery("from Movie").list();
    }

    public MovieBean getById(long id) {
        return (MovieBean) getSession().load(MovieBean.class, id);
    }

    public void update(MovieBean movie) {
        getSession().update(movie);
        return;
    }

}
