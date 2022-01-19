package com.poscoict.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ModifyAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		String title = request.getParameter("title");
		String content =request.getParameter("content");
		String input_no = request.getParameter("no");
		
		if((input_no==null)||"".equals(input_no)) {
			MvcUtil.redirect("/mysite02/board", request, response);
			return;
		}
		Integer no = Integer.valueOf(input_no);
		
		BoardVo vo = BoardVo.builder().title(title).contents(content).no(no).build();
		new BoardDao().updateOne(vo);
		MvcUtil.redirect("/mysite02/board?a=viewform&board_sn="+input_no, request, response);
	}

}
