package com.diaosiding.college.dao;

import java.util.List;

import com.diaosiding.college.model.Article;
import com.diaosiding.college.model.Reply;

public interface IReplyDao {

	public void addReply(Reply reply);

	public void addArtical(Article article);

	public List<Reply> findReply(int start, int size);

}