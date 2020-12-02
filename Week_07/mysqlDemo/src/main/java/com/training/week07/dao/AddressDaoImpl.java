package com.training.week07.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.training.week07.base.IBaseDao;
import com.training.week07.base.AbsBaseDao;
import com.training.week07.entity.Address;

@Repository("addressDao")
public class AddressDaoImpl extends AbsBaseDao implements IBaseDao {

    public AddressDaoImpl() {
        super("address_id",
                "insert into address (`area_id`, `detailed_address`, `recipient`, `phone`, `user_id`, `create_time`, `change_time`) values(?, ?, ?, ?, ?, ?, ?)",
                "SELECT `address_id`, `area_id`, `detailed_address`, `recipient`, `phone`, `user_id`, `create_time`, `change_time` FROM address",
                "update address SET `area_id` = ?, `detailed_address` = ?, `recipient` = ?, `phone` = ?, `user_id` = ?, `create_time` = ?, `change_time` = ? where address_id = ?");
    }

    @Override
    protected void insertPreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Address address = (Address) o;

        ps.setLong(1, address.getAreaId());
        ps.setString(2, address.getDetailedAddress());
        ps.setString(3, address.getRecipient());
        ps.setString(4, address.getPhone());
        ps.setLong(5, address.getUserId());
        ps.setLong(6, address.getCreateTime());
        ps.setLong(7, address.getChangeTime());
    }

    @Override
    protected Address toObject(ResultSet rs) throws SQLException {
        return new Address(rs.getLong("address_id"), rs.getLong("area_id"), rs.getString("detailed_address"),
                rs.getString("recipient"), rs.getString("phone"), rs.getLong("user_id"), rs.getLong("create_time"),
                rs.getLong("change_time"));
    }

    @Override
    protected void updatePreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Address address = (Address) o;

        ps.setLong(1, address.getAreaId());
        ps.setString(2, address.getDetailedAddress());
        ps.setString(3, address.getRecipient());
        ps.setString(4, address.getPhone());
        ps.setLong(5, address.getUserId());
        ps.setLong(6, address.getCreateTime());
        ps.setLong(7, address.getChangeTime());

        ps.setLong(8, address.getAddressId());
    }

}
