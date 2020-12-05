package com.training.week07.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.training.week07.service.IOrdersService;
import com.training.week07.utils.MockData;

//@Log
@Component("consumer")
public class Consumer implements IStressMock {
    @Autowired
    private IOrdersService ordersService;
    private static int SHOPPING_NO = 0;

    private static final int ALL_NO = 10000;// 1_000_000
    private static final int ONCE_NO = ALL_NO / 4 / 10;

    /**
     * 重置次数
     */
    @Override
    public void clean() {
        SHOPPING_NO = 0;
    }

    /**
     * 是否还能购物no次
     * 
     * @param no 计划接下来购物次数
     * @return true为允许
     */
    private static synchronized boolean isShopping(int no) {
        SHOPPING_NO += no;
        if (SHOPPING_NO <= ALL_NO) {
            return true;
        }
        return false;
    }

    /**
     * 购物，直到够100W次才结束
     * 
     * @return
     */
    @Override
    public boolean shopping(String name) {
        while (isShopping(ONCE_NO)) {
            for (int i = 0; i < ONCE_NO; i++) {
                // 模拟用户选好商品与地址后的下单操作
                ordersService.order(MockData.getRandomAddressId(), MockData.getRandomSkuMap());
            }
//            log.info(name + "第" + (++no) + "次出门购物");
        }
        return true;
    }
}
