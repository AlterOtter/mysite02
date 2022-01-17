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

public class UpdateFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		//접근 제어
		HttpSession sess =request.getSession();
		UserVo authUser = (UserVo)sess.getAttribute("authvo");
		if(authUser==null) {
			MvcUtil.redirect(request.getContextPath()+"/user?a=loginform", request, response);
			return;
		}
		
		UserVo vo = new UserDao().findByNo(authUser.getNo()); 
		
		MvcUtil.forward("", request, response)
		
	}

}
