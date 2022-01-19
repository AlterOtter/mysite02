package com.poscoict.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.CommDao;
import com.poscoict.mysite.vo.CommVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class WriteCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		String input_bd_sn =request.getParameter("no");
		String input_mem_sn =request.getParameter("mem_sn");
		String comment =request.getParameter("comment");
		
		if(input_bd_sn.isEmpty()||input_mem_sn.isEmpty()) {
			MvcUtil.redirect("/mysite02/board", request, response);
		}
		
		Integer bd_sn = Integer.parseInt(input_bd_sn);
		Integer mem_sn= Integer.parseInt(input_mem_sn);
		
		CommVo vo = CommVo.builder()
				.comm_bd_sn(bd_sn)
				.comm_mem_sn(mem_sn)
				.comm_content(comment)
				.build();
		
		new CommDao().insertComment(vo);
		
		MvcUtil.redirect("/mysite02/board?a=viewform&board_sn="+input_bd_sn, request, response);

	}

}
