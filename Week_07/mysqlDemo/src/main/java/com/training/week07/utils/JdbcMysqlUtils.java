package com.training.week07.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcMysqlUtils {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String dburl = "jdbc:mysql://localhost:3306/java_camp?characterEncoding=utf8&useSSL=true&serverTimezone=UTC";
            String dbuser = "root";
            String dbpwd = "123456";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dburl, dbuser, dbpwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
