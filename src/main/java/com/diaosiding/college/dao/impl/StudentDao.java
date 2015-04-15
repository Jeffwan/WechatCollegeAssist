package com.diaosiding.college.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.diaosiding.college.dao.BaseDao;
import com.diaosiding.college.dao.IStudentDao;
import com.diaosiding.college.model.Constants;
import com.diaosiding.college.model.Student;

@Repository
public class StudentDao extends BaseDao implements IStudentDao{

	public Student findStudentById(int id) {
		return this.readSqlSession.selectOne(Constants.DAONAMESPACE + ".StudentDao" + ".selectStudentById", id);
	}
	
	public List<Student> findStudent(int start,int size,Student student) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", start);
		map.put("size", size);
		map.put("student", student);
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".StudentDao" + ".selectStudent",map);
	}
	
	public List<Student> findStudentByClassesId(int classesId) {
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".StudentDao" + ".selectStudentByClassesId",classesId);
	}
	
	public int addStudent(Student student) {
		return this.writerSqlSession.insert(Constants.DAONAMESPACE + ".StudentDao" + ".addStudent", student);
	}
	
	public int deleteStudentById(int studentid) {
		return this.writerSqlSession.delete(Constants.DAONAMESPACE + ".StudentDao" + ".deleteStudentById", studentid);
	}
	
	public int updateStudent(Student student) {
		return this.writerSqlSession.update(Constants.DAONAMESPACE + ".StudentDao" + ".updateStudent", student);
	}
}
