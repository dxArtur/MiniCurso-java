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
        newTeacher.setCpf("12345.6789-00");
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
            System.out.println("professor não encontrado.");
        }
        
        
        //set teacher as teacher of course
        teacherDAO.teachCourse(newTeacher, newCourse);
        
        
        //get teacher of course
        Teachers teacherOfCourse = courseDAO.getTeacherOfCourse(newCourse);
        
        if (teacherOfCourse != null) {
        	System.out.println("nome"+ teacherOfCourse.getName());
        	System.out.println("email"+ teacherOfCourse.getEmail());
        	System.out.println("cpf"+ teacherOfCourse.getCpf());
        } else {
        	System.out.println("Professor não encontrado.");
        }
        
        
	}

}
