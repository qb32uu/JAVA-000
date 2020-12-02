package com.training.week07.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.training.week07.base.IBaseDao;
import com.training.week07.base.AbsBaseDao;
import com.training.week07.entity.Sku;

@Repository("skuDao")
public class SkuDaoImpl extends AbsBaseDao implements IBaseDao {

    public SkuDaoImpl() {
        super("sku_id",
                "insert into sku (`name`, `code`, `spu_id`, `price`, `no`, `stock_state`, `create_time`, `change_time`) values(?, ?, ?, ?, ?, ?, ?, ?)",
                "SELECT `sku_id`, `name`, `code`, `spu_id`, `price`, `no`, `stock_state`, `create_time`, `change_time` FROM sku",
                "update sku SET `name` = ?, `code` = ?, `spu_id` = ?, `price` = ?, `no` = ?, `stock_state` = ?, `create_time` = ?, `change_time` = ? where sku_id = ?");
    }

    @Override
    protected void insertPreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Sku sku = (Sku) o;

        ps.setString(1, sku.getName());
        ps.setString(2, sku.getCode());
        ps.setLong(3, sku.getSpuId());
        ps.setInt(4, sku.getPrice());
        ps.setInt(5, sku.getNo());
        ps.setInt(6, sku.getStockState());
        ps.setLong(7, sku.getCreateTime());
        ps.setLong(8, sku.getChangeTime());
    }

    @Override
    protected Sku toObject(ResultSet rs) throws SQLException {
        return new Sku(rs.getLong("sku_id"), rs.getString("name"), rs.getString("code"), rs.getLong("spu_id"),
                rs.getInt("price"), rs.getInt("no"), rs.getInt("stock_state"), rs.getLong("create_time"),
                rs.getLong("change_time"));
    }

    @Override
    protected void updatePreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Sku sku = (Sku) o;

        ps.setString(1, sku.getName());
        ps.setString(2, sku.getCode());
        ps.setLong(3, sku.getSpuId());
        ps.setInt(4, sku.getPrice());
        ps.setInt(5, sku.getNo());
        ps.setInt(6, sku.getStockState());
        ps.setLong(7, sku.getCreateTime());
        ps.setLong(8, sku.getChangeTime());

        ps.setLong(9, sku.getSkuId());
    }

}
