package com.poscoict.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.poscoict.mysite.repository.CommDao;
import com.poscoict.mysite.vo.CommVo;

@Service
public class CommService {

	@Autowired
	CommDao commdao;
	
	public void getComm(Integer no,Model model) {
		CommVo vo = CommVo.builder().comm_bd_sn(no).build();

		model.addAttribute("comments", new CommDao().SelectList(vo));
	}

	public boolean insert(CommVo vo) {
		if(vo==null) throw new RuntimeException("댓글 삽입 실패");
		
		commdao.insertComment(vo);
		
		return true;
	}

	public boolean delete(Integer no) {
		if(no==null)throw new RuntimeException("삭제 실패");
		
		commdao.DeleteOne(no);
		return true;
	}
}
