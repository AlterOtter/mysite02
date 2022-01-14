package com.poscoict.mysite.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.guest_dao;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class indexAction implements Action{
	public guest_dao  dao = new guest_dao();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setAttribute("list", dao.select());
			MvcUtil.forward("guestbook/list.jsp", request, response);
		
	}

}
