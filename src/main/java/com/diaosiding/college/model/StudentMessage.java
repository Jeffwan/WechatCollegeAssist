package com.diaosiding.college.model;

import java.io.Serializable;
import java.util.Date;

public class StudentMessage implements Serializable {

	private int id;

	private int studentId;
	
	private String content;
	
	private Date inserTtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getInserTtime() {
		return inserTtime;
	}

	public void setInserTtime(Date inserTtime) {
		this.inserTtime = inserTtime;
	}
	
}
