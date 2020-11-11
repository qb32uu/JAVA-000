package com.training.week04;

import org.junit.Assert;
import org.junit.Test;

import com.training.week04.vo.BizDataVo;

public class Week04Test {
    private ExecutorDemo executor = new ExecutorDemo();
    private ThreadDemo thread = new ThreadDemo();

    @Test
    public void testRun() {

        Assert.assertEquals("通过额外空间记录状态实现等待", thread.sleep(new BizDataVo()), 1);
        Assert.assertEquals("通过join实现等待", thread.join(new BizDataVo()), 1);

        Assert.assertEquals("通过synchronized锁实现等待", thread.syn(new BizDataVo()), 1);
        Assert.assertEquals("通过自旋锁实现等待", thread.cas(new BizDataVo()), 1);
        Assert.assertEquals("通过lock实现等待", thread.lock(new BizDataVo()), 1);
        Assert.assertEquals("通过readWriteLock实现等待", thread.readWriteLock(new BizDataVo()), 1);
        Assert.assertEquals("通过countDownLatch实现等待", thread.countDownLatch(new BizDataVo()), 1);

        Assert.assertEquals("主线程wait实现等待", thread.wait(new BizDataVo()), 1);
        Assert.assertEquals("主线程Condition.await实现等待", thread.condition(new BizDataVo()), 1);
        Assert.assertEquals("主线程子线程相互等共同完成", thread.cyclicBarrier(new BizDataVo()), 1);

        Assert.assertEquals("线程池通过awaitTermination实现等待", executor.awaitTermination(new BizDataVo()), 1);
        Assert.assertEquals("线程池submit方式实现等待", executor.submit(new BizDataVo()), 1);
    }
}
