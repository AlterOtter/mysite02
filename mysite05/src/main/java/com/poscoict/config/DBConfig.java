package com.poscoict.config;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:com/poscoict/mysite/config/app/jdbc.properties")
public class DBConfig {
	
	@Autowired
	private Environment env;
	
	//	Connection Pool DataSource
	@Bean
	public DataSource dataSource() {
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		//" value="org.mariadb.jdbc.Driver"
		datasource.setUrl(env.getProperty("jdbc.url"));
		//jdbc:mysql://192.168.0.73:3307/webdb?characterEncoding=UTF-8&amp;serverTimezone=UTC
		datasource.setUsername(env.getProperty("jdbc.username"));
		datasource.setPassword(env.getProperty("jdbc.password"));
		datasource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class));
		datasource.setMaxActive(env.getProperty("jdbc.maxActive",Integer.class));
		return datasource;
	}
	
	
	
}
