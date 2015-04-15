package com.diaosiding.college.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.diaosiding.college.model.Classes;
import com.diaosiding.college.model.Constants;
import com.diaosiding.college.model.ExamMark;
import com.diaosiding.college.model.Student;
import com.diaosiding.college.model.StudentMessage;
import com.diaosiding.college.service.StudentService;


@Controller
@Scope("prototype")
public class StudentController {

	@Resource(name="studentService")
	private StudentService studentService; 
	
	@RequestMapping(value="/manager/students", method=RequestMethod.GET)
	public ModelAndView listStudent(String pageNum, Student student) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("students");
		mv.addObject("sidebar", "students");
		int num = 1;
		
		if (pageNum != null && !pageNum.isEmpty()) {
			num = Integer.parseInt(pageNum);
		}
		 
		List<Student> list = studentService.listStudent((num - 1) * Constants.pageSize, Constants.pageSize, student);
		List<Classes> clsList = studentService.findAllClasses();
		mv.addObject("studentList", list);
		mv.addObject("clsList", clsList);
		mv.addObject("length", list.size());
		mv.addObject("pageNum", num);
		mv.addObject("student", student);
		
		return mv;
	}

	
	@RequestMapping(value="/manager/leavemessage", method=RequestMethod.GET)
	public ModelAndView leavemessage(int studentId) {
		ModelAndView mv = new ModelAndView();
		Student student = studentService.findStudentById(studentId);
		
		if (student == null) {
			mv.setViewName("redirect:/manager/students");
		} else {
			mv.setViewName("addstudentmessage");
			mv.addObject("sidebar","students");
			mv.addObject("student",student);
			List<StudentMessage> list = studentService.listMessageByStudentId(studentId, 100);
			mv.addObject("studentMessageList", list);
		}
		
		return mv;
	}
	
	
	@RequestMapping(value="/manager/examdetail", method=RequestMethod.GET)
	public ModelAndView examdetail(int studentId) {
		ModelAndView mv = new ModelAndView();
		Student student = studentService.findStudentById(studentId);
		
		if (student == null) {
			mv.setViewName("redirect:/manager/students");
		} else {
			mv.setViewName("examdetail");
			mv.addObject("sidebar","students");
			mv.addObject("student",student);
			List<ExamMark> list = studentService.findExamMarkByStudentId(studentId, 100);
			mv.addObject("emlist", list);
		}
		
		return mv;
	}
	
	@RequestMapping(value="/manager/addmessage",method=RequestMethod.POST)
	public ModelAndView addmessage(StudentMessage studentMessage){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/manager/leavemessage");
		mv.addObject("studentId",studentMessage.getStudentId());
		studentMessage.setInsertTime(new Date());
		
		studentService.addStudentMessage(studentMessage);
		mv.addObject("notice","Leave Message successfully");
		return mv;
	}
	
	@RequestMapping(value="/manager/deletemessage",method=RequestMethod.GET)
	public ModelAndView deletemessage(int studentId, int messageId){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/manager/leavemessage");
		mv.addObject("studentId",studentId);
		studentService.deleteStudentMessageById(messageId);
		mv.addObject("notice","Delete Message Successfully");
		return mv;
	}
	
	
}
