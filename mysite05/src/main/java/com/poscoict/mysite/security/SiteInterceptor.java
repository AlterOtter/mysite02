package com.poscoict.mysite.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.repository.SiteDao;

public class SiteInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private SiteDao sitedao;
	
	@Autowired
	private ServletContext servletcontext;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(servletcontext.getAttribute("siteVo")==null)servletcontext.setAttribute("siteVo", sitedao.getSiteInfo());			
		return true;
	}
}
