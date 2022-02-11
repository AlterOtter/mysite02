package com.poscoict.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;

@Configuration
public class MessageConfig {
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messagesource = new ResourceBundleMessageSource();
		messagesource.setBasename("com/poscoict/mysite/config/web/messages/messages_ko");
		messagesource.setDefaultEncoding("UTF-8");
		return messagesource;
	}
	
	
}
