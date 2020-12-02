package com.training.week07.service;

import java.util.List;

import com.training.week07.base.IBaseService;
import com.training.week07.entity.OrderProduct;

/**
 * 商品
 * 
 * @author Billion
 *
 */
public interface IOrderProductService extends IBaseService {
    /**
     * 为每个订单增加no个商品
     * 
     * @param no
     */
    public void randomAdd(int no);

    /**
     * 获取全部对象
     * 
     * @return
     */
    public List<OrderProduct> getAll();
}
