package com.diaosiding.college.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diaosiding.college.dao.IClassesDao;
import com.diaosiding.college.dao.IClassesNewsDao;
import com.diaosiding.college.dao.IStudentDao;
import com.diaosiding.college.model.Classes;
import com.diaosiding.college.model.ClassesNews;
import com.diaosiding.college.model.Student;

@Service
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
	
	public List<ClassesNews> findClassesNewsByClassId(int classid){
		return classesNewsDao.findClassesNewsByClassId(classid, 1000);
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
	
	public List<Student> findStudentByClassesId(int classesid){
		return studentDao.findStudentByClassesId(classesid);
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
	
	public void updateClassStudentCount(int classid){
		classesDao.updateClassStudentCount(classid);
	}
	
}
