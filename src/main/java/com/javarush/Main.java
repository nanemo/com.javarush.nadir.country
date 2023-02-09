package com.javarush;

import com.javarush.dao.ServiceDao;
import com.javarush.entity.City;
import com.javarush.redis.CityCountry;

import java.util.List;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        ServiceDao serviceDao = new ServiceDao();
        List<City> allCities = serviceDao.fetchData(serviceDao);
        List<CityCountry> preparedData = serviceDao.transformData(allCities);
        serviceDao.pushToRedis(preparedData);

        serviceDao.getSessionFactory().getCurrentSession().close();

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = currentTimeMillis();
        serviceDao.testRedisData(ids);
        long stopRedis = currentTimeMillis();

        long startMysql = currentTimeMillis();
        serviceDao.testMysqlData(ids);
        long stopMysql = currentTimeMillis();

        out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));

        serviceDao.shutdown();
    }

}
