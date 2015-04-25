package com.diorsding.college.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.diorsding.college.dao.BaseDao;
import com.diorsding.college.dao.IMessageDao;
import com.diorsding.college.model.Constants;
import com.diorsding.college.model.Message;

@Repository
public class MessageDao extends BaseDao implements IMessageDao {
	
	public int addMessage(Message message) {
		return this.writerSqlSession.insert(Constants.DAONAMESPACE + ".MessageDao" + ".addMessage", message);
	}
	
	public List<Message> findMessage(int start, int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("size", size);
		
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".MessageDao" + ".selectMessage", map);
	}
}
