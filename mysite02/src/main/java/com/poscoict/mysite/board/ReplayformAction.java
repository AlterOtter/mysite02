package com.poscoict.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ReplayformAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		Integer no = Integer.valueOf(request.getParameter("no"));

		BoardVo vo=new BoardDao().readContent(no);
		
		request.setAttribute("content",vo);
		MvcUtil.forward("board/reply.jsp", request, response);
	}
	
	
}
