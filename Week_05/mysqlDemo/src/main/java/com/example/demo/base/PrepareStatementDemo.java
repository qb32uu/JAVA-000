package com.example.demo.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.example.demo.vo.User;

public class PrepareStatementDemo {
    /**
     * 查
     * 
     * @return
     * @throws SQLException
     */
    public List<User> select() throws SQLException {
        List<User> result = new ArrayList<>();

        Connection connection = MysqlUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT id, name, age FROM week05_user");
        ResultSet rs = ps.executeQuery();
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
    public void insert(List<User> dataList) throws SQLException {
        Connection connection = MysqlUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into week05_user (name,age) values(?,?)");
        connection.setAutoCommit(false);
        for (User user : dataList) {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.addBatch();
        }
        ps.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
        MysqlUtils.closeConnection(connection);
    }

    /**
     * 改
     * 
     * @param dataList
     * @throws SQLException
     */
    public void update(List<User> dataList) throws SQLException {
        Connection connection = MysqlUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("update week05_user SET name = ?, age = ? WHERE id = ?");
        connection.setAutoCommit(false);
        for (User user : dataList) {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setInt(3, user.getId());
            ps.addBatch();
        }
        ps.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
        MysqlUtils.closeConnection(connection);
    }

    // 删
    public void delete(List<Integer> dataList) throws SQLException {
        Connection connection = MysqlUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("delete u5 FROM week05_user u5 WHERE id = ?");
        connection.setAutoCommit(false);
        for (Integer id : dataList) {
            ps.setInt(1, id);
            ps.addBatch();
        }
        ps.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
        MysqlUtils.closeConnection(connection);
    }

    public static void main(String[] args) {
        PrepareStatementDemo demo = new PrepareStatementDemo();
        try {
            List<User> dataList;
            List<Integer> idList;

            // 查找
            System.out.println("查找现有数据");
            dataList = demo.select();
            dataList.stream().forEach(e -> {
                System.out.println(e);
            });

            // 新增
            System.out.println("\n新增4人");
            dataList = Arrays.asList(new User("张三", new Random().nextInt(50)), new User("李四", new Random().nextInt(50)),
                    new User("王五", new Random().nextInt(50)), new User("赵六", new Random().nextInt(50)));
            demo.insert(dataList);
            demo.show();

            // 修改
            System.out.println("\n修改年龄");
            dataList = demo.select();
            dataList.stream().forEach(e -> e.setAge(new Random().nextInt(50) + 50));
            demo.update(dataList);
            demo.show();

            // 删除
            System.out.println("\n删除前4人");
            dataList = demo.select();
            idList = dataList.stream().map(e -> e.getId()).collect(Collectors.toList());
            demo.delete(idList);
            demo.show();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
