package com.training.week07.service;

import java.util.List;

import com.training.week07.base.IBaseService;
import com.training.week07.entity.Sku;

/**
 * 商品
 * 
 * @author Billion
 *
 */
public interface ISkuService extends IBaseService {
    /**
     * 为每个用户随机增加no个地址
     * 
     * @param no
     */
    public void randomAdd(int no);

    /**
     * 获取全部对象
     * 
     * @return
     */
    public List<Sku> getAll();
}
