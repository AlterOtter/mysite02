package com.poscoict.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ModifyformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		String input_no = request.getParameter("no"); 
		if(input_no==null) {
			MvcUtil.redirect("/mysite02/board", request, response);
			return;
		}
		
		Integer no = Integer.valueOf(input_no);
		BoardVo vo = new BoardDao().readContent(no);
		request.setAttribute("contents", vo);
		MvcUtil.forward("board/modify.jsp", request, response);

	}

}
