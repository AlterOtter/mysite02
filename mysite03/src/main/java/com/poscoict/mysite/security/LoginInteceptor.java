package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.service.UserService;
import com.poscoict.mysite.vo.UserVo;

public class LoginInteceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userservice;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo vo = userservice.LoginService(UserVo.builder().email(email).password(password).build());	
		
		if(vo==null) {
			request.setAttribute("result", "fail");
			request.setAttribute("email", email);
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
			return false;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authvo", vo);
		response.sendRedirect(request.getContextPath());
	
		return false;
		
	}

}
