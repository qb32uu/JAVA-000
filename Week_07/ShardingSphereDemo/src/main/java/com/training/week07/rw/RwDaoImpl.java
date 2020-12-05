package com.training.week07.rw;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.training.week07.base.AbsBaseDao;
import com.training.week07.base.IBaseDao;

@Repository("rwDao")
public class RwDaoImpl extends AbsBaseDao implements IBaseDao {

    public RwDaoImpl() {
        super("rw_id", "insert into rw (name, `create_time`, `change_time`) values(?, ?, ?)",
                "SELECT rw_id, name, `create_time`, `change_time` FROM rw",
                "update rw SET name = ?, `create_time` = ?, `change_time` = ? where rw_id = ?");
    }

    @Override
    protected void insertPreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Rw rw = (Rw) o;

        ps.setString(1, rw.getName());
        ps.setLong(2, rw.getCreateTime());
        ps.setLong(3, rw.getChangeTime());
    }

    @Override
    protected Rw toObject(ResultSet rs) throws SQLException {
        return new Rw(rs.getLong("rw_id"), rs.getString("name"), rs.getLong("create_time"),
                rs.getLong("change_time"));
    }

    @Override
    protected void updatePreparedStatement(Object o, PreparedStatement ps) throws SQLException {
        Rw rw = (Rw) o;

        ps.setString(1, rw.getName());
        ps.setLong(2, rw.getCreateTime());
        ps.setLong(3, rw.getChangeTime());

        ps.setLong(4, rw.getRwId());
    }

}
