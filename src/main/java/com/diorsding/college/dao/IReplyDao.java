package com.diorsding.college.dao;

import java.util.List;

import com.diorsding.college.model.Article;
import com.diorsding.college.model.Reply;

public interface IReplyDao {

	public void addReply(Reply reply);

	public void addArtical(Article article);

	public List<Reply> findReply(int start, int size);

}