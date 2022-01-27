package com.poscoict.mysite.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

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
			e.printStackTrace();
		}
		return false;
		
	}
	
	//==========================================================================================
	
	public List<BoardVo> getList(Integer page) {
		
		if(page==null)throw new RuntimeException("page error");
		
	
		return boarddao.SelectList2(page);
	}

	public Integer seacrhCotentcount(String input) {
		
		if(input.isEmpty()||input.isBlank())input="";
		
		return boarddao.searchCount2(input);
	}

	public List<String> pageCount() {
		
		return  boarddao.pageCount2();
	}

	public List<BoardVo> searchList(String input,Integer page) {

		
		return boarddao.SearchList2(input,page);
	}

	public Integer searchCount(String input) {
		return boarddao.searchCount2(input);
	}

	public List<String> searchPageCount(String input) {
	
		return boarddao.searchpageCount2(input);
	}


	// ======================================End List================================================
	
	// ======================================Delete List================================================
	public boolean delete(Integer no) {
		if(no==null)throw new RuntimeException("게시물 삭제 실패");
		
		
		boarddao.DeleteOne2(no);
		return false;
	}
	// ======================================END Delete List================================================
	
	
	// ======================================Write List================================================
	public boolean write(BoardVo vo) {
		int o_no=1;
		int depth=1;
		int hit =1;
		Integer group_num = (boarddao).MaxGroupCount2()+1;
		BoardVo insertvo = BoardVo.builder()
				.title(vo.getTitle())
				.contents(vo.getContents())
				.groupNo(group_num)
				.orderNo(o_no).depth(depth)
				.hit(hit)
				.build();
		insertvo.setUser_no(vo.getUser_no());
		return boarddao.insertNewContent2(insertvo);
	}
	// ======================================Write List================================================
	
	public boolean getContents(Integer no, Model model) {
		boarddao.addViewCount2(no);
		model.addAttribute("content", boarddao.readContent2(no));
		return true;
	}
	
	public void update(BoardVo vo) {
		if(vo==null||vo.getNo()==null) throw new RuntimeException(" 오류 발생 번호 확인");
		
		boarddao.updateOne2(vo);
	}

	public void writeReply(BoardVo vo) {
		if(vo.getOrderNo()==null||vo.getGroupNo()==null)throw new RuntimeException("Cant find Order no and Group no");
		vo.setDepth(vo.getDepth()+1);
		vo.setOrderNo(vo.getOrderNo()+1);
		List<BoardVo> o_list = boarddao.GetOrderList2(vo);
		
		for(BoardVo temp:o_list) {
			boarddao.AddorderCount2(temp.getNo());
		}
		
		boarddao.insertReplyContent2(vo);
	
	}
	
	
	
	
	
	
	
}
