package com.MiniCurso.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.MiniCurso.util.ConnectionFactory;


import com.MiniCurso.model.Student;

public class StudentDAO {
	private Connection connection;
	Student student = null;
	
	public StudentDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void addStudent(Student student) {
		String sql = "INSERT INTO students (matricula, name, email, cpf) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, student.getMatricula());
			stmt.setString(2, student.getName());
			stmt.setString(3, student.getEmail());
			stmt.setString(4, student.getCpf());
			
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	public Student getStudent(String matricula) {
		student = null;
		String sql = "select * from students where matricula = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, matricula);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					student = new Student();
					student.setCpf(rs.getString("cpf"));
					student.setMatricula(rs.getString("matricula"));
					student.setName(rs.getString("name"));
					student.setEmail(rs.getString("email"));

				}
			}
			
			try {
				if (student == null) {
					throw new NullPointerException("usuário com id " + id + " não encontrado");
				}
			}catch (NullPointerException e){
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return student;
	}
	
	public void enrollCourse() {
		
	}
	
}