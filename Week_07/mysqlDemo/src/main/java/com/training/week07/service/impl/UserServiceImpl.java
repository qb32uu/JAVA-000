package com.training.week07.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.week07.base.AbsBaseService;
import com.training.week07.base.IBaseDao;
import com.training.week07.entity.User;
import com.training.week07.service.IUserService;
import com.training.week07.utils.CreateDataUtils;
import com.training.week07.utils.MockData;

@Service("userService")
public class UserServiceImpl extends AbsBaseService implements IUserService {

    @Autowired
    private IBaseDao userDao;

    @Transactional
    @Override
    public void randomAdd(int no) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            while (--no >= 0) {
                userDao.insert(conn, CreateDataUtils.createRandomUser());
            }

            List<User> userList = userDao.getAll(conn);
            MockData.setUserIdList(userList.stream().map(e -> e.getUserId()).collect(Collectors.toList()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
    }

    @Override
    public List<User> getAll() {
        Connection conn = null;
        try {
            conn = this.getConnection();
            return userDao.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(conn);
        }
        return null;
    }
}
