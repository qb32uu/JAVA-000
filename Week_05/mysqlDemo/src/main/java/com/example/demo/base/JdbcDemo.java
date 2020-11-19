package com.example.demo.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.demo.vo.User;

public class JdbcDemo {

    /**
     * 查
     * 
     * @return
     * @throws SQLException
     */
    public List<User> select() throws SQLException {
        List<User> result = new ArrayList<>();

        Connection connection = MysqlUtils.getConnection();

        ResultSet rs = connection.createStatement().executeQuery("SELECT id, name, age FROM week05_user");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            User user = new User(id, name, age);
            result.add(user);
        }

        MysqlUtils.closeConnection(connection);
        return result;
    }

    public void show() throws SQLException {
        select().stream().forEach(e -> {
            System.out.println(e);
        });
    }

    /**
     * 增
     * 
     * @param dataList
     * @throws SQLException
     */
    public void insert(User user) throws SQLException {
        Connection connection = MysqlUtils.getConnection();

        StringBuilder sb = new StringBuilder();
        sb.append("insert into week05_user (name,age) values('");
        sb.append(user.getName());
        sb.append("',");
        sb.append(user.getAge());
        sb.append(")");
        connection.createStatement().execute(sb.toString());

        MysqlUtils.closeConnection(connection);
    }

    /**
     * 改
     * 
     * @param dataList
     * @throws SQLException
     */
    public void jdbcUpdate(User user) throws SQLException {
        Connection connection = MysqlUtils.getConnection();

        StringBuilder sb = new StringBuilder();
        sb.append("update week05_user SET name = '");
        sb.append(user.getName());
        sb.append("', age = ");
        sb.append(user.getAge());
        sb.append(" WHERE id = ");
        sb.append(user.getId());
        connection.createStatement().execute(sb.toString());

        MysqlUtils.closeConnection(connection);
    }

    // 删
    public void jdbcDelete(Integer id) throws SQLException {
        Connection connection = MysqlUtils.getConnection();

        StringBuilder sb = new StringBuilder();
        sb.append("delete u5 FROM week05_user u5 WHERE id = ");
        sb.append(id);
        connection.createStatement().execute(sb.toString());

        MysqlUtils.closeConnection(connection);
    }

    public static void main(String[] args) {
        JdbcDemo demo = new JdbcDemo();
        try {
            List<User> dataList;
            User user;

            // 查找
            System.out.println("查找现有数据");
            dataList = demo.select();
            dataList.stream().forEach(e -> {
                System.out.println(e);
            });

            // 新增
            System.out.println("\n新增1人");
            demo.insert(new User("张三", new Random().nextInt(50)));
            demo.show();

            // 修改
            System.out.println("\n修改首个人员年龄");
            dataList = demo.select();
            user = dataList.get(0);
            user.setAge(new Random().nextInt(50) + 50);
            demo.jdbcUpdate(user);
            demo.show();

            // 删除
            System.out.println("\n删除首个人员");
            dataList = demo.select();
            demo.jdbcDelete(dataList.get(0).getId());
            demo.show();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
