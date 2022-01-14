package com.poscoict.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.mvc.main.MainActionFactory;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;


public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String actionName = req.getParameter("a");
		
		ActionFactory af = new MainActionFactory();
		Action action = new MainActionFactory().getAction(actionName);
		action.execute(req,res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
	}

}
