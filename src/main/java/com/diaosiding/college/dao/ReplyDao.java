package com.diaosiding.college.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.diaosiding.college.model.Article;
import com.diaosiding.college.model.Constants;
import com.diaosiding.college.model.Reply;

@Component
public class ReplyDao extends BaseDao implements IReplyDao {

	public void addReply(Reply reply) {
		this.writerSqlSession.insert(Constants.DAONAMESPACE + ".ReplyDao" + "addReply", reply);
	}
	
	public void addArtical(Article article) {
		this.writerSqlSession.insert(Constants.DAONAMESPACE + ".MessageDao" + "addArticle", article);
	}
	
	public List<Reply> findReply(int start, int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("size", size);
		
		return this.readSqlSession.selectList(Constants.DAONAMESPACE + ".MessageDao" + "selectReply", map);
	}
	
}
