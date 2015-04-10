package com.diaosiding.college.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diaosiding.college.dao.IClassesNewsDao;
import com.diaosiding.college.dao.IExamDao;
import com.diaosiding.college.dao.IMessageDao;
import com.diaosiding.college.dao.IReplyDao;
import com.diaosiding.college.dao.IStudentDao;
import com.diaosiding.college.dao.IStudentMessageDao;

@Service
public class WechatService {

	@Autowired
	private IMessageDao messageDao;
	
	@Autowired
	private IReplyDao replyDao;
	
	@Autowired
	private IExamDao examDao;

	@Autowired
	private IStudentDao studentDao;
	
	@Autowired
	private IStudentMessageDao studentMessageDao;
	
	@Autowired
	private IClassesNewsDao classesNewsDao;
	
	
	
	
	
}
