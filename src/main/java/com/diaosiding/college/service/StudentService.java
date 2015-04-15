package com.diaosiding.college.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diaosiding.college.dao.impl.ClassesDao;
import com.diaosiding.college.dao.impl.ExamDao;
import com.diaosiding.college.dao.impl.StudentDao;
import com.diaosiding.college.dao.impl.StudentMessageDao;
import com.diaosiding.college.model.Classes;
import com.diaosiding.college.model.ExamMark;
import com.diaosiding.college.model.Student;
import com.diaosiding.college.model.StudentMessage;

@Service("studentService")
public class StudentService {

	@Autowired
	private StudentDao studentDao; 
	
	@Autowired
	private ClassesDao classesDao;
	
	@Autowired
	private StudentMessageDao studentMessageDao;
	
	@Autowired
	private ExamDao examDao;
	
	public List<ExamMark> findExamMarkByStudentId(int id ,int limit){
		return examDao.findExamMarkByStudentId(id, limit);
	}
	
	public List<Student> listStudent(int start,int size,Student student){
		return studentDao.findStudent(start,size,student);
	}
	 
	public Student findStudentById(int studentid){
		return studentDao.findStudentById(studentid);
	}
	
	public void addStudentMessage(StudentMessage studentMessage){
		studentMessageDao.addStudentMessage(studentMessage);
	}
	
	public void deleteStudentMessageById(int id){
		studentMessageDao.deleteStudentMessageById(id);
	}
	
	public List<Classes> findAllClasses(){
		return classesDao.findAllClasses();
	}
	
	public List<StudentMessage> listMessageByStudentId(int studentId,int limit){
		return studentMessageDao.findStudentMessageByStudentId(studentId, limit);
	}
	
}
