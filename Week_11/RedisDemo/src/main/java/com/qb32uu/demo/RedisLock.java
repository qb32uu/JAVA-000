package com.qb32uu.demo;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * 分布式锁
 * 
 * @author Billion
 *
 */
@Component
public class RedisLock {

    @Autowired
    private Jedis jedis;

    public boolean lock(String lock, int secondsToExpire) {
        SetParams setParams = new SetParams();
        setParams.ex(secondsToExpire);
        setParams.nx();
        String response = jedis.set(lock, lock, setParams);

        jedis.expire(lock, secondsToExpire);
        return "ok".equalsIgnoreCase(response);
    }

    public void unLock(String lock) {
//        jedis.del(lock);
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lock), Collections.singletonList(lock));
    }
}
