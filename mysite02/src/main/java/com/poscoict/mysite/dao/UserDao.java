package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;
import com.poscoict.mysite.vo.UserVo;

import dao.MySQL;
import vo.BoardVO;

public class UserDao {
	public boolean insert(UserVo vo) {
		boolean result = true;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `webdb`.`user` (`name`, `email`, `password`, `gender`, `join_date`) VALUES (?, ?, ?, ?, now());");){
			pstmt.setString(1,vo.getName());
			pstmt.setString(2,vo.getEmail());
			pstmt.setString(3,vo.getPassword());
			pstmt.setString(4,vo.getGender());
			pstmt.executeUpdate();			
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return result;
	}
	
	public UserVo login(UserVo vo) {
		UserVo result_vo = null;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("select * from user where email=? and password=?");){
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
		}
		ConnectionDB.close(conn);
		return result_vo;
	}
	

	public UserVo findByNo(int no) {
		UserVo result_vo = null;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("select * from user where no=?");){
			pstmt.setInt(1,no);
	
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
		}
		ConnectionDB.close(conn);
		return result_vo;
	}
	
	
	public boolean update(UserVo vo) {
		boolean result = true;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement(
						"UPDATE `webdb`.`user` SET `email` = 'kwonsoonmo2@na1ver.com', `password` = ?, `gender` = ?,  WHERE (`no` = ?););");){
			pstmt.executeUpdate();			
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return result;
	}
}
