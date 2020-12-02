package com.training.week07.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IBaseDao {
    public <T, PK> PK insert(Connection conn, T t) throws SQLException;

    public <T> void insert(Connection conn, List<T> list) throws SQLException;

    public <T> List<T> getAll(Connection conn) throws SQLException;

    public <T, PK> T get(Connection conn, PK id) throws SQLException;

    public <T> List<T> getByWhere(Connection conn, String where, Object... params) throws SQLException;

    public <T> void update(Connection conn, T t) throws SQLException;
}
