package com.example.demo.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.MysqlUtils;
import com.example.demo.vo.User;

@RestController
public class HikariDemo {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        StringBuilder result = new StringBuilder();
        try {
            List<User> dataList;
            List<Integer> idList;

            // 查找
            result.append("查找现有数据");
            dataList = select();
            result.append(show(dataList));
            result.append("<br />");

            // 新增
            result.append("<br />新增4人");
            dataList = Arrays.asList(new User("张三", new Random().nextInt(50)), new User("李四", new Random().nextInt(50)),
                    new User("王五", new Random().nextInt(50)), new User("赵六", new Random().nextInt(50)));
            insert(dataList);
            result.append(show());

            // 修改
            result.append("<br />修改年龄");
            dataList = select();
            dataList.stream().forEach(e -> e.setAge(new Random().nextInt(50) + 50));
            update(dataList);
            result.append(show());

            // 删除
            result.append("<br />删除前4人");
            dataList = select();
            idList = dataList.stream().map(e -> e.getId()).collect(Collectors.toList());
            delete(idList);
            result.append(show());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result.toString();
    }

    /**
     * 查
     * 
     * @return
     * @throws SQLException
     */
    private List<User> select() throws SQLException {
        List<User> result = new ArrayList<>();

        Connection connection = dataSource.getConnection();
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

    private String show() throws SQLException {
        return show(select());
    }

    private String show(List<User> list) throws SQLException {
        StringBuilder result = new StringBuilder();
        list.stream().forEach(e -> {
            result.append(e.toString());
            result.append("<br />");
        });
        return result.toString();
    }

    /**
     * 增
     * 
     * @param dataList
     * @throws SQLException
     */
    private void insert(List<User> dataList) throws SQLException {
        Connection connection = dataSource.getConnection();
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
    private void update(List<User> dataList) throws SQLException {
        Connection connection = dataSource.getConnection();
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
    private void delete(List<Integer> dataList) throws SQLException {
        Connection connection = dataSource.getConnection();
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

}
