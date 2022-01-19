package com.poscoict.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.CommDao;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class DeleteCommnetAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		String input_no = request.getParameter("no");
		String input_bd_sn = request.getParameter("bd_sn");
		if(input_no==null) {
			MvcUtil.redirect("/mysite02/board", request, response);
			return;
		}
		
		if(input_no.isEmpty()||input_no.isBlank()) {
			MvcUtil.redirect("/mysite02/board", request, response);
			return;
		}
		
		Integer no = Integer.valueOf(input_no);
	
		new CommDao().DeleteOne(no);
	
		
		if(input_bd_sn==null) {
			MvcUtil.redirect("/mysite02/board", request, response);
			return;
		}
		if(input_bd_sn.isEmpty()||input_bd_sn.isBlank()) {
			MvcUtil.redirect("/mysite02/board", request, response);
			return;
		}
		
		
		MvcUtil.redirect("/mysite02/board?a=viewform&board_sn="+input_bd_sn, request, response);
	}

}
