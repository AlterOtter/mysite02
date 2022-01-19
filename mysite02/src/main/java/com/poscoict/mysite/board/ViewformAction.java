package com.poscoict.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.dao.CommDao;
import com.poscoict.mysite.vo.CommVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ViewformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		String board_num = request.getParameter("board_sn");
		if(board_num==null) {
			MvcUtil.redirect("/mysite02/board", request, response);
			return;
		}
		
		//조회수 처리
		ReadCount(request,response);
		
		
		Integer sn = Integer.valueOf(board_num);
		CommVo vo = CommVo.builder().comm_bd_sn(sn).build();
		request.setAttribute("content", new BoardDao().readContent(sn));
		request.setAttribute("comments", new CommDao().SelectList(vo));
		
		
		
		MvcUtil.forward("board/view.jsp", request, response);

	}
	
	
	
	public void ReadCount(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String board_num = request.getParameter("board_sn");
		boolean check=true;
		if(cookies != null && cookies.length > 0) { //쿠키가 있으면
			for(Cookie cookie : cookies) { //쿠키에서 꺼내서 검사
				if(board_num.equals(cookie.getName())) { //쿠기 이름이 board_num하고 같은게 있으면
					check=false;
					break; 
				}
			}
			
		}
			// 쿠키 쓰기(굽기)
		if(check) {
			new BoardDao().addViewCount(Integer.parseInt(board_num));
			Cookie cookie = new Cookie(board_num, String.valueOf(board_num));
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(24 * 60 * 60); // 1 day	
			response.addCookie(cookie);
		}
		
			
		
		
		
		
	}

}
