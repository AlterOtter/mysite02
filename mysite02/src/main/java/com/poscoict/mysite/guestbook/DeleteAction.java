package com.poscoict.mysite.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.guest_dao;
import com.poscoict.mysite.vo.GuestbookVO;
import com.poscoict.web.mvc.Action;

public class DeleteAction implements Action {
	public guest_dao  dao = new guest_dao();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		int no = Integer.parseInt(request.getParameter("no"));
		String password =(String)request.getParameter("password");
		dao.delete(GuestbookVO.Builder().no(no).password(password).build());
		response.sendRedirect("/mysite02/guest");

	}

}
