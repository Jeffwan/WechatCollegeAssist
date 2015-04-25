package com.diorsding.college.dao;

import java.util.List;

import com.diorsding.college.model.Message;

public interface IMessageDao {

	public int addMessage(Message message);

	public List<Message> findMessage(int start, int size);

}