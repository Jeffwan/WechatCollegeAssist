package com.diorsding.college.dao;

import java.util.List;

import com.diorsding.college.model.Student;

public interface IStudentDao {

	public Student findStudentById(int id);

	public List<Student> findStudent(int start, int size, Student student);

	public List<Student> findStudentByClassesId(int classesId);

	public int addStudent(Student student);

	public int deleteStudentById(int studentId);

	public int updateStudent(Student student);

}