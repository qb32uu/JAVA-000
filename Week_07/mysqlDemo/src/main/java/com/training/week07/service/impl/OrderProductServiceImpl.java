package com.training.week07.service.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.week07.base.AbsBaseService;
import com.training.week07.base.IBaseDao;
import com.training.week07.entity.OrderProduct;
import com.training.week07.service.IOrderProductService;

@Service("orderProductService")
public class OrderProductServiceImpl extends AbsBaseService implements IOrderProductService {

    @Autowired
    private IBaseDao orderProductDao;

    @Transactional
    @Override
    public void randomAdd(int no) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            while (--no >= 0) {
                BigInteger id = orderProductDao.insert(conn, createRandomOrderProduct(30L, 1L, 3, 2));
                System.out.println(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
    }

    public OrderProduct createRandomOrderProduct(Long ordersId, Long skuId, Integer price, Integer no) {
        OrderProduct result = new OrderProduct();
        result.setOrdersId(ordersId);
        result.setSkuId(skuId);
        result.setPrice(price);
        result.setNo(no);
        return result;
    }

    @Override
    public List<OrderProduct> getAll() {
        Connection conn = null;
        try {
            conn = this.getConnection();
            return orderProductDao.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
        return null;
    }
}
