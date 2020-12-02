package com.training.week07.service;

import java.util.List;
import java.util.Map;

import com.training.week07.base.IBaseService;
import com.training.week07.entity.Orders;

/**
 * 商品
 * 
 * @author Billion
 *
 */
public interface IOrdersService extends IBaseService {
    /**
     * 随机增加no个订单
     * 
     * @param no
     */
    public void randomAdd(int no);

    /**
     * 用户（该地址使用者）下单
     * 
     * @param addressId 用户地址
     * @param skuMap    商品数量
     */
    public void order(Long addressId, Map<Long, Integer> skuMap);

    /**
     * 获取全部对象
     * 
     * @return
     */
    public List<Orders> getAll();
}
