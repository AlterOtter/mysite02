package com.poscoict.mysite.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.repository.BoardDao;
import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.service.CommService;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.CommVo;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	@Autowired
	CommService commservice;
	
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
	
	@Auth
	@RequestMapping(value="/write",method = RequestMethod.GET)
	public String WriteForm() {
		return "board/write";
		
	}
	
	@Auth
	@RequestMapping(value="/write",method = RequestMethod.POST)
	public String Write(BoardVo vo) {
		boolean result=boardservice.write(vo);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/view",method = RequestMethod.GET)
	public String view(@RequestParam(value="no",required = true)Integer no,@CookieValue(value="read" ,required =false)Cookie cookie,Model model) {
		try {	
			boardservice.getContents(no,model);
			commservice.getComm(no, model);
			return "board/view";
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/update",method = RequestMethod.GET)
	public String updateform(@RequestParam(value="no",required=true) Integer no,Model model) {
		boardservice.getContents(no, model);
		return "/board/modify";
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String update(BoardVo vo,Model model) {
		boardservice.update(vo);
		return "redirect:/board/view?no="+vo.getNo();
	}


	@RequestMapping(value="/reply/{no}",method=RequestMethod.GET)
	public String replyform(@PathVariable(value="no", required = true)Integer no,Model model) {
		boardservice.getContents(no, model);
		return "board/reply";
	}
	
	@RequestMapping(value="/reply",method=RequestMethod.POST)
	public String reply(BoardVo vo,HttpSession session,Model model) {
		try {
			Integer userno=((UserVo)session.getAttribute("authvo")).getNo();
			vo.setUserVo(UserVo.builder().no(userno).build());
			System.out.println(vo.toString());
			boardservice.writeReply(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/board";
		}

		
		return "redirect:/board";
	}
	@RequestMapping(value="/writecomm",method=RequestMethod.POST)
	public String wirteCommnet(CommVo vo) {
		try {
			commservice.insert(vo);
			return "redirect:/board/view?no="+vo.getComm_bd_sn();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/board";

		}
		
		
	}
	
	@RequestMapping(value="/deletecomm",method=RequestMethod.GET)
	public String deltecomm(@RequestParam(value="no",required = true) Integer no,
			@RequestParam(value="bd_sn",required = true) Integer bd_sn) {
		try {
			commservice.delete(no);
			return "redirect:/board/view?no="+bd_sn.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/board";
		}
		
	}

	
	
}
