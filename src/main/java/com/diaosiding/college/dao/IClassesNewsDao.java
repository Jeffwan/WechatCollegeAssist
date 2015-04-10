package com.diaosiding.college.dao;

import java.util.List;

import com.diaosiding.college.model.ClassesNews;

public interface IClassesNewsDao {

	public List<ClassesNews> findClassesNewsByClassId(int classId, int limit);

	public void addClassesNews(ClassesNews classesNews);

	public void deleteClassesNewsById(int id);

}