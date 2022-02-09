package com.poscoict.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.exception.FileUploadException;
import com.poscoict.mysite.repository.SiteDao;
import com.poscoict.mysite.vo.SiteVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SiteService {
	
	@Autowired
	private SiteDao sitedao;
	
	@Autowired
	private ServletContext servletcontext;
	
	private String SAVE_PATH="/upload-images";
	private String URL_BASE="/images";
	
	
	public SiteVo getSiteInfo() {
		
		return sitedao.getSiteInfo();
	}
	
	//이미지 URL을 뺸 나머지 업데이트
	public boolean update_MainInfo(SiteVo vo) {
		
		
		return sitedao.updateSiteInfo(vo);
	}
	
	// 파일이 있을 경우 저장
	public boolean update_MainPicture(MultipartFile multipartFile) {
		
		String fileurl=restore(multipartFile);
		
		if(fileurl==null||fileurl.isEmpty()) {
			return false;
		}
			
		boolean upload_result =sitedao.updateSiteImage(fileurl);
		
		if(upload_result==false) {
			throw new FileUploadException("Fail to Upload File");
		}
		
		
		
		return upload_result;
	}

	
	
	public boolean update(SiteVo vo, MultipartFile multipartFile) {
		try {
			if(multipartFile !=null)
				update_MainPicture(multipartFile);
			
			
			if(vo != null)
				update_MainInfo(vo);
			
			servletcontext.setAttribute("siteVo", sitedao.getSiteInfo());
		}catch(FileUploadException e){
			log.error(e.getMessage());
			return false;
		}
	
		
		
		return true;
	}


	
	//
	//파일 저장 함수
	//
	public String restore(MultipartFile multipartFile) {
		String url = null;
		
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			String originFilename = multipartFile.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf('.')+1);
			String saveFilename = genearteSaveFilename(extName);
			long fileSize = multipartFile.getSize();
			
			byte[] data = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(data);
			os.close();
			
			url = URL_BASE + "/" + saveFilename;
			
		} catch(IOException ex) {
			throw new RuntimeException("file upload error:" + ex);
		}
		
		return url;
	}
	
	//
	//파일 이름 생성 함수
	//
	private String genearteSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}
	
}
