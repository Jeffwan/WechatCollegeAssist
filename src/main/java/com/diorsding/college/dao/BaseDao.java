package com.diorsding.college.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {

	@Resource(name="readSqlSession")
	public SqlSessionTemplate readSqlSession;
	
	@Resource(name="writeSqlSession")
	public SqlSessionTemplate writerSqlSession;
}
