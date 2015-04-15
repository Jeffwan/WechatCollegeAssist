package com.diaosiding.college.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.diaosiding.college.dao.BaseDao;
import com.diaosiding.college.dao.IReplyDao;
import com.diaosiding.college.model.Article;
import com.diaosiding.college.model.Constants;
import com.diaosiding.college.model.Reply;

@Repository
public class ReplyDao extends BaseDao implements IReplyDao {

	public void addReply(Reply reply) {
		this.writerSqlSession.insert(Constants.DAONAMESPACE + ".ReplyDao" + ".addReply", reply);
	}
	
	public void addArtical(Article article) {
		this.writerSqlSession.insert(Constants.DAONAMESPACE + ".ReplyDao" + ".addArticle", article);
	}
	
	public List<Reply> findReply(int start, int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("size", size);
		
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".ReplyDao" + ".selectReply", map);
	}
	
}
