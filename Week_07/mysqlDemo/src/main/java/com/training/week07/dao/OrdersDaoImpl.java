package com.training.week07.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.training.week07.base.IBaseDao;
import com.training.week07.base.AbsBaseDao;
import com.training.week07.entity.Orders;

@Repository("ordersDao")
public class OrdersDaoImpl extends AbsBaseDao implements IBaseDao {

    public OrdersDaoImpl() {
        super("orders_id",
                "insert into orders (`area_id`, `detailed_address`, `recipient`, `phone`, `user_id`, `order_state`, `amount_total`, `create_time`, `pay_time`) values(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                "SELECT `orders_id`, `area_id`, `detailed_address`, `recipient`, `phone`, `user_id`, `order_state`, `amount_total`, `create_time`, `pay_time` FROM orders",
                "update orders SET `area_id` = ?, `detailed_address` = ?, `recipient` = ?, `phone` = ?, `user_id` = ?, `order_state` = ?, `amount_total` = ?, `create_time` = ?, `pay_time` = ? where `orders_id` = ?");
    }

    @Override
    protected void insertPreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Orders orders = (Orders) o;

        ps.setLong(1, orders.getAreaId());
        ps.setString(2, orders.getDetailedAddress());
        ps.setString(3, orders.getRecipient());
        ps.setString(4, orders.getPhone());
        ps.setLong(5, orders.getUserId());
        ps.setInt(6, orders.getOrderState());
        ps.setInt(7, orders.getAmountTotal());
        ps.setLong(8, orders.getCreateTime());
        ps.setLong(9, orders.getPayTime());
    }

    @Override
    protected Orders toObject(ResultSet rs) throws SQLException {
        return new Orders(rs.getLong("orders_id"), rs.getLong("area_id"), rs.getString("detailed_address"),
                rs.getString("recipient"), rs.getString("phone"), rs.getLong("user_id"), rs.getInt("order_state"),
                rs.getInt("amount_total"), rs.getLong("create_time"), rs.getLong("pay_time"));
    }

    @Override
    protected void updatePreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Orders orders = (Orders) o;

        ps.setLong(1, orders.getAreaId());
        ps.setString(2, orders.getDetailedAddress());
        ps.setString(3, orders.getRecipient());
        ps.setString(4, orders.getPhone());
        ps.setLong(5, orders.getUserId());
        ps.setInt(6, orders.getOrderState());
        ps.setInt(7, orders.getAmountTotal());
        ps.setLong(8, orders.getCreateTime());
        ps.setLong(9, orders.getPayTime());

        ps.setLong(10, orders.getOrdersId());
    }

}
