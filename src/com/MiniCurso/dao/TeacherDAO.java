package com.MiniCurso.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.MiniCurso.util.ConnectionFactory;
import com.MiniCurso.model.Teachers;

public class TeacherDAO {
	private Connection connection;
	Teachers teacher = null;
	
	public TeacherDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void addTeacher(Teachers teacher) {
		String sql = "INSERT INTO students (matricula, name, email, cpf) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, teacher.getMatricula());
			stmt.setString(2, teacher.getName());
			stmt.setString(3, teacher.getEmail());
			stmt.setString(4, teacher.getCpf());
			
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	public Teachers getTeacher(Long matricula) {
		teacher = null;
		String sql = "select * from students where matricula = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, matricula);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					teacher = new Teachers();
					teacher.setCpf(rs.getString("cpf"));
					teacher.setMatricula(rs.getLong("matricula"));
					teacher.setName(rs.getString("name"));
					teacher.setEmail(rs.getString("email"));

				}
			}
			try {
				if (teacher == null) {
					throw new NullPointerException("Estudante com matricula " + matricula + " não encontrado");
				}
			}catch (NullPointerException e){
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return teacher;
	}
	
}
