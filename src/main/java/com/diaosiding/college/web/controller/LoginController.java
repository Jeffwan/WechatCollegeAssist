package com.diaosiding.college.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.diaosiding.college.model.Constants;

@Controller
public class LoginController {

	@RequestMapping("/")
	public String index(){
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(String username,String password, HttpServletRequest request){
		ModelAndView mv=new ModelAndView("");
		if (Constants.USERNAME.equals(username) && Constants.PASSWORD.equals(password)){
			request.getSession().setAttribute("user", username);
			mv.setViewName("redirect:/manager/students");
		} else{
			mv.setViewName("forward:/");
			mv.addObject("username",username);
			mv.addObject("message", "username or password error ");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/loginout",method=RequestMethod.POST)
	public ModelAndView loginOut(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/");
		request.getSession().removeAttribute("user");
		
		return mv;
	}
	
}
