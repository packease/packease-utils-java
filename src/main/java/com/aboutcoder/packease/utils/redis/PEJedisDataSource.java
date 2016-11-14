package com.aboutcoder.packease.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 11/13/16 2:51 PM<br />
 * @description <br />
 */
@Repository("jedisDS")
public class PEJedisDataSource {
    private final static Logger logger = LoggerFactory.getLogger(PEJedisDataSource.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    public ShardedJedis getRedisClient() {
        ShardedJedis shardJedis = null;
        try {
            shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            logger.error("[JedisDS] getRedisClent error:" + e.getMessage());
            if (null != shardJedis){
                shardJedis.close();
            }
        }
        return null;
    }

    public void returnResource(ShardedJedis shardedJedis) {
        shardedJedis.close();
    }

    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        shardedJedis.close();
    }
}
