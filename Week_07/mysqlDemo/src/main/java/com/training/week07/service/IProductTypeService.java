package com.training.week07.service;

import java.util.List;

import com.training.week07.base.IBaseService;
import com.training.week07.entity.ProductType;

/**
 * 地区
 * 
 * @author Billion
 *
 */
public interface IProductTypeService extends IBaseService {
    /**
     * 随机增加no个对象
     * 
     * @param no
     */
    public void randomAdd(int no);

    /**
     * 获取全部对象
     * 
     * @return
     */
    public List<ProductType> getAll();
}
