package com.poscoict.mysite.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.guest_dao;
import com.poscoict.mysite.vo.GuestbookVO;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class InsertAction implements Action {
	public guest_dao dao =new guest_dao();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		System.out.println(" 실행 ");
		String name = (String)request.getParameter("name");
		String password = (String)request.getParameter("password");
		String message = (String)request.getParameter("message");
		dao.insert(GuestbookVO.builder().name(name).password(password).message(message).build());
	
		
		MvcUtil.redirect("http://localhost:8080/mysite02/guest",request,response);
	}

}
