package com.baixiang.repository;

import com.baixiang.model.Movie;
import com.baixiang.model.MovieTorrent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shenjj on 2017/6/29.
 */

@Repository
public class TorrentRepository {
    private static final Logger logger = LoggerFactory.getLogger(TorrentRepository.class);
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(MovieTorrent movieTorrent) {
        getSession().save(movieTorrent);
        return;
    }


    public void update(MovieTorrent movieTorrent) {
        getSession().update(movieTorrent);
        return;
    }

    public void saveOrUpdate(MovieTorrent movieTorrent) {
        getSession().saveOrUpdate(movieTorrent);
        return;
    }

    public void delete(MovieTorrent movieTorrent) {
        getSession().delete(movieTorrent);
        return;
    }

    public List<MovieTorrent> getIncludeName(String torrentName) {
        return (List<MovieTorrent>) getSession().createQuery("from MovieTorrent as t where t.torrentName like :torrentName").
                setParameter("torrentName", "%" + torrentName + "%").list();
    }
}
