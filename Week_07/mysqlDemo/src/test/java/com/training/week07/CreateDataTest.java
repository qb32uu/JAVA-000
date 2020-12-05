package com.training.week07;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.week07.service.IAddressService;
import com.training.week07.service.IAreaService;
import com.training.week07.service.IProductTypeService;
import com.training.week07.service.ISkuService;
import com.training.week07.service.ISpuService;
import com.training.week07.service.IUserService;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateDataTest {
    @Autowired
    private IAreaService areaService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private IProductTypeService productTypeService;
    @Autowired
    private ISpuService spuService;
    @Autowired
    private ISkuService skuService;

    @Test
    public void createBaseDatat() {
        log.info("添加100个区域");
        areaService.randomAdd(100);

        log.info("添加1000个用户");
        userService.randomAdd(1000);

        log.info("每个用户最多添加5个地址（最少1个地址）");
        addressService.randomAdd(5);

        log.info("添加100个商品类型");
        productTypeService.randomAdd(100);

        log.info("添加10000个商品");
        spuService.randomAdd(10000);

        log.info("每个商品最多添加5个SKU(最少1个)");
        skuService.randomAdd(5);

        log.info("成功添加基础数据");
    }
}
