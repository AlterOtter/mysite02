package com.poscoict.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Integer mem_sn = Integer.valueOf(request.getParameter("no"));
		Integer group_num = (new BoardDao()).MaxGroupCount()+1;
		int o_no=1;
		int depth=1;
		int hit =1;
		
		BoardVo vo = BoardVo.builder().title(title).contents(content).groupNo(group_num).orderNo(o_no).depth(depth).hit(hit).userVo(UserVo.builder().no(mem_sn).build()).build();
		new BoardDao().insertNewContent(vo);
		
		
		
		
		MvcUtil.redirect("/mysite02/board", request, response);

	}

}
