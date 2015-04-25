package com.diorsding.college.dao;

import java.util.List;

import com.diorsding.college.model.ClassesNews;

public interface IClassesNewsDao {

	public List<ClassesNews> findClassesNewsByClassId(int classId, int limit);

	public void addClassesNews(ClassesNews classesNews);

	public void deleteClassesNewsById(int id);

}