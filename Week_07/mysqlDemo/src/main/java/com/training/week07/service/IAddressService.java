package com.training.week07.service;

import java.util.List;

import com.training.week07.base.IBaseService;
import com.training.week07.entity.Address;

/**
 * 地区
 * 
 * @author Billion
 *
 */
public interface IAddressService extends IBaseService{
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
    public List<Address> getAll();
}
