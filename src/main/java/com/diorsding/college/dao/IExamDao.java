package com.diorsding.college.dao;

import java.util.List;

import com.diorsding.college.model.Exam;
import com.diorsding.college.model.ExamMark;

public interface IExamDao {

	public List<ExamMark> findExamMarkByStudentId(int studentId, int limit);

	public List<Exam> findExam(int start, int size, Exam exam);

	public void addExam(Exam exam);

	public void addExamMark(ExamMark examMark);

	public Exam findExamById(int id);

}