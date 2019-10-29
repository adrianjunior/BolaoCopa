package br.com.fbd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/bolao";
	private static final String username = "postgres";
	private static final String password = "ufc123";
    
	public Connection getConnection() {
        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}