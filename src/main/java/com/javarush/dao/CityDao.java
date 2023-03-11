package com.javarush.dao;

import com.javarush.entity.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CityDao extends GenericDao<City> {
    public CityDao(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City getCityById(Integer id) {
        Query<City> query = getCurrentSession().createQuery("select c from City as c join fetch c.country " +
                "where c.id = :ID", City.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }
}
