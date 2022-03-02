package com.poscoict.mysite.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscoict.mysite.dto.JsonResult;

import ch.qos.logback.classic.Logger;


@ControllerAdvice
public class GlobalExceptionHandler {
	//1. 로깅
			private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);

			@ExceptionHandler(Exception.class)
			public void ExceptionHandler(HttpServletRequest request,HttpServletResponse response,Exception e) throws ServletException, IOException {
				
				LOGGER.info(e.getMessage());
				//1.로깅
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				LOGGER.error(errors.toString());
				
				//2. 요청 구분
				//JSON 요청인 경우 request header의 accept에 application.json
				//HTML 요청인 경우 request header의 accept에 text/html
				
				String accept = request.getHeader("accept");
				if(accept.matches(".*application/json.*")) {
					//3. Json 응답
					JsonResult jsonresult=JsonResult.fail(errors.toString());
				
					String jsonString = new ObjectMapper().writeValueAsString(jsonresult);
					OutputStream out=response.getOutputStream();
					out.write(jsonString.getBytes("utf-8"));
					out.close();
				}else {
					//4 사과 페이지 (HTML 응답, 정상종료)
					
					request.setAttribute("exception", errors.toString());
					request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
				}
				
				
				
				
//				model.addAttribute("exception", errors.toString());
				
				//2. 사과 페이지(HTML 응답, 정상 종료)
//				return "error/exception";
			}
}
