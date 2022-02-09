package com.poscoict.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.poscoict.mysite.security.AuthInterCeptor;
import com.poscoict.mysite.security.AuthUserHandlerMethodArgumentResolver;
import com.poscoict.mysite.security.LoginInteceptor;
import com.poscoict.mysite.security.LogoutInterceptor;

@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter{



	//Arguement Resolver
	@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
		
		return new AuthUserHandlerMethodArgumentResolver();
	}
	
	//
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argument) {
		argument.add(handlerMethodArgumentResolver());
	}
	
	
	//Interceptor
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInteceptor();
	}
	//Interceptor
	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}
	//Interceptor
	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthInterCeptor();
	}	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/user/auth");
		registry.addInterceptor(logoutInterceptor()).addPathPatterns("/user/logout");
		registry.addInterceptor(authInterceptor()).addPathPatterns("/**")
			.excludePathPatterns("/user/auth")
			.excludePathPatterns("/user/logout")
			.excludePathPatterns("/assets/**");
		
	}
}