package com.training.week07.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.training.week07.base.IBaseDao;
import com.training.week07.base.AbsBaseDao;
import com.training.week07.entity.ProductType;

@Repository("productTypeDao")
public class ProductTypeDaoImpl extends AbsBaseDao implements IBaseDao {

    public ProductTypeDaoImpl() {
        super("product_type_id", "insert into product_type (name, `create_time`, `change_time`) values (?, ?, ?)",
                "SELECT product_type_id, name, `create_time`, `change_time` FROM product_type",
                "update product_type SET name = ?, `create_time` = ?, `change_time` = ? where product_type_id = ?");
    }

    @Override
    protected void insertPreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        ProductType productType = (ProductType) o;

        ps.setString(1, productType.getName());
        ps.setLong(2, productType.getCreateTime());
        ps.setLong(3, productType.getChangeTime());
    }

    @Override
    protected ProductType toObject(ResultSet rs) throws SQLException {
        return new ProductType(rs.getLong("product_type_id"), rs.getString("name"), rs.getLong("create_time"),
                rs.getLong("change_time"));
    }

    @Override
    protected void updatePreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        ProductType productType = (ProductType) o;

        ps.setString(1, productType.getName());
        ps.setLong(2, productType.getCreateTime());
        ps.setLong(3, productType.getChangeTime());

        ps.setLong(4, productType.getProductTypeId());
    }

}
