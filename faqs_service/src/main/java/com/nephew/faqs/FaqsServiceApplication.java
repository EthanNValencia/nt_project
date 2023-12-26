package com.nephew.faqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.*;

@SpringBootApplication
public class FaqsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaqsServiceApplication.class, args);
	}

	@Bean
	public Connection connection() throws SQLException {
		String url = "jdbc:postgresql://127.0.0.1:5432/nt_db";
		String user = "nt_user";
		String password = "password";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Query execution failure.");
			e.printStackTrace();
		}
		if(connection != null) {
			return connection;
		}
		throw new SQLException("Failed to connect to the database.");
	}
}
