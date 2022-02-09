package com.poscoict.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.poscoict.config.DBConfig;
import com.poscoict.config.MyBatisConfig;
import com.poscoict.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.poscoict.mysite.service", "com.poscoict.mysite.repository", "com.poscoict.mysite.aspect"})
@Import({DBConfig.class,MyBatisConfig.class,SecurityConfig.class})
public class AppConfig {

}
