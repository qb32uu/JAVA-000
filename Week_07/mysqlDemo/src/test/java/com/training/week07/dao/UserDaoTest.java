package com.training.week07.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.week07.DemoApplication;
import com.training.week07.base.IBaseDao;
import com.training.week07.entity.User;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDaoTest {
    @Autowired
    private IBaseDao userDao;

    @Autowired
    private DataSource dataSource;

    @Disabled
    @Test
    public void randomAddTest() throws SQLException {
        log.info("randomAddTest");
        Connection conn = dataSource.getConnection();
        User user = userDao.get(conn, 1);
        user.setValidity(2);
        userDao.update(conn, user);

    }
}
