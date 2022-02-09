package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mysql.cj.Session;

public class LogoutInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession sess  = request.getSession();
		if(sess==null) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		sess.removeAttribute("authvo");
		response.sendRedirect(request.getContextPath());
		return false;
	}
}
