package com.poscoict.mysite.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class DeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		request.setAttribute("no", request.getParameter("no"));
		MvcUtil.forward("guestbook/deleteform.jsp", request, response);
	}

}
