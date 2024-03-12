package com.MiniCurso.dao;
import java.sql.Connection;
import com.MiniCurso.util.ConnectionFactory;
import com.MiniCurso.model.Student;

public class StudentDAO {
	private Connection connection;
	Student student = null;
	
	public StudentDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Student addStudent(Student student) {
		
	}
	
}