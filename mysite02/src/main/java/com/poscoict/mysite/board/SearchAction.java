package com.poscoict.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class SearchAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		String input_value= request.getParameter("input");
		String str = request.getParameter("page");
		int pagenum = 1;
		
		if(str==null) {
			request.setAttribute("list", new BoardDao().SerachList(input_value,pagenum));  
		}else{
			pagenum = Integer.parseInt(str);
			request.setAttribute("list", new BoardDao().SerachList(input_value,pagenum));  
		}
		
		request.setAttribute("pages", new BoardDao().searchpageCount(input_value));
		MvcUtil.forward("board/list.jsp", request, response);
		
	}

}
