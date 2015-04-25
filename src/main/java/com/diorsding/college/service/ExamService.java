package com.diorsding.college.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diorsding.college.dao.IClassesDao;
import com.diorsding.college.dao.IExamDao;
import com.diorsding.college.dao.IStudentDao;
import com.diorsding.college.model.Classes;
import com.diorsding.college.model.Exam;
import com.diorsding.college.model.ExamMark;
import com.diorsding.college.model.Student;

@Service("examService")
public class ExamService {

	@Autowired
	private IStudentDao studentDao;
	
	@Autowired
	private IClassesDao classesDao;
	
	@Autowired
	private IExamDao examDao;
	
	public void addExam(Exam exam) {
		examDao.addExam(exam);
		
		for (ExamMark em : exam.getExamMarks()) {
			em.setExamId(exam.getId());
			examDao.addExamMark(em);
		}
	}
	
	public Exam findExamById(int id) {
		return examDao.findExamById(id);
	}
	
	
	public List<Exam> listExam(int start, int size, Exam exam) {
		return examDao.findExam(start, size, exam);
	}
	
	public List<Student> findStudentByClassesId(int classesId) {
		return studentDao.findStudentByClassesId(classesId);
	}
	
	public Student findStudentById(int studentid){
		return studentDao.findStudentById(studentid);
	}

	public List<Classes> findAllClasses(){
		return classesDao.findAllClasses();
	}
	
}
