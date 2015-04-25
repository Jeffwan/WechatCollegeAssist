package com.diorsding.college.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExamMark implements Serializable, Comparable<ExamMark>{

	private int examId;
	
	private int classId;
	
	private int studentId;
	
	private String studentName;
	
	private Date examTime;
	
	private BigDecimal mark;
	
	private int rank;
	
	private String remark;
	
	private Exam exam;

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Date getExamTime() {
		return examTime;
	}

	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}

	public BigDecimal getMark() {
		return mark;
	}

	public void setMark(BigDecimal mark) {
		this.mark = mark;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	
	
	@Override
	public String toString() {

		return "ExamMark [examid=" + examId + ", classid=" + classId
				+ ", studentid=" + studentId + ", examtime=" + examTime
				+ ", mark=" + mark + ", rank=" + rank + ", remark=" + remark
				+ ", exam=" + exam + "]";
	}

	public int compareTo(ExamMark o) {
		return this.mark.compareTo(o.mark);
	}
	
	
	
}
