package com.poscoict.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poscoict.mysite.dto.JsonResult;
import com.poscoict.mysite.service.GuestService;
import com.poscoict.mysite.vo.GuestbookVO;

@RestController("/guestbookApiController")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private GuestService guestservice;
	
	@ResponseBody
	@PostMapping("/add")
	public JsonResult addGuest(@RequestBody GuestbookVO vo) {
		if(guestservice.insert(vo)) {
			System.out.println(vo.toString());
			return JsonResult.success(vo);
		}else {
			return JsonResult.fail("방명록 추가 실패");
		}	
	}
	
	@ResponseBody
	@GetMapping("/list")
	public List<GuestbookVO> getlist(){
		List<GuestbookVO> list = guestservice.getlist();
		return list;
	}
	
	@ResponseBody
	@GetMapping("/list2/{no}")
	public JsonResult getlist2(@PathVariable(value="no", required = false)Integer no ) {
		List<GuestbookVO> list = guestservice.getlist(no);
		return JsonResult.success(list);
	}
	
	@ResponseBody
	@DeleteMapping("/delete/{no}")
	public JsonResult deleteOne(@PathVariable(value="no")Integer no,@RequestParam(value = "password") String password) {
		if(guestservice.delete(GuestbookVO.builder().no(no).password(password).build())) {
			System.out.println(password);
			System.out.println(no);
			return JsonResult.success(no);
		}else {
			return JsonResult.fail(no.toString());
		}
		
	}
	
}
