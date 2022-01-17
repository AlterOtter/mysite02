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
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		int no =  Integer.parseInt(request.getParameter("no"));
	
		UserVo vo = UserVo.builder().name(name).email(email).gender(gender).no(no).build();
		System.out.println(vo.toString());
		if(new UserDao().update(vo)) {
			HttpSession sess= request.getSession();
			sess.removeAttribute("authvo");
			sess.setAttribute("authvo", vo);
			MvcUtil.redirect("/mysite02/main", request, response);
		}else {
			MvcUtil.redirect("/mysite02/main", request, response);
		}

	}

}
