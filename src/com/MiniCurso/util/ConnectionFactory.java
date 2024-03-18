package com.MiniCurso.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection()  {
		try {
			System.out.println("conecting with database");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/dac@study", "postgres", "postgres");
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	
	}
}