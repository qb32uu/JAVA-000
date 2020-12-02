package com.training.week07.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.training.week07.base.IBaseDao;
import com.training.week07.base.AbsBaseDao;
import com.training.week07.entity.OrderProduct;

@Repository("orderProductDao")
public class OrderProductDaoImpl extends AbsBaseDao implements IBaseDao {

    public OrderProductDaoImpl() {
        super("order_product_id",
                "insert into order_product (`orders_id`,  `sku_id`,  `price`,  `no`) values(?, ?, ?, ?)",
                "SELECT `order_product_id`,  `orders_id`,  `sku_id`,  `price`, `no` FROM order_product",
                "update order_product SET `orders_id` = ?,  `sku_id` = ?,  `price` = ?,  `no` = ? where order_product_id = ?");
    }

    @Override
    protected void insertPreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        OrderProduct orderProduct = (OrderProduct) o;

        ps.setLong(1, orderProduct.getOrdersId());
        ps.setLong(2, orderProduct.getSkuId());
        ps.setInt(3, orderProduct.getPrice());
        ps.setInt(4, orderProduct.getNo());
    }

    @Override
    protected OrderProduct toObject(ResultSet rs) throws SQLException {
        return new OrderProduct(rs.getLong("order_product_id"), rs.getLong("orders_id"), rs.getLong("sku_id"),
                rs.getInt("price"), rs.getInt("no"));
    }

    @Override
    protected void updatePreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        OrderProduct orderProduct = (OrderProduct) o;

        ps.setLong(1, orderProduct.getOrdersId());
        ps.setLong(2, orderProduct.getSkuId());
        ps.setInt(3, orderProduct.getPrice());
        ps.setInt(4, orderProduct.getNo());

        ps.setLong(5, orderProduct.getOrderProductId());
    }

}
