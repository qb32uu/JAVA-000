package com.training.week04.vo;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟业务数据VO
 * 
 * @author Billion
 *
 */
public class BizDataVo {
    private AtomicInteger no = new AtomicInteger(0);
    private boolean allFinished = false;
    private int result = -1; // 模拟通过额外空间保存计算结果

    public static final int SLEEP_TIME = 10;

    /**
     * 业务计算
     */
    public Integer compute() {
        try {

            // 模拟业处理。+SLEEP_TIME*2，部分方法需要等待SLEEP_TIME保证子线程启动，所以需要保证子线程在SLEEP_TIME后还在处理中
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(200) + SLEEP_TIME * 2);
            no.incrementAndGet();
            allFinished = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = no.get();
        return result;
    }

    /**
     * 线程是否完成
     * 
     * @return
     */
    public boolean isAllFinished() {
        return allFinished;
    }

    /**
     * 获取通过额外空间保存的线程处理结果
     * 
     * @return
     */
    public int getResult() {
        return result;
    }
}
