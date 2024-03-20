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
					System.out.println("Professor com matricula " + matricula + " não encontrado");
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
        try {
        	
        	String checkSql = "SELECT COUNT(*) AS count FROM teacher_courses WHERE teacher_matricula = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setLong(1, matricula);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt("count");
        	
            if (count == 0) {
                String deleteTeacherSql = "DELETE FROM teachers WHERE matricula = ?";
                PreparedStatement deleteStmt = connection.prepareStatement(deleteTeacherSql);
                deleteStmt.setLong(1, matricula);
                int rowsDeleted = deleteStmt.executeUpdate();
                
                if (rowsDeleted > 0) {
                    System.out.println("Professor deletado com sucesso.");
                } else {
                    System.out.println("Falha ao deletar professor. Nenhum registro foi deletado.");
                }
            } else {
                String deleteTeacherCoursesSql = "DELETE FROM teacher_courses WHERE teacher_matricula = ?";
                PreparedStatement deleteStmt = connection.prepareStatement(deleteTeacherCoursesSql);
                
                deleteStmt.setLong(1, matricula);
                int rowsDeleted = deleteStmt.executeUpdate();
                
                if (rowsDeleted > 0) {
                    deleteTeacher(matricula);
                } else {
                    System.out.println("Falha ao deletar professor. Nenhum registro foi deletado.");
                }
            }
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
