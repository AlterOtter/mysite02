package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.vo.UserVo;

public class AuthInterCeptor  extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		Auth classauth=handlerMethod.getBeanType().getAnnotation(Auth.class);
		//4. Handler Method @Auth 가 없다면 Type에 있는지 확인(과제)
		//5. type과 method에 @Auth 가 적용이 안되어 있는 경우
		if(auth == null) {
			if(classauth==null) {
				return true;
			}
		}
		

		//5. @Auth가 적용이 되어 있기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		UserVo authUser = (UserVo)session.getAttribute("authvo");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		if(classauth != null) {
			if(classauth.role().equals("ADMIN")&&!authUser.getRole().equals("ADMIN")) {
					response.sendRedirect(request.getContextPath() + "/main");
					return false;
			}
		}
		
		return true;
	}
}
