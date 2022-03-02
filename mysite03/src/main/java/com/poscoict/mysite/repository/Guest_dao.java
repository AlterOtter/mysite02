package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.GuestbookVO;

@Repository
public class Guest_dao {
	

	
	@Autowired
	private SqlSession sqlSession;



	public List<GuestbookVO> select2(){
		return sqlSession.selectList("guest.selectList");
	}

	public boolean insert2(GuestbookVO vo) {
		
		return sqlSession.insert("guest.Insert",vo)==1;
	}
	
	public boolean delete2(GuestbookVO vo) {
		return sqlSession.insert("guest.delete",vo)==1;
	}
	
	public List<GuestbookVO> selectlimit(Integer no){
		if(no == null||no == -1 ) {
			return sqlSession.selectList("guest.selectinit");
			
		}else {
			return sqlSession.selectList("guest.selectlimit",no);
		}
	}
	

	
}
