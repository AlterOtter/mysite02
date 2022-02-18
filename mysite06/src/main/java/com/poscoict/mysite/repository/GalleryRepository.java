package com.poscoict.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public List<GalleryVo> getGalleryInfo() {
		return sqlSession.selectList("gallery.SelectList");
	}
	
	public boolean Insert(GalleryVo vo) {
		
		return sqlSession.insert("gallery.insert",vo)==1;
	}
	
	public boolean delete(Long no) {
		return sqlSession.insert("gallery.delete",no)==1;
	}
	
	

}
