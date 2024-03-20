package com.MiniCurso.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaSetup {
	public void createTables(Connection connection) {
		try {
		Statement statement = connection.createStatement();
		String createStudentsTable = "CREATE TABLE IF NOT EXISTS students (" +
                "matricula BIGINT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "email VARCHAR(255) NOT NULL," +
                "cpf VARCHAR(14) NOT NULL" +
                ")";
        statement.execute(createStudentsTable);
        System.out.println("Tabela students criada com sucesso.");

        // Criar tabela teachers
        String createTeachersTable = "CREATE TABLE IF NOT EXISTS teachers (" +
                "matricula BIGINT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "email VARCHAR(255) NOT NULL," +
                "cpf VARCHAR(14) NOT NULL" +
                ")";
        statement.execute(createTeachersTable);
        System.out.println("Tabela teachers criada com sucesso.");

        // Criar tabela courses
        String createCoursesTable = "CREATE TABLE IF NOT EXISTS courses (" +
                "id BIGINT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "teacher BIGINT," +
                "course_hours INT NOT NULL," +
                "FOREIGN KEY (teacher) REFERENCES teachers(matricula)" +
                ")";
        statement.execute(createCoursesTable);
        System.out.println("Tabela courses criada com sucesso.");

        // Criar tabela student_courses
        String createStudentCoursesTable = "CREATE TABLE IF NOT EXISTS student_courses (" +
                "student_matricula BIGINT," +
                "course_id BIGINT," +
                "PRIMARY KEY (student_matricula, course_id)," +
                "FOREIGN KEY (student_matricula) REFERENCES students(matricula)," +
                "FOREIGN KEY (course_id) REFERENCES courses(id)" +
                ")";
        statement.execute(createStudentCoursesTable);
        System.out.println("Tabela student_courses criada com sucesso.");

        // Criar tabela teacher_courses
        String createTeacherCoursesTable = "CREATE TABLE IF NOT EXISTS teacher_courses (" +
                "teacher_matricula BIGINT," +
                "course_id BIGINT," +
                "PRIMARY KEY (teacher_matricula, course_id)," +
                "FOREIGN KEY (teacher_matricula) REFERENCES teachers(matricula)," +
                "FOREIGN KEY (course_id) REFERENCES courses(id)" +
                ")";
        statement.execute(createTeacherCoursesTable);
        System.out.println("Tabela teacher_courses criada com sucesso.");

    } catch (SQLException e) {
        System.out.println("Erro ao criar tabelas: " + e.getMessage());
    }
	}
}

