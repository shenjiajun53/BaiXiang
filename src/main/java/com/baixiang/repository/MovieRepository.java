package com.baixiang.repository;

import com.baixiang.controller.RouterController;
import com.baixiang.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(MovieRepository.class);
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

    public List<Movie> getByTag(String tag) {
//        String sql = "select * from movies inner join movie_tags on movies.id=movie_tags.movie_Id where movie_tags.movie_tag=" + "'" + tag + "'";
//        logger.info("sql=" + sql);
//        List result = getSession().createSQLQuery(sql).list();

        return getSession().createQuery("from Movie as m join m.movieTagSet as tags on VALUE(tags.movie_tag)=:tag").setParameter("tag",tag).list();
    }

    public Movie getById(long id) {
        return (Movie) getSession().load(Movie.class, id);
    }

    public void update(Movie movie) {
        getSession().update(movie);
        return;
    }

}
