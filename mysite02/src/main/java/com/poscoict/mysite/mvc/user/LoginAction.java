package com.poscoict.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.UserDao;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo vo = UserVo.builder().email(email).password(password).build();
		UserVo res_vo=new UserDao().login(vo);
		if(res_vo==null) {
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			request.setAttribute("result", "fail");
			MvcUtil.forward("user/loginform.jsp", request, response);
			return;
		}else {
			HttpSession sess = request.getSession();
			sess.setAttribute("authvo", res_vo);
			response.sendRedirect("/mysite02/main");
		}

	}

}
