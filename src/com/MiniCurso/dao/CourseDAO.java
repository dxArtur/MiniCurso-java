package com.MiniCurso.dao;
import java.sql.Connection;
import com.MiniCurso.util.ConnectionFactory;
import com.MiniCurso.model.Courses;

public class CourseDAO {
	private Connection connection;
	Courses course = null;
	
	public CourseDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
}
