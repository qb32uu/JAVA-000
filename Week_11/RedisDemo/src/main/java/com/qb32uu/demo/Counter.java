package com.qb32uu.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

/**
 * 分布式计数器
 * @author Billion
 *
 */
@Component
public class Counter {
    @Autowired
    private Jedis jedis;

    /**
     * 设置库存
     * 
     * @param key 库存标识
     * @param no
     */
    public void set(String key, int no) {
        jedis.set(key, no + "");
    }

    public String get(String key) {
        return jedis.get(key);
    }

    /**
     * 减库存
     * 
     * @param key       库存标识
     * @param decrement
     * @return true时为成功
     */
    public boolean decr(String key, int decrement) {
        String value = jedis.get(key);
        if (StringUtils.isBlank(value)) {
            return false;
        }

        int no = Integer.parseInt(value);
        if (no >= decrement) {
            // 有库存时才减
            Long count = jedis.decrBy(key, decrement);
            if (count < 0) {
                // 超扣，回退
                jedis.incrBy(key, decrement);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
