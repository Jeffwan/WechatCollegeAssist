package com.diaosiding.college.dao;

import java.util.List;

import com.diaosiding.college.model.StudentMessage;

public interface IStudentMessageDao {

	public List<StudentMessage> findStudentMessageByStudentId(int studentId,
			int limit);

	public int addStudentMessage(StudentMessage studentMessage);

	public int deleteStudentMessageById(int id);

}