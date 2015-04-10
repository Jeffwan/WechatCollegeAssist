package com.diaosiding.college.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.diaosiding.college.model.Constants;
import com.diaosiding.college.model.StudentMessage;

@Component
public class StudentMessageDao extends BaseDao implements IStudentMessageDao{

	public List<StudentMessage> findStudentMessageByStudentId(int studentId,int limit) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("studentid", studentId);
		map.put("limit", limit);
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".StudentDao" + ".selectStudentMessageByStudentId",map);
	}
	
	public int addStudentMessage(StudentMessage studentMessage) {
		return this.writerSqlSession.insert(Constants.DAONAMESPACE + ".StudentDao" + ".addStudentMessage", studentMessage);
	}
	
	public int deleteStudentMessageById(int id) {
		return this.writerSqlSession.delete(Constants.DAONAMESPACE + ".StudentDao" + ".deleteStudentMessageById", id);
	}
	
}
