package com.MiniCurso.model;

public class Courses {
	private Number id;
	private String name;
	private String nameTeacher;
	private Number courseHours;
	//add students list for course
	public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameTeacher() {
		return nameTeacher;
	}
	public void setNameTeacher(String nameTeacher) {
		this.nameTeacher = nameTeacher;
	}
	public Number getCourseHours() {
		return courseHours;
	}
	public void setCourseHours(Number courseHours) {
		this.courseHours = courseHours;
	}
}
