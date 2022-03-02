package com.poscoict.mysite.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.poscoict.mysite.security.AuthInterCeptor;
import com.poscoict.mysite.security.AuthUserHandlerMethodArgumentResolver;
import com.poscoict.mysite.security.LoginInteceptor;
import com.poscoict.mysite.security.LogoutInterceptor;
import com.poscoict.mysite.security.SiteInterceptor;

@SpringBootConfiguration
@PropertySource("classpath:com/mysite/config/webconfig.properties")
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	private Environment env;
	

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
	
	@Bean
	public HandlerInterceptor siteInterceptor() {
		return new SiteInterceptor();
	}
	

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/user/auth");
		
		registry.addInterceptor(logoutInterceptor()).addPathPatterns("/user/logout");
		
		registry.addInterceptor(authInterceptor()).addPathPatterns("/**")
			.excludePathPatterns("/user/auth")
			.excludePathPatterns("/user/logout")
			.excludePathPatterns("/assets/**")
			.excludePathPatterns("/images/**");
		
		registry.addInterceptor(siteInterceptor())
			.addPathPatterns("/**").excludePathPatterns("/images/**");
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:/upload-images/");
	}
	
	//MessageConvertor
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
		messageConverter.setSupportedMediaTypes(
				Arrays.asList(
						new MediaType("text", "html", Charset.forName("utf-8"))
				)
		);
		return messageConverter;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
				.indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyyy-mm-dd"));
		
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(builder.build());
		messageConverter.setSupportedMediaTypes(
				Arrays.asList(
						new MediaType("application", "json", Charset.forName("utf-8"))
				)
		);
		return messageConverter;		
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
	}
			
	
	
	
}