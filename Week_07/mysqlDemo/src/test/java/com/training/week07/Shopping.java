package com.training.week07;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.week07.mock.IStressMock;

//import lombok.extern.java.Log;

//@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Shopping {

    @Autowired
    private IStressMock consumer;
    @Autowired
    private IStressMock writeOnly;

//    @Disabled
    @Test
    public void consumer() {
        for (int threadNo = 1; threadNo <= 10; threadNo++) {
            System.out.println("**" + threadNo + "个线程");
            // 连测5次
            for (int i = 0; i < 5; i++) {
                consumerTest(consumer, threadNo);
            }
        }
    }

    @Disabled
    @Test
    public void writeOnly() {
        for (int threadNo = 1; threadNo <= 10; threadNo++) {
            System.out.println("**" + threadNo + "个线程");
            // 连测5次
            for (int i = 0; i < 5; i++) {
                consumerTest(writeOnly, threadNo);
            }
        }
    }

    private void consumerTest(final IStressMock mock, int threadNo) {
        mock.clean(); // 重置测试次次数

        ExecutorService service = Executors.newSingleThreadExecutor();
        List<Future<Boolean>> futureList = new ArrayList<>();
        // 设置并发线程
        for (int i = 0; i < threadNo; i++) {
            futureList.add(service.submit(() -> mock.shopping("1号")));
        }
        service.shutdown();

        long time = System.currentTimeMillis();
        try {
            for (Future<Boolean> future : futureList) {
                future.get();
            }
            System.out.println(System.currentTimeMillis() - time); //
        } catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }
    }
}
