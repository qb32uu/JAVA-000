package com.training.week07.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.training.week07.base.IBaseDao;
import com.training.week07.base.AbsBaseDao;
import com.training.week07.entity.User;

@Repository("userDao")
public class UserDaoImpl extends AbsBaseDao implements IBaseDao {

    public UserDaoImpl() {
        super("user_id",
                "insert into user (nickname, account, validity, remark, `password`, salt, create_time, change_time) values(?, ?, ?, ?, ?, ?, ?, ?)",
                "SELECT user_id, nickname, account, validity, remark, `password`, salt, create_time, change_time FROM user",
                "update user SET nickname = ?, account = ?, validity = ?, remark = ?, `password` = ?, salt = ?, create_time = ?, change_time = ? where user_id = ?");
    }

    @Override
    protected void insertPreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        User user = (User) o;

        ps.setString(1, user.getNickname());
        ps.setString(2, user.getAccount());
        ps.setInt(3, user.getValidity());
        ps.setString(4, user.getRemark());
        ps.setString(5, user.getPassword());
        ps.setString(6, user.getSalt());
        ps.setLong(7, user.getCreateTime());
        ps.setLong(8, user.getChangeTime());
    }

    @Override
    protected Object toObject(ResultSet rs) throws SQLException {
        return new User(rs.getLong("user_id"), rs.getString("nickname"), rs.getString("account"), rs.getInt("validity"),
                rs.getString("remark"), rs.getString("password"), rs.getString("salt"), rs.getLong("create_time"),
                rs.getLong("change_time"));
    }

    @Override
    protected void updatePreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        User user = (User) o;

        ps.setString(1, user.getNickname());
        ps.setString(2, user.getAccount());
        ps.setInt(3, user.getValidity());
        ps.setString(4, user.getRemark());
        ps.setString(5, user.getPassword());
        ps.setString(6, user.getSalt());
        ps.setLong(7, user.getCreateTime());
        ps.setLong(8, user.getChangeTime());

        ps.setLong(9, user.getUserId());
    }
}
