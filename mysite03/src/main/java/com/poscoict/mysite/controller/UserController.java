package com.poscoict.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String Userlogin(UserVo vo,Model model,HttpSession session) {
		try {
			UserVo res_vo=userservice.LoginService(vo);
			session.setAttribute("authvo", res_vo);
			return "main/index";
		} catch (Exception e) {
			model.addAttribute("result", "fail");
			System.out.println(e.getMessage());
			return "user/loginform";
		}
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String UserLogout(Model model,HttpSession session) {
		session.invalidate();
		return "main/index";	
	}
	//=================================END LOGIN============================================
	
	//=================================UPDATE============================================
	
	@RequestMapping(value ="/updateform",method=RequestMethod.GET)
	public String UserUpdateform(Model model,HttpSession session) {
		try {
			UserVo vo=(UserVo)session.getAttribute("authvo");
			UserVo InfoVo=userservice.updateFormInfo(vo);
			model.addAttribute("userInfo", InfoVo);
			return "user/updateform";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "user/loginform";
		}
	
	}
	
	@RequestMapping("/update")
	public String UserUpdate(UserVo vo,Model model,HttpSession session) {
		try {
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
		try {
			boolean result = userservice.join(vo);
			if(result) return "user/joinform";
			return "user/joinsuccess";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "user/joinform";
		}
		

	}
	
	//=================================END JOIN============================================
}
