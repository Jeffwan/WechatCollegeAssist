package com.diorsding.college.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.diorsding.college.dao.BaseDao;
import com.diorsding.college.dao.IExamDao;
import com.diorsding.college.model.Constants;
import com.diorsding.college.model.Exam;
import com.diorsding.college.model.ExamMark;

@Repository
public class ExamDao extends BaseDao implements IExamDao{

	public List<ExamMark> findExamMarkByStudentId(int studentId, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studentId", studentId);
		map.put("limit", limit);
		
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".ExamDao" + ".selectExamMarkByStudentId", map);
	}
	
	public List<Exam> findExam(int start, int size, Exam exam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("size", size);
		map.put("exam", exam);
		
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".ExamDao" + ".selectExam", map);
	}
	
	public void addExam(Exam exam) {
		writerSqlSession.insert(Constants.DAONAMESPACE + ".ExamDao" + ".addExam", exam);
	}
	
	public void addExamMark(ExamMark examMark) {
		writerSqlSession.insert(Constants.DAONAMESPACE + ".ExamDao" + ".addExamMark", examMark);
	}
	
	public Exam findExamById(int id) {
		return this.readSqlSession.selectOne(Constants.DAONAMESPACE + ".ExamDao" + ".selectExamById", id);
	}
}
