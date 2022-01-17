package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscoict.mysite.vo.GuestbookVO;

public class guest_dao {
	public boolean insert(GuestbookVO vo) {
		boolean result = true;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `webdb`.`guestbook` (`name`, `password`, `message`, `reg_date`) VALUES (?, ?, ?, now());");){
			pstmt.setString(1,vo.getName());
			pstmt.setString(2,vo.getPassword());
			pstmt.setString(3,vo.getMessage());
			pstmt.executeUpdate();			
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return result;
	}
	
	

	public List<GuestbookVO> select() {
		List<GuestbookVO> volist = new ArrayList<>();
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("select * from guestbook order by no desc;");){
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
		}
		ConnectionDB.close(conn);
		return volist;
	}
	
	public boolean delete(GuestbookVO vo) {
		boolean result = true;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM `webdb`.`guestbook` WHERE (`no` = ? and `password`=?);");){
			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			pstmt.executeUpdate();	
			
		}catch(SQLException e){
			result =false;
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return result;
	}
}
