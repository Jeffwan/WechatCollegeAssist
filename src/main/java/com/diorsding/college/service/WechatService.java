package com.diorsding.college.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diorsding.college.dao.IClassesNewsDao;
import com.diorsding.college.dao.IExamDao;
import com.diorsding.college.dao.IMessageDao;
import com.diorsding.college.dao.IReplyDao;
import com.diorsding.college.dao.IStudentDao;
import com.diorsding.college.dao.IStudentMessageDao;
import com.diorsding.college.model.Article;
import com.diorsding.college.model.ClassesNews;
import com.diorsding.college.model.Exam;
import com.diorsding.college.model.ExamMark;
import com.diorsding.college.model.Message;
import com.diorsding.college.model.Reply;
import com.diorsding.college.model.Student;
import com.diorsding.college.model.StudentMessage;

@Service("wechatService")
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
	
	public String getSingleExamMarkStringByStudentId(int studentid){
		StringBuilder sb = new StringBuilder();
		Student student = studentDao.findStudentById(studentid);
		if(student == null){
			sb.append("No student No").append(studentid).append("student !");
		}else{
			List<ExamMark> list = examDao.findExamMarkByStudentId(studentid, 1);
			sb.append("Hi, No").append(studentid).append("student(").append(student.getName());
			if(list == null || list.size()<1 || list.get(0).getExam()==null){
				sb.append(")no exam record!");
			}else{
				ExamMark em = list.get(0);
				Exam e = em.getExam();
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				sb.append(")last exam record:\n").append("Exam：").append(e.getCourse())
				  .append("\nscore:").append(em.getMark())
				  .append("\nrank:").append(em.getRank())
				  .append("\ntime:").append(sf.format(em.getExamTime()))
				  .append("\nremark:").append(em.getRemark())
				  .append("\nfullScore:").append(e.getFullMarks())
				  .append("\nAverage:").append(e.getAverage())
				  .append("\nHighest:").append(e.getTopMark())
				  .append("\nLowest:").append(e.getLowestMark())
				  .append("\nRemark").append(e.getRemark());
			}
		}
		return sb.toString();
	}
	
	public String getExamMarkHistoryStringByStudentId(int studentid){
		StringBuilder sb = new StringBuilder();
		Student student = studentDao.findStudentById(studentid);
		if(student == null){
			sb.append("您好，未找到编号为").append(studentid).append("的学生！");
		}else{
			sb.append("您好，编号为").append(studentid).append("的学生(").append(student.getName());
			List<ExamMark> list = examDao.findExamMarkByStudentId(studentid, 10);
			if(list == null || list.size()<1 || list.get(0).getExam()==null){
				sb.append(")无考试记录！");
			}else{
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Exam e = null;
				sb.append(")最近10次考试情况如下:");
				for(ExamMark em : list){
					e = em.getExam();
					sb.append("\n考试时间：").append(sf.format(em.getExamTime()))
					  .append("\n科目：").append(e.getCourse())
					  .append("\n分数：").append(em.getMark())
					  .append("\n班级排名：").append(em.getRank())
					  .append("\n老师备注：").append(em.getRemark())
					  .append("\n试卷满分：").append(e.getFullMarks())
					  .append("\n班级均分：").append(e.getAverage())
					  .append("\n班级最高分：").append(e.getTopMark())
					  .append("\n班级最低分：").append(e.getLowestMark())
					  .append("\n考试说明：").append(e.getRemark())
					  .append("\n------分割线-------");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 根据学生编号查询学生最近的老师留言信息
	 * @param studentid 学生编号
	 * @return 以字符串形式返回老师留言信息
	 */
	public String getSingleStudentMessageByStudentId(int studentid){
		StringBuilder sb = new StringBuilder();
		Student student = studentDao.findStudentById(studentid);
		if(student == null){
			sb.append("您好，未找到编号为").append(studentid).append("的学生！");
		}else{
			sb.append("您好，编号为").append(studentid).append("的学生(").append(student.getName());
			List<StudentMessage> list = studentMessageDao.findStudentMessageByStudentId(studentid, 1);
			if(list == null || list.size()<1 ){
				sb.append(")无老师留言！");
			}else{
				sb.append(")最近老师留言如下:");
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				StudentMessage sm = list.get(0);
				sb.append("\n留言时间：").append(sf.format(sm.getInsertTime()))
				  .append("\n留言内容：").append(sm.getContent());
			}	
		}
		return sb.toString();
	}
	
	/**
	 * 根据学生编号查询学生的老师留言信息记录（最近10次）
	 * @param studentid 学生编号
	 * @return 以字符串形式返回老师留言信息
	 */
	public String getStudentMessageHistoryByStudentId(int studentid){
		StringBuilder sb = new StringBuilder();
		Student student = studentDao.findStudentById(studentid);
		if(student == null){
			sb.append("您好，未找到编号为").append(studentid).append("的学生！");
		}else{
			sb.append("您好，编号为").append(studentid).append("的学生(").append(student.getName());
			List<StudentMessage> list = studentMessageDao.findStudentMessageByStudentId(studentid, 10);
			if(list == null || list.size()<1 ){
				sb.append(")无老师留言！");
			}else{
				sb.append(")最近(10次)老师留言如下:");
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				for(StudentMessage sm : list){
					sb.append("\n留言时间：").append(sf.format(sm.getInsertTime()))
					  .append("\n留言内容：").append(sm.getContent())
					  .append("\n------分割线-------");
				}
			}	
		}
		return sb.toString();
	}
	

	public String getSingleClassesNewsByStudentId(int studentid){
		StringBuilder sb = new StringBuilder();
		Student student = studentDao.findStudentById(studentid);
		if(student == null){
			sb.append("I didn't find No.").append(studentid).append("student!");
		}else{
			sb.append("Hi, No.").append(studentid).append("Student(").append(student.getName());
			List<ClassesNews> list = classesNewsDao.findClassesNewsByClassId(student.getClassId(), 1);
			if(list == null || list.size()<1 ){
				sb.append(")No news in the class");
			}else{
				sb.append(")Recent class news");
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				ClassesNews cn = list.get(0);
				sb.append("\nNews time:").append(sf.format(cn.getInsertTime()))
				  .append("\nNews content").append(cn.getContent());
			}	
		}
		return sb.toString();
	}
	
	public String getClassesNewsHistoryByStudentId(int studentid){
		StringBuilder sb = new StringBuilder();
		Student student = studentDao.findStudentById(studentid);
		if(student == null){
			sb.append("I didn't find No.").append(studentid).append("student!");
		}else{
			sb.append("Hi, No").append(studentid).append("Student (").append(student.getName());
			List<ClassesNews> list = classesNewsDao.findClassesNewsByClassId(student.getClassId(), 10);
			if(list == null || list.size()<1 ){
				sb.append(") No news in this class");
			}else{
				sb.append(")Recent class news:");
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				for(ClassesNews cn:list){
					sb.append("\nNews time：").append(sf.format(cn.getInsertTime()))
					  .append("\nNews content：").append(cn.getContent())
					  .append("\n----------------");
				}
			}	
		}
		return sb.toString();
	}
	
	
	
	public void addMessage(Message message) {
		System.out.println("addMessage: " + message);
		
		messageDao.addMessage(message);
	}
	
	public List<Message> listMessage(int start,int size){
		return messageDao.findMessage(start,size);
	}
	
	public List<Reply> listReply(int start,int size){
		return replyDao.findReply(start,size);
	}

	public void addReply(Reply reply){
		replyDao.addReply(reply);
		if(Reply.NEWS.equals(reply.getMsgType())&&null != reply.getArticles()){
			List<Article> articles = reply.getArticles();
			for(Article a: articles){
				a.setReplyId(reply.getId());
				replyDao.addArtical(a);
			}
		}
	}
}
