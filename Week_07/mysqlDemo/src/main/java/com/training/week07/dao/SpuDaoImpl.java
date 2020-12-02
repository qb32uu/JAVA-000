package com.training.week07.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.training.week07.base.IBaseDao;
import com.training.week07.base.AbsBaseDao;
import com.training.week07.entity.Spu;

@Repository("spuDao")
public class SpuDaoImpl extends AbsBaseDao implements IBaseDao {

    public SpuDaoImpl() {
        super("spu_id",
                "insert into spu (`name`, `product_type_id`, `code`, `product_state`, `create_time`, `change_time`) values(?, ?, ?, ?, ?, ?)",
                "SELECT `spu_id`, `name`, `product_type_id`, `code`, `product_state`, `create_time`, `change_time` FROM spu",
                "update spu SET `name` = ?, `product_type_id` = ?, `code` = ?, `product_state` = ?, `create_time` = ?, `change_time` = ? where spu_id = ?");
    }

    @Override
    protected void insertPreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Spu spu = (Spu) o;

        ps.setString(1, spu.getName());
        ps.setLong(2, spu.getProductTypeId());
        ps.setString(3, spu.getCode());
        ps.setInt(4, spu.getProductState());
        ps.setLong(5, spu.getCreateTime());
        ps.setLong(6, spu.getChangeTime());
    }

    @Override
    protected Spu toObject(ResultSet rs) throws SQLException {
        return new Spu(rs.getLong("spu_id"), rs.getString("name"), rs.getLong("product_type_id"), rs.getString("code"),
                rs.getInt("product_state"), rs.getLong("create_time"), rs.getLong("change_time"));
    }

    @Override
    protected void updatePreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Spu spu = (Spu) o;

        ps.setString(1, spu.getName());
        ps.setLong(2, spu.getProductTypeId());
        ps.setString(3, spu.getCode());
        ps.setInt(4, spu.getProductState());
        ps.setLong(5, spu.getCreateTime());
        ps.setLong(6, spu.getChangeTime());

        ps.setLong(7, spu.getSpuId());
    }

}
