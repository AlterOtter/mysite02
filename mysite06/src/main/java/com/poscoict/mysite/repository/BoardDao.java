package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.exception.UserRepositoryException;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.GuestbookVO;
import com.poscoict.mysite.vo.UserVo;

@Repository
public class BoardDao {
	
	
	@Autowired
	private DataSource datasource;
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> SelectList2(Integer pagenum){
		pagenum-=1;
		pagenum=pagenum*10;	
		return sqlSession.selectList("board.selectList", pagenum);
	}
	public List<BoardVo> SearchList2(String input,Integer pagenum){
		pagenum-=1;
		pagenum=pagenum*10;	
		Map<String, Object> map = new HashMap<>();
		map.put("input", input);
		map.put("page", pagenum);
		
		return sqlSession.selectList("board.SearchList", map);
	}
	public boolean updateOne2(BoardVo vo) {
		return sqlSession.update("board.updateone",vo)==1;
	}
	public Integer searchCount2(String input) {
		Map<String, Object> map = new HashMap<>();
		map.put("input", input);
		return sqlSession.selectOne("board.searchCount", map);
	}
	public List<String> searchpageCount2(String input){
		List<String> list =new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("input", input);
		Integer pageCount=sqlSession.selectOne("board.searchCount", map);
		Integer resultCount=pageCount/10;
		if(pageCount%10>=1) {
			resultCount+=1;
		}
		
		for(int i=1;i<resultCount+1;i++) {
			list.add("/mysite03/board?page="+i+"&input="+input);
		}
		
		return list;
	}
	public boolean addViewCount2(Integer bd_sn) {
		return sqlSession.update("board.addViewCount",bd_sn)==1;
	}
	public boolean DeleteOne2(Integer no) {
		return sqlSession.delete("board.deleteone",no)==1;
	}

	public boolean AddorderCount2(Integer no) {
		return sqlSession.update("board.AddorderCount",no)==1;
	}

	public List<BoardVo> GetOrderList2(BoardVo vo){
		return sqlSession.selectList("board.GetOrderList", vo);
	}

	public boolean insertReplyContent2(BoardVo vo) {
		Map<String, Object> map = new HashMap<>();
		map.put("title", vo.getTitle());
		map.put("contents", vo.getContents());
		map.put("g_no", vo.getGroupNo());
		map.put("o_no", vo.getOrderNo());
		map.put("depth", vo.getDepth());
		map.put("user_no", vo.getUser_no());
		return sqlSession.insert("board.insertReplyContent",map)==1;
	}

	public boolean insertNewContent2(BoardVo vo) {
		Map<String, Object> map = new HashMap<>();
		map.put("title", vo.getTitle());
		map.put("contents", vo.getContents());
		map.put("g_no", vo.getGroupNo());
		map.put("user_no", vo.getUser_no());
		return sqlSession.insert("board.insertNewContent",map)==1;
	}
	
	public Integer MaxGroupCount2() {
		return sqlSession.selectOne("board.MaxGroupCount");
	}
	
	public BoardVo readContent2(Integer no){
		return sqlSession.selectOne("board.readContent",no);
	}
	
	public List<String> pageCount2() {
		List<String> list =new ArrayList<>();
		Integer pageCount = sqlSession.selectOne("board.pageCount");
		Integer resultCount=pageCount/10;
		if(pageCount%10>=1) {
			resultCount+=1;
		}
		
		for(int i=1;i<resultCount+1;i++) {
			list.add("/mysite03/board?page="+i);
		}
		return list;
	}
}
