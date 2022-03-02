package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.cj.protocol.Resultset;
import com.poscoict.mysite.exception.UserRepositoryException;
import com.poscoict.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo vo)  {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {	
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO `webdb`.`user` (`name`, `email`, `password`, `gender`, `join_date`) VALUES (?, ?, ?, ?, now());");
			pstmt.setString(1,vo.getName());
			pstmt.setString(2,vo.getEmail());
			pstmt.setString(3,vo.getPassword());
			pstmt.setString(4,vo.getGender());
			pstmt.executeUpdate();	
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
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
	
	public UserVo login(UserVo vo) {
		UserVo result_vo = null;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from user where email=? and password=?");
			pstmt.setString(1,vo.getEmail());
			pstmt.setString(2,vo.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				result_vo= UserVo.builder()
						.no(rs.getInt("no"))
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.password(rs.getString("password"))
						.gender(rs.getString("gender"))
						.join_date("join_date")
						.build();
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
		return result_vo;
	}
	

	public UserVo findByNo(int no) {
		UserVo result_vo = null;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from user where no=?");
			pstmt.setInt(1,no);
	
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				result_vo= UserVo.builder()
						.no(rs.getInt("no"))
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.gender(rs.getString("gender"))
						.join_date("join_date")
						.build();
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
	
		return result_vo;
	}
	
	
	public boolean update(UserVo vo) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("UPDATE `webdb`.`user` SET `name` = ?, `email` = ?, `gender` = ? WHERE (`no` = ?);");
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getGender());
			pstmt.setInt(4,vo.getNo());
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
	
	public boolean updateWithPassword(UserVo vo) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt=null;
		
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("UPDATE `webdb`.`user` SET `name` = ?, `email` = ?, `gender` = ?,`password` =?  WHERE (`no` = ?);");
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getPassword());
			pstmt.setInt(5,vo.getNo());
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


	
	//====================================
	
	public boolean update2(UserVo vo) {
		int count = sqlSession.update("user.update", vo);
		return count == 1;
	}
	
	public boolean insert2(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}	

	public UserVo findByNo2(Long userNo) {
		return sqlSession.selectOne("user.findByNo", userNo);
	}

	public UserVo findByEmailAndPassword2(String email, String password) throws UserRepositoryException {
		Map<String, String> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		
		return sqlSession.selectOne("user.findByEmailAndPassword", map);
	}	
	public UserVo findByEmail(String email) {
		
		return sqlSession.selectOne("user.findByEmail",email);
	}	

	
}
