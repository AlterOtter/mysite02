package com.poscoict.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.Guest_dao;
import com.poscoict.mysite.vo.GuestbookVO;

@Service
public class GuestService {

	@Autowired
	private Guest_dao guest_dao; 
	
	public List<GuestbookVO> getlist() {
		return guest_dao.select2();
	}
	
	public List<GuestbookVO> getlist(Integer no) {
		return guest_dao.selectlimit(no);
	}

	public boolean delete(GuestbookVO vo) {
		
		if(vo==null || vo.getNo()==null||vo.getPassword()==null) {
			throw new RuntimeException("Check Board No  or  Password");
		}
		
		return guest_dao.delete2(vo);
	}
	
	public boolean insert(GuestbookVO vo) {
		
		
		return guest_dao.insert2(vo);
	}
	
}
