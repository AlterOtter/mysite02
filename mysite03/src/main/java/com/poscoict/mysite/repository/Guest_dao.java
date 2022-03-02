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
	private DataSource datasource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(GuestbookVO vo) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO `webdb`.`guestbook` (`name`, `password`, `message`, `reg_date`) VALUES (?, ?, ?, now());");
			pstmt.setString(1,vo.getName());
			pstmt.setString(2,vo.getPassword());
			pstmt.setString(3,vo.getMessage());
			pstmt.executeUpdate();			
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	

	public List<GuestbookVO> select() {
		List<GuestbookVO> volist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from guestbook order by no desc;");
			ResultSet rs=pstmt.executeQuery();	
			while(rs.next()) {
				GuestbookVO vo = GuestbookVO.builder()
						.no(rs.getInt("no"))
						.name(rs.getString("name"))
						.password(rs.getString("password"))
						.message(rs.getString("message"))
						.reg_date(rs.getString("reg_date"))
						.build();
				volist.add(vo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return volist;
	}
	
	public boolean delete(GuestbookVO vo) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM `webdb`.`guestbook` WHERE (`no` = ? and `password`=?);");
			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			pstmt.executeUpdate();	
			
		}catch(SQLException e){
			result =false;
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}



	public List<GuestbookVO> select2(){
		return sqlSession.selectList("guest.selectList");
	}

	public boolean insert2(GuestbookVO vo) {
		
		return sqlSession.insert("guest.Insert",vo)==1;
	}
	
	public boolean delete2(GuestbookVO vo) {
		return sqlSession.insert("guest.delete",vo)==1;
	}
	

	
}
