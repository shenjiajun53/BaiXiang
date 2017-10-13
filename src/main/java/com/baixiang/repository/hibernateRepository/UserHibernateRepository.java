package com.baixiang.repository.hibernateRepository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/5/16.
 */

@Repository
@Transactional
public class UserHibernateRepository {
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    private Session getSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
//    public void save(User user) {
//        getSession().save(user);
//        return;
//    }
//
//    public void delete(User user) {
//        getSession().delete(user);
//        return;
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<User> getAll() {
//        return getSession().createQuery("from User").list();
//    }
//
//    public User getById(long id) {
//        return (User) getSession().createQuery("from User where id=:id").setParameter("id", id).uniqueResult();
//    }
//
//    public User getByName(String userName) {
//        return (User) getSession().createQuery(
//                "from User where userName = :userName")
//                .setParameter("userName", userName)
//                .uniqueResult();
//    }
//
//    public void update(User user) {
//        getSession().update(user);
//        return;
//    }

}
