package com.javarush.connection;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;

import static java.lang.System.*;

public class RedisClientSessionFactory {
    public static RedisClient prepareRedisClient() {
        RedisClient localhost = RedisClient.create(RedisURI.create("localhost", 6379));
        try (StatefulRedisConnection<String, String> connect = localhost.connect()) {
            out.println("\nConnected to Redis\n");
        }
        return localhost;
    }
}
