package com.diorsding.college.dao;

import java.util.List;

import com.diorsding.college.model.StudentMessage;

public interface IStudentMessageDao {

	public List<StudentMessage> findStudentMessageByStudentId(int studentId,
			int limit);

	public int addStudentMessage(StudentMessage studentMessage);

	public int deleteStudentMessageById(int id);

}