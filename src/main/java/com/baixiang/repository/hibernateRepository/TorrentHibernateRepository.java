package com.baixiang.repository.hibernateRepository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by shenjj on 2017/6/29.
 */

@Repository
@Transactional
public class TorrentHibernateRepository {
//    private static final Logger logger = LoggerFactory.getLogger(TorrentRepository.class);
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    private Session getSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
//    public void save(MovieTorrent movieTorrent) {
//        getSession().save(movieTorrent);
//        return;
//    }
//
//
//    public void update(MovieTorrent movieTorrent) {
//        getSession().update(movieTorrent);
//        return;
//    }
//
//    public void saveOrUpdate(MovieTorrent movieTorrent) {
//        getSession().saveOrUpdate(movieTorrent);
//        return;
//    }
//
//    public void delete(MovieTorrent movieTorrent) {
//        getSession().delete(movieTorrent);
//        return;
//    }
//
//    public List<MovieTorrent> getIncludeName(String torrentName) {
//        return (List<MovieTorrent>) getSession().createQuery("from MovieTorrent as t where t.torrentName like :torrentName").
//                setParameter("torrentName", "%" + torrentName + "%").list();
//    }
}
