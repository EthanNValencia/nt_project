package com.nephew.faqs.services;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

@Service
public class ConnectionService {
    private static final Logger logger = Logger.getLogger(ConnectionService.class.getName());

    public Connection getConnection() {
        String url = "jdbc:postgresql://127.0.0.1:5432/nt_db";
        String user = "nt_user";
        String password = "password";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.warning("The connection was unsuccessful.");
            e.printStackTrace();
        }
        if(connection != null) {
            return connection;
        }
        return null;
    }
}
