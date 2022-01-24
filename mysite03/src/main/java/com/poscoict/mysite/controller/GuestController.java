package com.poscoict.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.mysite.service.GuestService;
import com.poscoict.mysite.vo.GuestbookVO;

@Controller
@RequestMapping("/guest")
public class GuestController {
	
	@Autowired
	private GuestService guestservice;
	
	//==================================== List============================================
	@RequestMapping(value ="",method = RequestMethod.GET)
	public String GuestList(Model model) {
		
		List<GuestbookVO> volist=guestservice.getlist();
		model.addAttribute("list",volist);
		return "guestbook/list";
	}
	//====================================END List============================================	
	
	//==================================== List============================================
	@RequestMapping(value ="/delete/{no}",method = RequestMethod.GET)
	public String deleteform(@PathVariable(value="no",required = true) Integer no,Model model) {
		model.addAttribute("no",no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value ="/delete",method = RequestMethod.POST)
	public String delete(GuestbookVO vo,Model model) {
		try {
			boolean result=guestservice.delete(vo);
			return "redirect:/guest";
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "redirect:/guest";
		}
		
	}
	//====================================END List============================================
}
