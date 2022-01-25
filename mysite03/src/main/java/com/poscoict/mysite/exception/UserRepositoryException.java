package com.poscoict.mysite.exception;

import java.sql.SQLException;

public class UserRepositoryException extends RuntimeException{
	public UserRepositoryException() {
		super("SQL Error");
	}
	
	public UserRepositoryException(String msg) {
		super(msg);
	}
	
}
