package com.poscoict.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.UserDao;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String eamil = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		
		UserVo vo =  UserVo.builder()
				.name(name)
				.email(eamil)
				.password(password)
				.gender(gender)
				.build();
		
		if(new UserDao().insert(vo)) {
			// 회원가입 성공시 이동할 페이지
			MvcUtil.forward("user/joinsuccess.jsp", request, response);
		}else {
			MvcUtil.redirect("/mysite02/user", request, response);
		}
	}
	
}
