package com.training.week07.rw;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.week07.DemoApplication;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RwServiceTest {
    @Autowired
    private IRwService rwService;

    @Disabled
    @Test
    public void randomAddTest() {
        log.info("randomAddTest");
        rwService.randomAdd(100);
    }

//  @Disabled
    @Test
    public void getAllTest() {
        log.info("getAllTest");

        System.out.println("getAll第1次读数据量：" + rwService.getAll().size());
        System.out.println("getAll第2次读数据量：" + rwService.getAll().size());
        System.out.println("getAllBySlave第1次读，数据量：" + rwService.getAllBySlave().size());
        System.out.println("getAllBySlave第2次读，数据量：" + rwService.getAllBySlave().size());
        System.out.println("getAllBySlave第3次读，数据量：" + rwService.getAllBySlave().size());
        System.out.println("getAllBySlave第4次读，数据量：" + rwService.getAllBySlave().size());
        System.out.println("getAll第3次读数据量：" + rwService.getAll().size());
        System.out.println("getAllBySlave第5次读，数据量：" + rwService.getAllBySlave().size());
        System.out.println("getAllBySlave第6次读，数据量：" + rwService.getAllBySlave().size());
        log.info("getAllTestEnd");
    }

}
