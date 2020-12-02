package com.training.week07.base;

import java.sql.Connection;
import java.sql.SQLException;

public interface IBaseService {

    public Connection getConnection() throws SQLException;

    public void closeConnection(Connection conn);
}
