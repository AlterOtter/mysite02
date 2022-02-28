package com.poscoict.mysite.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	
	
	@RequestMapping({"","/main"})
	public String index() {
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/msg01")
	public String msg01() {
		return "안녕";
	}
	
//	@RequestMapping("/msg02")
//	public String msg02(HttpServletResponse resp) throws IOException {
//		resp.setContentType("application/json;charset=UTF-8");
//		resp.getWriter().print("(\"message\":\"Hello World\")");
//		return null;
//	}

//	@RequestBody
//	@RequestMapping("/msg03")
//	public Object message03() {
//		Map <String,Object> map = new Map<>();
//	}

		
	
	
}
