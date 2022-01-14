package com.poscoict.mysite.mvc.join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class JoinAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MvcUtil.redirect("user/join", request, response);
	}

}
