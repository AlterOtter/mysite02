package com.poscoict.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.poscoict.mysite.repository.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.CommVo;
import com.poscoict.mysite.vo.UserVo;

@Service
public class BoardService {

	@Autowired
	BoardDao boarddao;
	
	
	//=================================Board List 얻어오기==============================================

	public boolean GetBoadList(Integer page,String input,Model model) {

		if(page==null)page=1;
		
		try {
			if(input==null) {
				model.addAttribute("list",getList(page));
				model.addAttribute("cnt", seacrhCotentcount(""));
				model.addAttribute("pages",pageCount());
			}else {
				model.addAttribute("list", searchList(input,page));  
				model.addAttribute("cnt", searchCount(input));
				model.addAttribute("pages", searchPageCount(input));
			}
			model.addAttribute("page", page);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		
		}
		
	}
	
	//==========================================================================================
	
	public List<BoardVo> getList(Integer page) {
		
		if(page==null)throw new RuntimeException("page error");
		
		return boarddao.SelectList(page);
	}

	public Object seacrhCotentcount(String input) {
		
		if(input.isEmpty()||input.isBlank())input="";
		
		return new BoardDao().searchCount(input);
	}

	public List<String> pageCount() {
		
		return  new BoardDao().pageCount();
	}

	public List<BoardVo> searchList(String input,Integer page) {

		
		return new BoardDao().SerachList(input,page);
	}

	public Integer searchCount(String input) {
		return new BoardDao().searchCount(input);
	}

	public List<String> searchPageCount(String input) {
	
		return new BoardDao().searchpageCount(input);
	}


	// ======================================End List================================================
	
	// ======================================Delete List================================================
	public boolean delete(Integer no) {
		if(no==null)throw new RuntimeException("게시물 삭제 실패");
		
		boarddao.DeleteOne(no);
		return false;
	}
	// ======================================END Delete List================================================
	
	
	// ======================================Write List================================================
	public boolean write(BoardVo vo) {
		int o_no=1;
		int depth=1;
		int hit =1;
		Integer group_num = (new BoardDao()).MaxGroupCount()+1;
		BoardVo insetvo = BoardVo.builder()
				.title(vo.getTitle())
				.contents(vo.getContents())
				.groupNo(group_num)
				.orderNo(o_no).depth(depth)
				.hit(hit)
				.userVo(UserVo.builder()
						.no(vo.getNo())
						.build())
				.build();
		
		return new BoardDao().insertNewContent(insetvo);
	}
	// ======================================Write List================================================
	
	public boolean getContents(Integer no, Model model) {
		model.addAttribute("content", boarddao.readContent(no));
		return true;
	}
	
	public void update(BoardVo vo) {
		if(vo==null||vo.getNo()==null) throw new RuntimeException(" 오류 발생 번호 확인");
		
		boarddao.updateOne(vo);
	}

	public void writeReply(BoardVo vo) {
		if(vo.getOrderNo()==null||vo.getGroupNo()==null)throw new RuntimeException("Cant find Order no and Group no");
		vo.setDepth(vo.getDepth()+1);
		vo.setOrderNo(vo.getOrderNo()+1);
		List<BoardVo> o_list = new BoardDao().GetOrderList(vo);
		
		for(BoardVo temp:o_list) {
			new BoardDao().AddorderCount(temp.getNo());
		}
		
		new BoardDao().insertReplyContent(vo);
	
	}
	
	
	
	
	
	
	
}
