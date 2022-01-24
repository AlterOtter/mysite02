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
}
