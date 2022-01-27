package com.poscoict.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.security.AuthUser;
import com.poscoict.mysite.service.UserService;
import com.poscoict.mysite.vo.UserVo;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userservice;
	
	//=================================LOGIN============================================
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String UserLoginform() {
		return "user/loginform";
	}

	//=================================END LOGIN============================================
	
	//=================================UPDATE============================================
	
	@Auth
	@RequestMapping(value ="/updateform",method=RequestMethod.GET)
	public String UserUpdateform(@AuthUser UserVo authvo,Model model) {
		try {
			System.out.println(authvo.toString());
			UserVo InfoVo=userservice.updateFormInfo(authvo);
			model.addAttribute("userInfo", InfoVo);
			return "user/updateform";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "user/loginform";
		}
	
	}
	
	@Auth
	@RequestMapping("/update")
	public String UserUpdate(@AuthUser UserVo authvo,UserVo vo,Model model,HttpSession session) {
		try {
			System.out.println(vo.toString());
			boolean res_vo = userservice.updateService(vo);
			if(res_vo) return "user/updateform";
			session.setAttribute("authvo", vo);
			return "redirect:/";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "user/updateform";
		}
	}
	
	//=================================END UPDATE============================================

	
	//=================================JOIN============================================
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String UserJoinform() {
		return "user/joinform";
	}
	
	@RequestMapping("/join")
	public String UserJoin(UserVo vo,HttpSession session) {
		userservice.join(vo);
		return "user/joinsuccess";
	}
	
	//=================================END JOIN============================================
	
//	@ExceptionHandler(Exception.class)
//	public String exception() {
//		return "error/exception";
//	}

	
	
}
