package com.diorsding.college.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.diorsding.college.dao.BaseDao;
import com.diorsding.college.dao.IReplyDao;
import com.diorsding.college.model.Article;
import com.diorsding.college.model.Constants;
import com.diorsding.college.model.Reply;

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
