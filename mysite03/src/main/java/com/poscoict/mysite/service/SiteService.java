package com.poscoict.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.repository.SiteDao;
import com.poscoict.mysite.vo.SiteVo;

@Service
public class SiteService {
	
	@Autowired
	private SiteDao sitedao;
	
	private String SAVE_PATH="/upload-images";
	private String URL_BASE="/images";
	
	
	public SiteVo getSiteInfo() {
		
		return sitedao.getSiteInfo();
	}
	
	//이미지 URL을 뺸 나머지 업데이트
	public boolean updateMainInfo(SiteVo vo) {
		
		
		return sitedao.updateSiteInfo(vo);
	}
	
	// 파일이 있을 경우 저장
	public boolean updateMainPicture(MultipartFile multipartFile) {
		
		String fileurl=restore(multipartFile);
		System.out.println(fileurl);
		
		if(fileurl==null||fileurl.isEmpty()) {
			return false;
		}
			
		return sitedao.updateSiteImage(fileurl);
	}

	
	public boolean update(SiteVo vo, MultipartFile multipartFile) {
		
		if(multipartFile !=null || !multipartFile.isEmpty()) {
			updateMainPicture(multipartFile);
		}
		
		if(vo != null) {
			updateMainInfo(vo);
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
