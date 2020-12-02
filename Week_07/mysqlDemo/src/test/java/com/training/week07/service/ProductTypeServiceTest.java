package com.training.week07.service;

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
public class ProductTypeServiceTest {
    @Autowired
    private IProductTypeService productTypeService;

    @Disabled
    @Test
    public void randomAddTest() {
        log.info("randomAddTest");
        productTypeService.randomAdd(10);
    }

//    @Disabled
    @Test
    public void getAllTest() {
        log.info("getAllTest");
        productTypeService.getAll().stream().forEach(System.out::print);
    }

}
