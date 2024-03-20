package com.MiniCurso;
import com.MiniCurso.dao.*;
import com.MiniCurso.model.*;
import com.MiniCurso.util.*;

import java.sql.Connection;

public class Main {

	public static void main(String[] args) {
		
		Connection connection = new ConnectionFactory().getConnection();
		
		SchemaSetup init = new SchemaSetup();
		init.createTables(connection);
		
		CourseDAO courseDAO = new CourseDAO();
		
		Courses newCourse = new Courses();
		newCourse.setId(Long.valueOf(1));
        newCourse.setName("Java - Introduction to JDBC");
        newCourse.setCourseHours(40);
        
        //add course
        courseDAO.addCourse(newCourse);
        
      //get course
        Courses searchedCourse = courseDAO.getCourse(Long.valueOf(1));
        
        if (searchedCourse != null) {
        	 System.out.println("Curso encontrado:");
             System.out.println("Nome: " + searchedCourse.getName());
             System.out.println("Carga Horária: " + searchedCourse.getCourseHours());
        }else {
            System.out.println("Curso não encontrado.");
        }
        
        //add teacher
        TeacherDAO teacherDAO = new TeacherDAO();
        
        Teachers newTeacher = new Teachers();
        newTeacher.setMatricula(Long.valueOf(1));
        newTeacher.setCpf("123.456.789-00");
        newTeacher.setName("John Doe");
        newTeacher.setEmail("johndoe@teacher.com");
        
        teacherDAO.addTeacher(newTeacher);
        
        //get teacher
        Teachers searchedTeacher = teacherDAO.getTeacher(Long.valueOf(1));
        
        if (searchedTeacher != null) {
        	 System.out.println("Professor encontrado:");
             System.out.println("Nome: " + searchedTeacher.getName());
             System.out.println("Email: " + searchedTeacher.getEmail());
             System.out.println("Cpf: " + searchedTeacher.getCpf());
        }else {
            System.out.println("Professor não encontrado.");
        }
        
        
        //set teacher as teacher of course
        teacherDAO.teachCourse(newTeacher, newCourse);
        
        
        //get teacher of course
        Long teacherOfCourse = courseDAO.getTeacherOfCourse(newCourse);
        
        if (teacherOfCourse != null) {
        	teacherDAO.getTeacher(teacherOfCourse);        	
        } else {
        	System.out.println("O curso: '"+ newCourse.getName() +"', ainda não possui professor cadastrado");
        }
        
        
      //add student
        StudentDAO studentDAO = new StudentDAO();
        
        Student newStudent = new Student();
        newStudent.setMatricula(Long.valueOf(1));
        newStudent.setCpf("987.654.321-00");
        newStudent.setName("Jane Smith");
        newStudent.setEmail("janesmith@student.com");
        
        studentDAO.addStudent(newStudent);
        
        //get student
        Student searchedStudent = studentDAO.getStudent(Long.valueOf(1));
        
        if (searchedStudent != null) {
        	 System.out.println("Aluno encontrado:");
             System.out.println("Nome: " + searchedStudent.getName());
             System.out.println("Email: " + searchedStudent.getEmail());
             System.out.println("Cpf: " + searchedStudent.getCpf());
        }else {
            System.out.println("Aluno não encontrado.");
        }
        
        //enroll student in course
        studentDAO.enrollStudentInCourse(newStudent, newCourse);
        
        
        // get course of student
        Long courseOfStudent = studentDAO.getCourseOfStudent(newStudent);
        
        if (courseOfStudent != null) {
        	studentDAO.getStudent(courseOfStudent);
        } else {
        	System.out.println("Aluno: '"+ newStudent.getName() +"', ainda não foi matriculado em nenhum curso.");
        }
        
        // update course
        Student updatedStudent = new Student();
        updatedStudent.setMatricula(Long.valueOf(1));
        updatedStudent.setName("João Silva");
        updatedStudent.setEmail("joaosilva@student.com");
        updatedStudent.setCpf("123.456.789-00");
        
        studentDAO.updateStudent(updatedStudent);
        
        Student updatedDataStudent = studentDAO.getStudent(Long.valueOf(1));
        
        if (updatedDataStudent != null) {
        	System.out.println("Aluno atualizado: ");
        	System.out.println("Matrícula: " + updatedDataStudent.getMatricula());
        	System.out.println("Nome: " + updatedDataStudent.getName());
            System.out.println("Email: " + updatedDataStudent.getEmail());
            System.out.println("CPF: " + updatedDataStudent.getCpf());
        }else {
        	System.out.println("Falha ao atualizar aluno.");
        }
        
        //update teacher
        Teachers updatedTeacher = new Teachers();
        updatedTeacher.setMatricula(Long.valueOf(1));
        updatedTeacher.setName("João Silva");
        updatedTeacher.setEmail("joaosilva@teacher.com");
        updatedTeacher.setCpf("123.456.789-00");
        
        teacherDAO.updateTeacher(updatedTeacher);
        
        Teachers updatedDataTeacher = teacherDAO.getTeacher(Long.valueOf(1));
        
        if (updatedDataTeacher != null) {
        	System.out.println("Professor atualizado: ");
        	System.out.println("Matrícula: " + updatedDataStudent.getMatricula());
        	System.out.println("Nome: " + updatedDataStudent.getName());
            System.out.println("Email: " + updatedDataStudent.getEmail());
            System.out.println("CPF: " + updatedDataStudent.getCpf());
        }else {
        	System.out.println("Falha ao atualizar professo.");
        }
        
      //update Course
        Courses updatedCourse = new Courses();
        updatedCourse.setId(Long.valueOf(1));
        updatedCourse.setName("Java - introduction to Enterprise Aplications");
        updatedCourse.setCourseHours(50);
        
        courseDAO.updateCourse(updatedCourse);
        
        Courses updatedDataCourses = courseDAO.getCourse(Long.valueOf(1));
        
        if (updatedDataCourses != null) {
        	System.out.println("Curso atualizado: ");
        	System.out.println("Nome: " + updatedDataCourse.getName());
            System.out.println("Carga horaria: " + updatedDataCourse.getCourseHours());
        }else {
        	System.out.println("Falha ao atualizar curso.");
        }
        
        //delete student
        studentDAO.deleteStudent(updatedStudent.getMatricula());
        
        studentDAO.getStudent(updatedStudent.getMatricula());
        
      //delete teacher
        teacherDAO.deleteTeacher(updatedTeacher.getMatricula());
        
        teacherDAO.getTeacher(updatedTeacher.getMatricula());
        
      //delete course
        courseDAO.deleteCourse(updatedCourse.getId());
        
        courseDAO.getCourse(updatedCourse.getId());
        
        
        //clean database
        
        
        
        
	}

}
