package com.MiniCurso;
import com.MiniCurso.dao.*;
import com.MiniCurso.model.*;

public class Main {

	public static void main(String[] args) {
		
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
        
        
	}

}
