package com.poscoict.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		Integer no = Integer.valueOf(request.getParameter("no"));
		Integer mem_no = Integer.valueOf(request.getParameter("mem_no"));
		
		new BoardDao().DeleteOne(no);
		
		MvcUtil.redirect("/mysite02/board", request, response);

	}

}
