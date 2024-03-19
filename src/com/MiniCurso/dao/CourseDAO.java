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
		String sql = "INSERT INTO courses (id, name, course_hours) VALUES (?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, course.getId());
			stmt.setString(2, course.getName());
			stmt.setInt(3, course.getCourseHours());
			
			int rowsInserted = stmt.executeUpdate(); 
	        if (rowsInserted > 0) {
	            System.out.println("Curso adicionado com sucesso.");
	        } else {
	            System.out.println("Falha ao adicionar curso.");
	        }
			
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	public Courses getCourse(Long id) {
		course = null;
		String sql = "select * from courses where id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					course = new Courses();
					course.setName(rs.getString("name"));
					course.setCourseHours(rs.getInt("course_hours"));

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
	
	public void updateCourse(Courses course) {
	    String sql = "UPDATE courses SET name = ?, course_hours = ? WHERE id = ?";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setString(1, course.getName());
	        stmt.setInt(2, course.getCourseHours());
	        stmt.setLong(3, course.getId());
	        
	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Curso atualizado com sucesso.");
	        } else {
	            System.out.println("Falha ao atualizar curso. Nenhum curso foi modificado.");
	        }
	        
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public void deleteCourse(Long id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	
	public Long getTeacherOfCourse(Courses course) {
		Teachers teacher = null;
		String sql = "SELECT teacher_matricula FROM teacher_courses WHERE course_id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, course.getId());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				teacher = new Teachers();
				teacher.setMatricula(rs.getLong("teacher_matricula"));
			
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
		
		return teacher.getMatricula();
		}
}
		

