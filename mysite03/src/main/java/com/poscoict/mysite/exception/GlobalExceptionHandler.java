package com.poscoict.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ch.qos.logback.classic.Logger;


@ControllerAdvice
public class GlobalExceptionHandler {
	//1. 로깅
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public String ExceptionHandler(Model model,Exception e) {
		
		LOGGER.info(e.getMessage());
		
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		model.addAttribute("exception", errors.toString());
		
		
		//2. 사과 페이지(HTML 응답, 정상 종료)
		return "error/exception";
	}
}
