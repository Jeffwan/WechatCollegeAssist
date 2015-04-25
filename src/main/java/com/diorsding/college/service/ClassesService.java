package com.diorsding.college.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diorsding.college.dao.IClassesDao;
import com.diorsding.college.dao.IClassesNewsDao;
import com.diorsding.college.dao.IStudentDao;
import com.diorsding.college.model.Classes;
import com.diorsding.college.model.ClassesNews;
import com.diorsding.college.model.Student;

@Service("classesService")
public class ClassesService {
	
	@Autowired
	private IStudentDao studentDao;
	
	@Autowired
	private IClassesDao classesDao;
	
	@Autowired
	private IClassesNewsDao classesNewsDao;

	
	/**
	 * ClassNews information
	 * @param classesNews
	 */
	public void addClassesNews(ClassesNews classesNews) {
		classesNewsDao.addClassesNews(classesNews);
	}
	
	public void deleteClassesNewsById(int id) {
		classesNewsDao.deleteClassesNewsById(id);
	}
	
	public List<ClassesNews> findClassesNewsByClassId(int classId){
		return classesNewsDao.findClassesNewsByClassId(classId, 1000);
	}
	
	
	/**
	 * Class information
	 */
	public List<Classes> listClasses(int start, int size, Classes classes) {
		return classesDao.findClasses(start, size, classes);
	 }
	
	public void addClasses(Classes classes) {
		classesDao.addClasses(classes);
	}
	
	public Classes findClassesById(int id){
		return classesDao.findClassesById(id);
	}
	
	/**
	 * Student
	 * @param 
	 * @return
	 */
	public Student findStudentById(int studentId){
		return studentDao.findStudentById(studentId);
	}
	
	public List<Student> findStudentByClassesId(int classesId){
		return studentDao.findStudentByClassesId(classesId);
	}
	
	public void addStudent(Student student){
		studentDao.addStudent(student);
	}
	
	public void deleteStudentById(int studentid){
		studentDao.deleteStudentById(studentid);
	}

	
	public void updateStudentBy(Student student) {
		studentDao.updateStudent(student);
	}
	
	/**
	 * Student Class association
	 * @param classid
	 */
	
	public void updateClassStudentCount(int classId){
		classesDao.updateClassStudentCount(classId);
	}
	
}
