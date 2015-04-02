package com.diaosiding.college.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Exam implements Serializable {

	private int id;
	
	private int classId;
	
	private String course;
	
	private Date examTime;
	
	private BigDecimal fullMarks;
	
	private BigDecimal average;
	
	private BigDecimal topMark;
	
	private BigDecimal lowestMark;
	
	private String remark;
	
	private List<ExamMark> examMarks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Date getExamTime() {
		return examTime;
	}

	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}

	public BigDecimal getFullMarks() {
		return fullMarks;
	}

	public void setFullMarks(BigDecimal fullMarks) {
		this.fullMarks = fullMarks;
	}

	public BigDecimal getAverage() {
		return average;
	}

	public void setAverage(BigDecimal average) {
		this.average = average;
	}

	public BigDecimal getTopMark() {
		return topMark;
	}

	public void setTopMark(BigDecimal topMark) {
		this.topMark = topMark;
	}

	public BigDecimal getLowestMark() {
		return lowestMark;
	}

	public void setLowestMark(BigDecimal lowestMark) {
		this.lowestMark = lowestMark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ExamMark> getExamMarks() {
		return examMarks;
	}

	public void setExamMarks(List<ExamMark> examMarks) {
		this.examMarks = examMarks;
	}
	
	
	
	
}
