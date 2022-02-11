package com.poscoict.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;

@Auth(role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	SiteService siteservice;
	
	@RequestMapping(value={"/main",""})
	public String main(Model model) {
		model.addAttribute("siteVo", siteservice.getSiteInfo());
		return "admin/main";
	}
	
	@RequestMapping(value="/main/update" ,method = RequestMethod.POST)
	public String updateMain(@RequestParam(value="upload-file",required = false) MultipartFile multipartFile,SiteVo vo) {
		System.out.println("제목 : "+ vo.getTitle());
		boolean result=siteservice.update(vo,multipartFile);
		return "redirect:/admin/main";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/guestbook")
	public String guest() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
