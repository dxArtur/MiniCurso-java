package com.MiniCurso.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/dac@study";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";
	
	public Connection getConnection()  {
		try {
			System.out.println("conecting with database");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

}