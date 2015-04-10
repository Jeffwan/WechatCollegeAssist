package com.diaosiding.college.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.diaosiding.college.model.ClassesNews;
import com.diaosiding.college.model.Constants;

@Component
public class ClassesNewsDao extends BaseDao implements IClassesNewsDao {
	
	
	public List<ClassesNews> findClassesNewsByClassId(int classId, int limit) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("classId", classId);
		map.put("limit", limit);
		
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".ClassesNewsDao" + ".selectClassesNewsByClassId", map);
	}
	
	
	public void addClassesNews(ClassesNews classesNews) {
		writerSqlSession.insert(Constants.DAONAMESPACE + ".ClassesNewsDao" + ".addClassesNews", classesNews);
	}
	
	public void deleteClassesNewsById(int id) {
		writerSqlSession.delete(Constants.DAONAMESPACE + ".ClassesNewsDao" + ".selectClassesNewsByClassId", id);
	}
}
