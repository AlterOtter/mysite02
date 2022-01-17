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

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		HttpSession sess= request.getSession();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		int no =  Integer.parseInt(request.getParameter("no"));
		String password = request.getParameter("password");
		
		
		UserVo vo = UserVo.builder().name(name).email(email).gender(gender).no(no).password(password).build();
		
		if(password.isBlank()) {
			new UserDao().update(vo);
		}else {
			new UserDao().updateWithPassword(vo);
		}
		
		UserVo authvo = new UserDao().findByNo(no);
		
		
		sess.setAttribute("authvo", authvo);
		
		
		MvcUtil.redirect("/mysite02/main", request, response);
	}

}
