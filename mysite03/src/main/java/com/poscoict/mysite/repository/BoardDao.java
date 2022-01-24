package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.GuestbookVO;
import com.poscoict.mysite.vo.UserVo;

@Repository
public class BoardDao {
	
	public List<BoardVo> SelectList(int pagenum) {
		pagenum-=1;
		pagenum=pagenum*10;
		List<BoardVo> list =new ArrayList<>();
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("select b.no as "
				+ "'no',b.title as 'title',u.name as 'name' ,b.reg_date as "
				+ "'reg_date',g_no,o_no,depth,hit,user_no  from board as b join user as u"
				+ " on b.user_no=u.no order by g_no,o_no limit ?,10");){
			pstmt.setInt(1, pagenum);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				BoardVo vo= BoardVo.builder()
						.no(rs.getInt("no"))
						.title(rs.getString("title"))
						.userName(rs.getString("name"))
						.hit(rs.getInt("hit"))
						.groupNo(rs.getInt("g_no"))
						.orderNo(rs.getInt("o_no"))
						.depth(rs.getInt("depth"))
						.regDate(rs.getString("reg_date"))
						.userVo(UserVo.builder().no(rs.getInt("user_no")).build())
						.build();				
				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return list;
	}
	
	
	public List<String> pageCount() {
		List<String> list =new ArrayList<>();
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("select count(*) as count from board ;");){
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Integer pageCount = rs.getInt("count");
				Integer resultCount=pageCount/10;
				if(pageCount%10>=1) {
					resultCount+=1;
				}
				
				for(int i=1;i<resultCount+1;i++) {
					list.add("/mysite03/board?page="+i);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return list;
	}
	

	
	public BoardVo readContent(Integer board_sn){
		BoardVo vo=null;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("select* from board where no=?;");){
			pstmt.setInt(1, board_sn);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				vo= BoardVo.builder()
						.no(rs.getInt("no"))
						.title(rs.getString("title"))
						.contents(rs.getString("contents"))
						.hit(rs.getInt("hit"))
						.groupNo(rs.getInt("g_no"))
						.orderNo(rs.getInt("o_no"))
						.depth(rs.getInt("depth"))
						.regDate(rs.getString("reg_date"))
						.userVo(UserVo.builder().no(rs.getInt("user_no")).build())
						.build();		
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return vo;
	}
	
	public Integer MaxGroupCount() {
		Integer max=0;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("select ifnull(max(g_no),0) as max from board;");){
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				max= rs.getInt("max");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return max;
	}


	public boolean insertNewContent(BoardVo vo) {
		boolean result = true;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `webdb`.`board` (`title`, `contents`, `g_no`, `o_no`, `depth`, `reg_date`, `user_no`) VALUES (?, ?, ?, 1, 1, now(), ?);");){
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getContents());
			pstmt.setInt(3,vo.getGroupNo());
			pstmt.setInt(4, vo.getUserVo().getNo());
			pstmt.executeUpdate();			
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return result;
	}
	
	public boolean insertReplyContent(BoardVo vo) {
		boolean result = true;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `webdb`.`board` (`title`, `contents`, `g_no`, `o_no`, `depth`, `reg_date`, `user_no`) VALUES (?, ?, ?, ?, ?, now(), ?);");){
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getContents());
			pstmt.setInt(3,vo.getGroupNo());
			pstmt.setInt(4,vo.getOrderNo());
			pstmt.setInt(5, vo.getDepth());
			pstmt.setInt(6, vo.getUserVo().getNo());
			pstmt.executeUpdate();			
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return result;
	}
	
	public List<BoardVo> GetOrderList(BoardVo input) {
		List<BoardVo> list =new ArrayList<>();
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("select * from board where o_no>=? and g_no=?;");){
			pstmt.setInt(1, input.getOrderNo());
			pstmt.setInt(2, input.getGroupNo());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				BoardVo vo= BoardVo.builder()
						.no(rs.getInt("no"))
						.groupNo(rs.getInt("g_no"))
						.orderNo(rs.getInt("o_no"))
						.build();				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return list;
	}
	
	public boolean AddorderCount(Integer sn) {
		boolean result = true;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("UPDATE `webdb`.`board` SET `o_no` = o_no+1 WHERE (`no` = ?);");){
			pstmt.setInt(1,sn);
			pstmt.executeUpdate();			
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return result;
		
		
	}
	
	public boolean DeleteOne(Integer sn) {
		boolean result = true;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM `webdb`.`board` WHERE (`no` = ?);");){
			pstmt.setInt(1,sn);
			pstmt.executeUpdate();			
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return result;
	}

	public boolean addViewCount(int bd_sn) {
		boolean result = false;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("update board as bd set bd.hit=IFNULL(bd.hit,0)+1 where no =?;")) {
			pstmt.setInt(1, bd_sn);
			pstmt.executeUpdate();		
			result = true;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		ConnectionDB.close(conn);
		return result;
	}	
	
	// 검색 기능 관련 ㅋㅋ
	public List<BoardVo> SerachList(String input,Integer pagenum){
		pagenum-=1;
		pagenum=pagenum*10;
		List<BoardVo> list =new ArrayList<>();
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("select b.no as "
				+ "'no',b.title as 'title',u.name as 'name' ,b.reg_date as "
				+ "'reg_date',g_no,o_no,depth,hit  from board as b join user as u"
				+ " on b.user_no=u.no where b.title like  '%"+input+"%' or contents like '%"+input+"%' order by g_no,o_no limit ?,10");){
			pstmt.setInt(1, pagenum);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				BoardVo vo= BoardVo.builder()
						.no(rs.getInt("no"))
						.title(rs.getString("title"))
						.userName(rs.getString("name"))
						.hit(rs.getInt("hit"))
						.groupNo(rs.getInt("g_no"))
						.orderNo(rs.getInt("o_no"))
						.depth(rs.getInt("depth"))
						.regDate(rs.getString("reg_date"))
						.build();				
				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return list;
		
	}


	public List<String> searchpageCount(String input) {
		List<String> list =new ArrayList<>();
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("select count(*) as count from board where title like '%"+input+"%' or contents like '%"+input+"%';");){
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Integer pageCount = rs.getInt("count");
				Integer resultCount=pageCount/10;
				if(pageCount%10>=1) {
					resultCount+=1;
				}
				
				for(int i=1;i<resultCount+1;i++) {
					list.add("/mysite03/board?page="+i+"&input="+input);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return list;
	}

	public Integer searchCount(String input) {
		Integer pageCount=0;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("select count(*) as count from board where title like '%"+input+"%' or contents like '%"+input+"%';");){
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				pageCount = rs.getInt("count");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return pageCount;
	}

	public boolean updateOne(BoardVo vo) {
		boolean result = true;
		Connection conn = ConnectionDB.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("UPDATE `webdb`.`board` SET `title` = ?, `contents` = ? WHERE (`no` = ?);");){
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3,vo.getNo());
			pstmt.executeUpdate();			
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}
		ConnectionDB.close(conn);
		return result;
		
	}
	
	
}
