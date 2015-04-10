package com.diaosiding.college.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.diaosiding.college.model.Constants;
import com.diaosiding.college.model.Message;

@Component
public class MessageDao extends BaseDao {
	public int addMessage(Message message) {
		return this.writerSqlSession.insert(Constants.DAONAMESPACE + ".MessageDao" + "addMessage", message);
	}
	
	public List<Message> findMessage(int start, int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("size", size);
		
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".MessageDao" + "selectMessage", map);
	}
}
