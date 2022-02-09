package com.poscoict.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FileUploadConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public MultipartResolver multiPartResolver() {
		CommonsMultipartResolver MultipartResolver = new CommonsMultipartResolver();
		MultipartResolver.setMaxUploadSize(52428800);
		MultipartResolver.setMaxInMemorySize(52428800);
		MultipartResolver.setDefaultEncoding("UTF-8");
		return MultipartResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:/upload-images/");
	}
}
