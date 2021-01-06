package com.qb32uu.demo;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AllTest {
    @Autowired
    private RedisLock redisLock;

    @Autowired
    private Counter counter;

    @Test
    void testLock() {
        String lock = "lock";
        int secondsToExpire = 2;
        boolean isLock;

        // 锁测试
        isLock = redisLock.lock(lock, secondsToExpire);
        Assert.assertEquals("首次拿锁", isLock, true);
        isLock = redisLock.lock(lock, secondsToExpire);
        Assert.assertEquals("第二次拿锁", isLock, false);
        isLock = redisLock.lock(lock, secondsToExpire);
        Assert.assertEquals("第三次拿锁", isLock, false);

        // 超时测试
        try {
            TimeUnit.SECONDS.sleep(secondsToExpire);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isLock = redisLock.lock(lock, secondsToExpire);
        Assert.assertEquals("超时后拿锁", isLock, true);
        isLock = redisLock.lock(lock, secondsToExpire);
        Assert.assertEquals("第二次拿锁", isLock, false);
        isLock = redisLock.lock(lock, secondsToExpire);
        Assert.assertEquals("第三次拿锁", isLock, false);

        // 解锁测试
        redisLock.unLock(lock);
        isLock = redisLock.lock(lock, secondsToExpire);
        Assert.assertEquals("解锁后拿锁", isLock, true);
        isLock = redisLock.lock(lock, secondsToExpire);
        Assert.assertEquals("第二次拿锁", isLock, false);
        isLock = redisLock.lock(lock, secondsToExpire);
        Assert.assertEquals("第三次拿锁", isLock, false);

        redisLock.unLock(lock);// 清理锁
    }

    @Test
    public void testDecr() {
        final String key = "key";

        counter.set(key, 100);
        int no = 0;
        for (int i = 1; i < 100; ++i) {
            if (counter.decr(key, i)) {
                ++no;
            }
        }

        Assert.assertEquals("购买次数", no, 13);
        Assert.assertEquals("销量", Integer.parseInt(counter.get(key)), (100 - 91));// 1+...+13=91

    }

}
