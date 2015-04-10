package com.diaosiding.college.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.diaosiding.college.model.Classes;
import com.diaosiding.college.model.Constants;
import com.diaosiding.college.model.Exam;
import com.diaosiding.college.model.Student;
import com.diaosiding.college.service.ExamService;
import com.diaosiding.college.util.ExamUtil;

@Controller
public class ExamController {

	@Autowired
	private ExamService examService;
	
	@RequestMapping(value="/manager/exams",method=RequestMethod.GET)
	public ModelAndView listStudent(String pageNum, Exam exam){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("exams");
		mv.addObject("sidebar","exams");
		
		int num = 1;
		if(pageNum != null){
			num = Integer.parseInt(pageNum);
		}
		
		List<Exam> list = examService.listExam((num-1) * Constants.pageSize, Constants.pageSize,exam);
		List<Classes> clslist = examService.findAllClasses();
		mv.addObject("examList", list);
		mv.addObject("clsList", clslist);
		mv.addObject("length", list.size());
		mv.addObject("pagenum", num);
		mv.addObject("exam", exam);
		
		return mv;
	}
	
	@RequestMapping(value="/manager/addexam",method=RequestMethod.POST)
	public ModelAndView addExam(Exam exam){
		List<Student> stlist = examService.findStudentByClassesId(exam.getClassId());
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("addexam");
		mv.addObject("sidebar","exams");
		mv.addObject("exam",exam);
		mv.addObject("stlist",stlist);
		
		return mv;
	}
	
	@RequestMapping(value="/manager/viewexam",method=RequestMethod.GET)
	public ModelAndView viewExam(int id){
		Exam exam = examService.findExamById(id);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("viewexam");
		mv.addObject("sidebar","exams");
		mv.addObject("exam",exam);
		
		return mv;
	}
	
	@RequestMapping(value="/manager/addexammark",method=RequestMethod.POST)
	public ModelAndView addExamMark(Exam exam){
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("sidebar","exams");
		mv.setViewName("redirect:/manager/exams");
		
		exam.setExamTime(new Date());
		exam = ExamUtil.sortExamMark(exam); // sort score first
		examService.addExam(exam);
		
		return mv;
	}
	
	
}
