package com.diorsding.college.dao;

import java.util.List;

import com.diorsding.college.model.Classes;

public interface IClassesDao {

	public List<Classes> findAllClasses();

	public List<Classes> findClasses(int start, int size, Classes classes);

	public void addClasses(Classes classes);

	public Classes findClassesById(int id);

	public void updateClassStudentCount(int classId);

}