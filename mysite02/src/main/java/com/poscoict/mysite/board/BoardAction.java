package com.poscoict.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class BoardAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		String str = request.getParameter("page");
		int pagenum = 1;
		
		if(str==null) {
			request.setAttribute("list", new BoardDao().SelectList(pagenum));  
		}else{
			pagenum = Integer.parseInt(str);
			request.setAttribute("list", new BoardDao().SelectList(pagenum));  
		}
		request.setAttribute("page", pagenum);
		request.setAttribute("cnt", new BoardDao().searchCount(""));
		request.setAttribute("pages", new BoardDao().pageCount());
		MvcUtil.forward("board/list.jsp", request, response);
		
	}

}
