package com.poscoict.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.repository.GalleryRepository;
import com.poscoict.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	
	private String SAVE_PATH="/upload-images";
	private String URL_BASE="/images";
	@Autowired
	private GalleryRepository galleryRepository;
	
	public List<GalleryVo> GalleryList(){
		
		return galleryRepository.getGalleryInfo();
	}
	
	
	
	public boolean insertGallery(MultipartFile file,String comment) {
		if(file==null|| file.isEmpty()) {
			return false;
		}
		
		String url=restore(file);
		
		GalleryVo vo = new GalleryVo();
		vo.setComments(comment);
		vo.setUrl(url);
		
		
		return galleryRepository.Insert(vo);
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
