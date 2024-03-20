package com.MiniCurso.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.MiniCurso.util.ConnectionFactory;
import com.MiniCurso.model.Courses;
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
			
			stmt.setLong(1, student.getMatricula());
			stmt.setString(2, student.getName());
			stmt.setString(3, student.getEmail());
			stmt.setString(4, student.getCpf());
			
			int rowsInserted = stmt.executeUpdate(); 
	        if (rowsInserted > 0) {
	            System.out.println("Aluno adicionado com sucesso.");
	        } else {
	            System.out.println("Falha ao adicionar aluno.");
	        }
			
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	public Student getStudent(Long matricula) {
		student = null;
		String sql = "select * from students where matricula = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, matricula);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					student = new Student();
					student.setCpf(rs.getString("cpf"));
					student.setMatricula(rs.getLong("matricula"));
					student.setName(rs.getString("name"));
					student.setEmail(rs.getString("email"));

				}
			}
			
			try {
				if (student == null) {
					System.out.println("Aluno com matricula " + matricula + " não encontrado");
				}
			}catch (NullPointerException e){
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return student;
	}
	
	
	public void updateStudent(Student student) {
	    String sql = "UPDATE students SET name = ?, email = ?, cpf = ? WHERE matricula = ?";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        
	        stmt.setString(1, student.getName());
	        stmt.setString(2, student.getEmail());
	        stmt.setString(3, student.getCpf());
	        stmt.setLong(4, student.getMatricula());
	        
	        int rowsUpdated = stmt.executeUpdate(); 
	        if (rowsUpdated > 0) {
	            System.out.println("Aluno atualizado com sucesso.");
	        } else {
	            System.out.println("Falha ao atualizar aluno.");
	        }
	        
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public void deleteStudent(Long matricula) {
		try {
        	
        	String checkSql = "SELECT COUNT(*) AS count FROM student_courses WHERE student_matricula = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setLong(1, matricula);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt("count");
        	
            if (count == 0) {
                String deleteStudentSql = "DELETE FROM students WHERE matricula = ?";
                PreparedStatement deleteStmt = connection.prepareStatement(deleteStudentSql);
                deleteStmt.setLong(1, matricula);
                int rowsDeleted = deleteStmt.executeUpdate();
                
                if (rowsDeleted > 0) {
                    System.out.println("Aluno deletado com sucesso.");
                } else {
                    System.out.println("Falha ao deletar Aluno. Nenhum registro foi deletado.");
                }
            } else {
                String deleteStudentCoursesSql = "DELETE FROM student_courses WHERE student_matricula = ?";
                PreparedStatement deleteStmt = connection.prepareStatement(deleteStudentCoursesSql);
                
                deleteStmt.setLong(1, matricula);
                int rowsDeleted = deleteStmt.executeUpdate();
                
                if (rowsDeleted > 0) {
                    deleteStudent(matricula);
                } else {
                    System.out.println("Falha ao deletar aluno. Nenhum registro foi deletado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public void enrollStudentInCourse(Student student, Courses course) {
		String sql = "INSERT INTO student_courses (student_matricula, course_id) VALUES (?, ?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, student.getMatricula());
			stmt.setLong(2, course.getId());
			
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
	            System.out.println("Aluno matriculado ao curso com sucesso.");
	        } else {
	            System.out.println("Falha ao matricular aluno ao curso.");
	        }
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	

	
	public Long getCourseOfStudent(Student student) {
		Courses course = null;
		String sql = "SELECT course_id FROM student_courses WHERE student_matricula = ? ";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, student.getMatricula());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				course = new Courses();
				course.setId(rs.getLong("course_id"));
			
			}
			try {
				if (course == null) {
					System.out.println("O aluno: '" + student.getName() + "', ainda não se matriculou em nenhum ");
				}
			}catch (NullPointerException e){
					e.printStackTrace();
				}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return course.getId();
	}
	
}