package com.MiniCurso.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.MiniCurso.util.ConnectionFactory;
import com.MiniCurso.model.Courses;
import com.MiniCurso.model.Teachers;

public class CourseDAO {
	private Connection connection;
	Courses course = null;
	
	public CourseDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void addCourse(Courses course) {
		String sql = "INSERT INTO students (matricula, name, email, cpf) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, course.getId());
			stmt.setString(2, course.getName());
			stmt.setLong(3, course.getTeacher());
			stmt.setInt(4, course.getCourseHours());
			
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	public Courses getCourse(Long id) {
		course = null;
		String sql = "select * from students where matricula = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					course = new Courses();
					course.setName(rs.getString("name"));
					course.setTeacher(rs.getLong("teacher"));
					course.setCourseHours(rs.getInt("courseHours"));

				}
			}
			try {
				if (course == null) {
					throw new NullPointerException("Curso com id " + id + " não encontrado");
				}
			}catch (NullPointerException e){
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return course;
	}
	
	public Teachers getTeacherOfCourse(Courses course) {
		Teachers teacher = null;
		String sql = "SELECT teacher_id FROM teachers_courses WHERE course_id = ?\"";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, course.getId());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				teacher = new Teachers();
				teacher.setMatricula(rs.getLong("matricula"));
			
		}
		try {
			if (teacher == null) {
				throw new NullPointerException("O curso "+ course.getName() +" ainda não tem professor registrado");
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
		

