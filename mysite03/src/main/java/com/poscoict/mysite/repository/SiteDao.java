package com.poscoict.mysite.repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.vo.SiteVo;

@Repository
public class SiteDao {
	
	@Autowired
	private SqlSession sqlSession;

	public SiteVo getSiteInfo() {
		return sqlSession.selectOne("site.getSiteInfo");
	}
	
	public boolean updateSiteInfo(SiteVo vo) {
		System.out.println(vo.toString());
		return sqlSession.update("site.updateMainInfo",vo)==1;
	}
	
	public boolean updateSiteImage(String image_url) {
		return sqlSession.update("site.updateMainImage",image_url)==1;
	}
	
	
	
}
