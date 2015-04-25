package com.diorsding.college.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.diorsding.college.dao.BaseDao;
import com.diorsding.college.dao.IStudentMessageDao;
import com.diorsding.college.model.Constants;
import com.diorsding.college.model.StudentMessage;

@Repository
public class StudentMessageDao extends BaseDao implements IStudentMessageDao{

	public List<StudentMessage> findStudentMessageByStudentId(int studentId,int limit) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("studentId", studentId);
		map.put("limit", limit);
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".StudentMessageDao" + ".selectStudentMessageByStudentId",map);
	}
	
	public int addStudentMessage(StudentMessage studentMessage) {
		return this.writerSqlSession.insert(Constants.DAONAMESPACE + ".StudentMessageDao" + ".addStudentMessage", studentMessage);
	}
	
	public int deleteStudentMessageById(int id) {
		return this.writerSqlSession.delete(Constants.DAONAMESPACE + ".StudentMessageDao" + ".deleteStudentMessageById", id);
	}
	
}
