package com.training.week07.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.training.week07.base.IBaseDao;
import com.training.week07.base.AbsBaseDao;
import com.training.week07.entity.Area;

@Repository("areaDao")
public class AreaDaoImpl extends AbsBaseDao implements IBaseDao {

    public AreaDaoImpl() {
        super("area_id", "insert into area (name, `create_time`, `change_time`) values(?, ?, ?)",
                "SELECT area_id, name, `create_time`, `change_time` FROM area",
                "update area SET name = ?, `create_time` = ?, `change_time` = ? where area_id = ?");
    }

    @Override
    protected void insertPreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Area area = (Area) o;

        ps.setString(1, area.getName());
        ps.setLong(2, area.getCreateTime());
        ps.setLong(3, area.getChangeTime());
    }

    @Override
    protected Area toObject(ResultSet rs) throws SQLException {
        return new Area(rs.getLong("area_id"), rs.getString("name"), rs.getLong("create_time"),
                rs.getLong("change_time"));
    }

    @Override
    protected void updatePreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Area area = (Area) o;

        ps.setString(1, area.getName());
        ps.setLong(2, area.getCreateTime());
        ps.setLong(3, area.getChangeTime());

        ps.setLong(4, area.getAreaId());
    }

}
