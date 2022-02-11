package com.poscoict.mysite.exception;

public class FileUploadException extends RuntimeException {
	private static final long searialVersionUID=1L;

	public FileUploadException() {
		super("File UploadError");
	}
	
	public FileUploadException(String msg) {
		super(msg);
	}
}
