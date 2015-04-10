package com.diaosiding.college.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.diaosiding.college.model.Classes;
import com.diaosiding.college.model.ClassesNews;
import com.diaosiding.college.model.Constants;
import com.diaosiding.college.model.Student;
import com.diaosiding.college.service.ClassesService;

@Controller
public class ClassesController {

	@Autowired
	private ClassesService classesService;
	
	@RequestMapping(value="/manager/classes",method=RequestMethod.GET)
	public ModelAndView listStudent(String pageNum, Classes classes){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("classes");
		mv.addObject("sidebar","classes");
		int num = 1;
		if(pageNum != null){
			num = Integer.parseInt(pageNum);
		}
		
		List<Classes> list = classesService.listClasses((num-1) * Constants.pageSize, Constants.pageSize,classes);
		mv.addObject("classesList", list);
		mv.addObject("length", list.size());
		mv.addObject("pagenum", num);
		mv.addObject("classes", classes);
		
		
		return mv;
	}
	
	@RequestMapping(value="/manager/addclassespage",method=RequestMethod.GET)
	public ModelAndView addClassesPage(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("addclasses");
		mv.addObject("sidebar","classes");
	
		return mv;
	}
	
	
	@RequestMapping(value="/manager/addclasses",method=RequestMethod.POST)
	public ModelAndView addClasses(Classes classes){
		ModelAndView mv=new ModelAndView();
		Classes cls = classesService.findClassesById(classes.getId());
		if(cls == null){
			mv.setViewName("redirect:/manager/classes");
			classes.setStudentCount(0);
			classesService.addClasses(classes);
		}else{
			mv.setViewName("redirect:/manager/addclassespage");
			mv.addObject("name", classes.getName());
			mv.addObject("headteacher", classes.getHeadTeacher());
			mv.addObject("notice", "No. " + classes.getId() + " class already exist");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/manager/managerstudentpage",method=RequestMethod.GET)
	public ModelAndView studentPage(int classesid){
		ModelAndView mv=new ModelAndView();
		Classes cls = classesService.findClassesById(classesid);
		List<Student> stlist = classesService.findStudentByClassesId(classesid);
		
		mv.setViewName("addstudents");
		mv.addObject("sidebar","classes");
		mv.addObject("cls",cls);
		mv.addObject("stlist",stlist);
		mv.addObject("length", stlist.size());
		
		return mv;
	}

	@RequestMapping(value="/manager/classesnewspage", method=RequestMethod.GET)
	public ModelAndView classesnewsPage(int classesId){
		ModelAndView mv=new ModelAndView();
		Classes cls = classesService.findClassesById(classesId);
		List<ClassesNews> cnlist= classesService.findClassesNewsByClassId(classesId);
		mv.setViewName("addclassesnews");
		mv.addObject("sidebar","classes");
		mv.addObject("cls",cls);
		mv.addObject("cnlist",cnlist);
		
		return mv;
	}
	
	@RequestMapping(value="/manager/addclassesnews",method=RequestMethod.POST)
	public ModelAndView addClassesNews(ClassesNews classesNews){
		ModelAndView mv=new ModelAndView();
		classesNews.setInsertTime(new Date());
		classesService.addClassesNews(classesNews);
		mv.addObject("notice","Add class news successfully");
		mv.addObject("classesid",classesNews.getClassId());
		mv.setViewName("redirect:/manager/classesnewspage");
		
		return mv;
	}
	
	
	@RequestMapping(value="/manager/deleteclassesnews",method=RequestMethod.GET)
	public ModelAndView deleteClassesNews(int classesid,int id){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/manager/classesnewspage");
		mv.addObject("classesid",classesid);
		mv.addObject("notice","Delete class news successfully");
		
		return mv;
	}
	
	
	@RequestMapping(value="/manager/addstudent",method=RequestMethod.POST)
	public ModelAndView addStudent(Student student){
		ModelAndView mv=new ModelAndView();
		Student stu = classesService.findStudentById(student.getId());
		if(stu == null){
			classesService.addStudent(student);
			classesService.updateClassStudentCount(student.getClassId());
			mv.addObject("notice","Add student successfully");
		}else{
			mv.addObject("notice","No. "+student.getId()+" ("+stu.getName()+") exist!");
		}
		mv.addObject("classesid",student.getClassId());
		mv.setViewName("redirect:/manager/managerstudentpage");
		return mv;
	}
	
	@RequestMapping(value="/manager/deletestudent",method=RequestMethod.GET)
	public ModelAndView deleteStudent(int studentid,int classid){
		ModelAndView mv=new ModelAndView();
		classesService.deleteStudentById(studentid);
		classesService.updateClassStudentCount(classid);
		mv.addObject("classesid",classid);
		mv.addObject("notice","Delete student info successfully");
		mv.setViewName("redirect:/manager/managerstudentpage");
		return mv;
	}
	
	@RequestMapping(value="/manager/updatestudent",method=RequestMethod.POST)
	public ModelAndView updateStudent(Student student){
		ModelAndView mv = new ModelAndView();
		classesService.updateStudentBy(student); 
		mv.addObject("classesid",student.getClassId());
		mv.addObject("notice","Edit student info successfully");
		mv.setViewName("redirect:/manager/managerstudentpage");
		return mv;
	}
	
}
