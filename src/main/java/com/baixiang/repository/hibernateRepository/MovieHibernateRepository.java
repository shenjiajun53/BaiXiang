package com.baixiang.repository.hibernateRepository;

import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;

/**
 * Created by shenjj on 2017/5/12.
 */

@Repository
@Transactional
public class MovieHibernateRepository {
//    private static final Logger logger = LoggerFactory.getLogger(MovieHibernateRepository.class);
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    private Session getSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
//    public void save(Movie movie) {
//        getSession().save(movie);
//        return;
//    }
//
//
//    public void update(Movie movie) {
//        getSession().update(movie);
//        return;
//    }
//
//    public void saveOrUpdate(Movie movie) {
//        getSession().saveOrUpdate(movie);
//        return;
//    }
//
//    public void delete(Movie movie) {
//        getSession().delete(movie);
//        return;
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<Movie> getAll() {
//        return getSession().createQuery("from Movie").list();
//    }
//
//    public List<Movie> getByTag(String tag) {
////        String sql = "select * from movies inner join movie_tags on movies.id=movie_tags.movie_Id where movie_tags.movie_tag=" + "'" + tag + "'";
////        logger.info("sql=" + sql);
////        List result = getSession().createSQLQuery(sql).list();
////        return getSession().createQuery("from Movie as m join fetch m.screenShots as screenShots where screenShots.imageName='1495715557811-P40618-095242_2869174.jpg'").list();
//        return getSession().createQuery("from Movie as m  where :tag in elements(m.movieTagSet) ").setParameter("tag", tag).list();
//    }
//
//    public List<Movie> getByTag(String tag, int page, int pageSize) {
//        return getSession().createQuery("from Movie as m  where :tag in elements(m.movieTagSet) ")
//                .setParameter("tag", tag)
//                .setFirstResult(page * pageSize)
//                .setMaxResults(pageSize)
//                .list();
//    }
//
//    public int getSizeByTag(String tag) {
//        return getSession().createQuery("from Movie as m  where :tag in elements(m.movieTagSet) ")
//                .setParameter("tag", tag)
//                .list().size();
//    }
//
//    public List<Movie> getHostest() {
//        return getSession().createQuery("from Movie as m order by m.viewTimes desc ").setMaxResults(15).list();
//    }
//
//    public List<Movie> getNewest() {
//        return getSession().createQuery("from Movie").setMaxResults(15).list();
//    }
//
//    public Movie getById(long id) {
//        return (Movie) getSession().load(Movie.class, id);
//    }
//
//    public List<Movie> getByName(String movieName) {
//        return (List<Movie>) getSession().createQuery("from Movie as m where m.movieName=:movieName").setParameter("movieName", movieName).list();
//    }
//
//    public List<Movie> getIncludeName(String movieName) {
//        return (List<Movie>) getSession().createQuery("from Movie as m where m.movieName like :movieName").
//                setParameter("movieName", "%" + movieName + "%").list();
//    }
}
