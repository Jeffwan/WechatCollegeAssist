package com.diaosiding.college.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.diaosiding.college.dao.BaseDao;
import com.diaosiding.college.dao.IClassesDao;
import com.diaosiding.college.model.Classes;
import com.diaosiding.college.model.Constants;

@Repository
public class ClassesDao extends BaseDao implements IClassesDao{

	public List<Classes> findAllClasses() {
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".ClassesDao" + ".selectAllClasses");
	}
	
	public List<Classes> findClasses(int start, int size, Classes classes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("size", size);
		map.put("classes", classes);
		
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".ClassesDao" + ".selectClasses", map);
	}
	
	public void addClasses(Classes classes) {
		this.writerSqlSession.insert(Constants.DAONAMESPACE + ".ClassesDao" + ".addClass", classes);
	}
	
	public Classes findClassesById(int id) {
		return readSqlSession.selectOne(Constants.DAONAMESPACE + ".ClassesDao" + ".selectClassById", id);
	}
	
	public void updateClassStudentCount(int id) {
		writerSqlSession.update(Constants.DAONAMESPACE + ".ClassesDao" + ".updateClassStudentCount", id);
	}
	
}
