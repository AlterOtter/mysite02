package com.poscoict.web.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MvcUtil {
	public static void forward(String path,HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		req.getRequestDispatcher("/WEB-INF/views/"+path).forward(req, res);
	}
	
	public static void redirect(String path,HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		res.sendRedirect(path);
	}
}
