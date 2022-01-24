package com.poscoict.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.UserDao;
import com.poscoict.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userdao;
	
	public UserVo LoginService(UserVo vo) {
		
		if(vo==null || vo.getEmail()==null || vo.getPassword()==null) {
			throw new RuntimeException("Check Your ID or Password");
		}
		
		
		return userdao.login(vo);
	}

	public boolean updateService(UserVo vo) {
		boolean result=false;
		
		if(vo==null || vo.getEmail()==null || vo.getName()==null) {
			throw new RuntimeException("Check Your ID or Password");
		}
		
		if(vo.getPassword()==null||vo.getPassword().isBlank()) {
			result = userdao.update(vo);
		}else {
			result = userdao.updateWithPassword(vo);
		}
		
		return result;
	}

	public UserVo updateFormInfo(UserVo vo) {
		
		if(vo==null|| vo.getNo()==null) {
			throw new RuntimeException("Check Your Login");
		}
		
		return userdao.findByNo(vo.getNo());
	}

	public boolean join(UserVo vo) {
		if(vo==null||vo.getEmail()==null||vo.getPassword()==null||vo.getGender()==null) {
			throw new RuntimeException("회원 가입 실패 ! 입력값 오류 !"+vo.toString());
		}
		
		userdao.insert(vo);
		
		return userdao.insert(vo);
	}
}
