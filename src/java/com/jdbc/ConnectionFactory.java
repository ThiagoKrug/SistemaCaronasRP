package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ConnectionFactory {

    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/sistema_caronas?allowMultiQueries=true&useUnicode=true&characterEncoding=utf8", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }
}
