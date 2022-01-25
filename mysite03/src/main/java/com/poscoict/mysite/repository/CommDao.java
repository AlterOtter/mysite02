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

import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.CommVo;

@Repository
public class CommDao {
	
	@Autowired
	private DataSource datasource;
	@Autowired
	private SqlSession sqlSession;
	
	public List<CommVo> SelectList(CommVo vo) {
		List<CommVo> list =new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt =null;
		try {
			conn=datasource.getConnection();
			pstmt = conn.prepareStatement("select * from comment_tb join user on comm_mem_sn = user.no where comm_bd_sn=?;");
			pstmt.setInt(1, vo.getComm_bd_sn());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				CommVo temp= CommVo.builder()
									.comm_sn(rs.getInt("comm_sn"))
									.comm_bd_sn(rs.getInt("comm_bd_sn"))
									.comm_mem_sn(rs.getInt("comm_mem_sn"))
									.comm_content(rs.getString("comm_content"))
									.comm_date(rs.getString("comm_date"))
									.mem_nm(rs.getString("name"))
									.build();				
				
				list.add(temp);
			}
		}catch(Exception e){
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
		return list;
	}
	
	public boolean insertComment(CommVo vo) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt =null;
		try {
			conn=datasource.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO `webdb`.`comment_tb` (`comm_mem_sn`, `comm_bd_sn`, `comm_content`, `comm_date`) VALUES (?, ?, ?, now());");
			pstmt.setInt(1, vo.getComm_mem_sn());
			pstmt.setInt(2, vo.getComm_bd_sn());
			pstmt.setString(3, vo.getComm_content());
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
	
	
	public boolean DeleteOne(Integer sn) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt =null;
		try {
			conn=datasource.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM `webdb`.`comment_tb` WHERE (`comm_sn` = ?);");
			pstmt.setInt(1,sn);
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

}
