package com.poscoict.mysite.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String getlist(@RequestParam(value="page",required = false)Integer page
			,@RequestParam(value="input",required = false) String input 
			,Model model) {
		
		if(boardservice.GetBoadList(page, input, model)) {
			return "board/list";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value ="/delete/{no}",method = RequestMethod.GET)
	public String delete(@PathVariable(value="no", required = true)Integer vo,Model model) {
		try {
			boolean result=boardservice.delete(vo);
			return "redirect:/board";
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "redirect:/board";
		}
		
	}
	
	@RequestMapping(value="/write",method = RequestMethod.GET)
	public String WriteForm() {
		
		return "board/write";
	}
	
	
}
