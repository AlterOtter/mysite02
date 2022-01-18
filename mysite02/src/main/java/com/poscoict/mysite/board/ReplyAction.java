package com.poscoict.mysite.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException, ServletException {
		Integer mem_no = Integer.valueOf(request.getParameter("mem_no"));
		String title= request.getParameter("title");
		String content =request.getParameter("content");
		Integer no = Integer.valueOf(request.getParameter("no"));
		Integer g_no = Integer.valueOf(request.getParameter("g_no"));
		Integer o_no = Integer.valueOf(request.getParameter("o_no"))+1;
		Integer depth = Integer.valueOf(request.getParameter("depth"))+1;
		
		BoardVo vo = BoardVo.builder()
				.userVo(UserVo.builder().no(mem_no).build())
				.title(title)
				.contents(content)
				.no(no)
				.groupNo(g_no)
				.orderNo(o_no)
				.depth(depth)
				.build();
		List<BoardVo> o_list = new BoardDao().GetOrderList(BoardVo.builder().orderNo(o_no).groupNo(g_no).build());
		
		for(BoardVo temp:o_list) {
			new BoardDao().AddorderCount(temp.getNo());
		}
		
		new BoardDao().insertReplyContent(vo);
		
		MvcUtil.redirect("/mysite02/board", request, response);
		

	}

}
