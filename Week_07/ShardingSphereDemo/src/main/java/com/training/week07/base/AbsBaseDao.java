package com.training.week07.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbsBaseDao implements IBaseDao {

    private String idNmae;
    private String insertSql;
    private String selectAllSql;
    private String updateSql;

    public AbsBaseDao(String idNmae, String insertSql, String selectAllSql, String updateSql) {
        super();
        this.idNmae = idNmae;
        this.insertSql = insertSql;
        this.selectAllSql = selectAllSql;
        this.updateSql = updateSql;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T, PK> PK insert(Connection conn, T t) throws SQLException {
        PK result = null;
        PreparedStatement ps = conn.prepareStatement(this.insertSql, Statement.RETURN_GENERATED_KEYS);
        insertPreparedStatement(t, ps);
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            result = (PK) rs.getObject(1);
        }
        return result;
    }

    protected abstract void insertPreparedStatement(Object o, PreparedStatement ps) throws SQLException;

    @Override
    public <T> void insert(Connection conn, List<T> list) throws SQLException {
        boolean isNotTransaction = conn.getAutoCommit();// 不在大事务中
        try {
            if (isNotTransaction) {
                // 不在大事务中，开启动事务
                conn.setAutoCommit(false);
            }
            PreparedStatement ps = conn.prepareStatement(this.insertSql);
            for (T t : list) {
                insertPreparedStatement(t, ps);// 拼装参数
                ps.addBatch();
            }
            ps.executeBatch();

            if (isNotTransaction) {
                // 不在大事务中，结束事务
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            if (isNotTransaction) {
                // 不在大事务中，回滚
                e.printStackTrace();
                try {
                    if (conn != null && !conn.isClosed()) {
                        // 事务回滚
                        conn.rollback();
                        conn.setAutoCommit(true);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                // 大事务中，向上抛异常
                throw new SQLException(e);
            }
        }
        // 由service决定如何关闭conn

    }

    /**
     * 转对象
     * 
     * @param rs
     * @return
     * @throws SQLException
     */
    protected abstract Object toObject(ResultSet rs) throws SQLException;

    @Override
    public <T> List<T> getAll(Connection conn) throws SQLException {
        List<T> result = this.getByWhere(conn, "");
        return result;
    }

    @Override
    public <T, PK> T get(Connection conn, PK id) throws SQLException {
        List<T> list = this.getByWhere(conn, "WHERE " + this.idNmae + " = ?", id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getByWhere(Connection conn, String where, Object... params) throws SQLException {
        List<T> result = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement(this.selectAllSql + " " + where);
        if (params != null)
            for (int i = 0; i < params.length; i++) {
                Object o = params[i];
                ps.setObject(i + 1, o);
            }
        ps.addBatch();

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            result.add((T) toObject(rs));
        }
        return result;
    }

    protected abstract void updatePreparedStatement(Object o, PreparedStatement ps) throws SQLException;

    @Override
    public <T> void update(Connection conn, T t) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(this.updateSql);
        updatePreparedStatement(t, ps);
        ps.executeUpdate();
    }

}
