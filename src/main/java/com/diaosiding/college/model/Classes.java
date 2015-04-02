package com.diaosiding.college.model;

import java.io.Serializable;

public class Classes implements Serializable{

	private int id;
	
	private String name;
	
	private String headTeacher;
	
	private int studentCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadTeacher() {
		return headTeacher;
	}

	public void setHeadTeacher(String headTeacher) {
		this.headTeacher = headTeacher;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}
	
	
	
}
