package com.MiniCurso.model;

public class Courses {
	private Long id;
	private String name;
	private Integer courseHours;
	//add students list for course
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCourseHours() {
		return courseHours;
	}
	public void setCourseHours(Integer courseHours) {
		this.courseHours = courseHours;
	}
}
