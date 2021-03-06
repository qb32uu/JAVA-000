package com.training.week07.base;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.week07.datasource.DynamicDataSource;

public abstract class AbsBaseService implements IBaseService {

    @Autowired
    private DataSource masterSource;
    @Autowired
    private DataSource slaveSource;
    @Autowired
    private DataSource dataSource;

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
//        return masterSource.getConnection(); // hikari 写入订单测试用
//      return JdbcMysqlUtils.getConnection(); //jdbc 写入订单测试用

        return dataSource.getConnection(); // 动
    }

}
