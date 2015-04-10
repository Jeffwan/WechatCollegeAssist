package com.diaosiding.college.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.diaosiding.college.model.Classes;
import com.diaosiding.college.model.Constants;

@Component
public class ClassesDao extends BaseDao implements IClassesDao{

	/* (non-Javadoc)
	 * @see com.diaosiding.college.dao.IClassesDao#findAllClasses()
	 */
	public List<Classes> findAllClasses() {
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".ClassesDao" + ".selectClasses");
	}
	
	/* (non-Javadoc)
	 * @see com.diaosiding.college.dao.IClassesDao#findClasses(int, int, com.diaosiding.college.model.Classes)
	 */
	public List<Classes> findClasses(int start, int size, Classes classes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("size", size);
		map.put("classes", classes);
		
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".ClassesDao" + ".selectClasses", map);
	}
	
	/* (non-Javadoc)
	 * @see com.diaosiding.college.dao.IClassesDao#addClasses(com.diaosiding.college.model.Classes)
	 */
	public void addClasses(Classes classes) {
		this.writerSqlSession.insert(Constants.DAONAMESPACE + ".ClassesDao" + ".addClass", classes);
	}
	
	/* (non-Javadoc)
	 * @see com.diaosiding.college.dao.IClassesDao#findClassesById(int)
	 */
	public Classes findClassesById(int id) {
		return readSqlSession.selectOne(Constants.DAONAMESPACE + ".ClassesDao" + ".selectClassById", id);
	}
	
	/* (non-Javadoc)
	 * @see com.diaosiding.college.dao.IClassesDao#updateClassesStudentCount(int)
	 */
	public void updateClassStudentCount(int classId) {
		writerSqlSession.update(Constants.DAONAMESPACE + ".ClassesDao" + ".updateClassStudentCount", classId);
	}
	
}
