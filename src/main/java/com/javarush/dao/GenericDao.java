package com.javarush.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public abstract class GenericDao<T> {
    private final SessionFactory sessionFactory;
    private final Class<T> clazz;

    protected GenericDao(final Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    public List<T> findAll() {
        String query = "select c from " + clazz.getName() + " as c join fetch c.languages";
        return getCurrentSession().createQuery(query, clazz).list();
    }

    public List<T> getItems(int offset, int limit) {
        Query<T> query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public int getTotalCount() {
        Query<Long> query = getCurrentSession().createQuery("select count(c) from " + clazz.getName() + " c", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
