package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.poscoict.mysite.config.ConfigMysite;

public class ConnectionDB {
	public static Connection connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			String url = ConfigMysite.URL;
			String user =  ConfigMysite.USER;
			String passwd =  ConfigMysite.PASSWORD;
			conn = DriverManager.getConnection(url, user, passwd);						
		}catch (Exception e) {
			System.out.println("MYSQL 연결 실패");
			System.out.print("사유 : " + e.getMessage());
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null) 
				conn.close();
		} catch (Exception e) {
			System.out.println("MYSQL 닫기 실패");
			System.out.print("사유 : " + e.getMessage());
		}
	}
}
