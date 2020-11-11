package com.training.week04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.training.week04.vo.BizDataVo;

/**
 * 用线程池启动新线程进行计算
 * 
 * @author Billion
 *
 */
public final class ExecutorDemo {

    /**
     * 线程池通过awaitTermination实现等待
     * 
     * @param data 业务数据
     * @return
     */
    public int awaitTermination(final BizDataVo data) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> data.compute());
        service.shutdown(); // 关闭线程池

        try {
            // 超时未结束，强行结束
            if (service.awaitTermination(200, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            service.shutdownNow();
        }
        return data.getResult();
    }

    /**
     * 线程池submit方式实现等待
     * 
     * @param data 业务数据
     * @return
     */
    public int submit(BizDataVo data) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> future = service.submit(() -> data.compute());
        service.shutdown();

        try {
            return future.get(); // Future的get己实现等待
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
