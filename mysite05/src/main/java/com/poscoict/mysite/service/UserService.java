package com.poscoict.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.exception.UserRepositoryException;
import com.poscoict.mysite.repository.UserDao;
import com.poscoict.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userdao;
	
	public UserVo LoginService(UserVo vo) {
		
		if(vo==null || vo.getEmail().isBlank() || vo.getPassword().isBlank()) {
			throw new RuntimeException("Check Your ID or Password");
		}
		
		UserVo res= userdao.findByEmailAndPassword2(vo.getEmail(),vo.getPassword());
		
		if(res==null) {
			throw new RuntimeException("Cant find User");
		}
		System.out.println(res.getRole());
		
		return res;
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

	public boolean join(UserVo vo) throws UserRepositoryException {
		if(vo==null||vo.getEmail().isBlank()||vo.getPassword().isBlank()||vo.getGender().isBlank()) {
			throw new RuntimeException("회원 가입 실패 ! 입력값 오류 !"+vo.toString());
		}
		
		return userdao.insert(vo);
	}
	
	public UserVo checkemail(String email) {
		
		return userdao.findByEmail(email);
	}
}
