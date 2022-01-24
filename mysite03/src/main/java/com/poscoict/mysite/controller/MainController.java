package com.poscoict.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poscoict.mysite.service.UserService;

@Controller
public class MainController {
	
	
	
	@RequestMapping({"","/main"})
	public String index() {
		return "main/index";
	}
	
}
