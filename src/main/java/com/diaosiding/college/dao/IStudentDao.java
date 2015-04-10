package com.diaosiding.college.dao;

import java.util.List;

import com.diaosiding.college.model.Student;

public interface IStudentDao {

	public Student findStudentById(int id);

	public List<Student> findStudent(int start, int size, Student student);

	public List<Student> findStudentByClassesId(int classesid);

	public int addStudent(Student student);

	public int deleteStudentById(int studentid);

	public int updateStudent(Student student);

}