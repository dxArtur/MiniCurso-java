package com.MiniCurso.dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.MiniCurso.util.ConnectionFactory;
import com.MiniCurso.model.Teachers;
import com.MiniCurso.model.Courses;

public class TeacherDAO {
	private Connection connection;
	Teachers teacher = null;
	
	public TeacherDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void addTeacher(Teachers teacher) {
		String sql = "INSERT INTO teachers (matricula, name, email, cpf) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, teacher.getMatricula());
			stmt.setString(2, teacher.getName());
			stmt.setString(3, teacher.getEmail());
			stmt.setString(4, teacher.getCpf());
			
			int rowsInserted = stmt.executeUpdate(); 
	        if (rowsInserted > 0) {
	            System.out.println("Professor adicionado com sucesso.");
	        } else {
	            System.out.println("Falha ao adicionar professor.");
	        }
			
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	public Teachers getTeacher(Long matricula) {
		teacher = null;
		String sql = "select * from teachers where matricula = ?";
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
					throw new NullPointerException("Professor com matricula " + matricula + " não encontrado");
				}
			}catch (NullPointerException e){
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return teacher;
	}
	

	public void deleteTeacher(Long matricula) {
        String sql = "DELETE FROM teachers WHERE matricula = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public void updateTeacher(Teachers teacher) {
        String sql = "UPDATE teachers SET name = ?, email = ?, cpf = ? WHERE matricula = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getEmail());
            stmt.setString(3, teacher.getCpf());
            stmt.setLong(4, teacher.getMatricula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

	
	public void teachCourse(Teachers teacher, Courses course) {
		String sql = "INSERT INTO teacher_courses (teacher_matricula, course_id) VALUES (?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, teacher.getMatricula());
			stmt.setLong(2, course.getId());
			
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
	            System.out.println("Professor atribuído ao curso com sucesso.");
	        } else {
	            System.out.println("Falha ao atribuir professor ao curso.");
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
}
