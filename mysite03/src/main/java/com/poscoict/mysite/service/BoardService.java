package com.poscoict.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.poscoict.mysite.repository.BoardDao;
import com.poscoict.mysite.vo.BoardVo;

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

	public boolean delete(Integer no) {
		if(no==null)throw new RuntimeException("게시물 삭제 실패");
		
		boarddao.DeleteOne(no);
		return false;
	}

	// ======================================End List================================================
	
	
	
	
	
	
}
