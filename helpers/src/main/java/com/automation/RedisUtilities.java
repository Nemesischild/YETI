package com.automation;

import com.lambdaworks.redis.*;
import com.runner.runner.EnhancedLogging;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

public class RedisUtilities {


        public static RedisConnection openNewRedisConnection(String strRedisURI) throws RedisConnectionException {

            try {
                RedisClient redisClient = new RedisClient(
                        // RedisURI.create("redis://password@host:port"));
                        RedisURI.create(strRedisURI));
                RedisConnection<String, String> connection = redisClient.connect();
                EnhancedLogging.debug("Connected to Redis: " + strRedisURI);
                return connection;

            } catch (RedisConnectionException Re) {
                EnhancedLogging.debug("Connection to Redis: " + strRedisURI + "\nFAILED: " + Re.getMessage());
                return null;
            }
        }

}
