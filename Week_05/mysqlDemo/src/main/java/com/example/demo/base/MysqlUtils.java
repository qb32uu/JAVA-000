package com.example.demo.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlUtils {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String dburl = "jdbc:mysql://localhost:3306/java_camp?characterEncoding=utf8&useSSL=true&serverTimezone=UTC";
            String dbuser = "root";
            String dbpwd = "123456";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dburl, dbuser, dbpwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
