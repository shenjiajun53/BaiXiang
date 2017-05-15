package com.baixiang.repository;

import com.baixiang.model.Movie;
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

    public void save(Movie movie) {
        getSession().save(movie);
        return;
    }

    public void delete(Movie movie) {
        getSession().delete(movie);
        return;
    }

    @SuppressWarnings("unchecked")
    public List<Movie> getAll() {
        return getSession().createQuery("from Movie").list();
    }

    public Movie getById(long id) {
        return (Movie) getSession().load(Movie.class, id);
    }

    public void update(Movie movie) {
        getSession().update(movie);
        return;
    }

}
