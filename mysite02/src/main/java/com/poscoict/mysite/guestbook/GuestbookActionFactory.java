package com.poscoict.mysite.guestbook;

import com.poscoict.mysite.dao.guest_dao;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory{
	public guest_dao  dao = new guest_dao();
	
	@Override
	public Action getAction(String actionName) {
		Action action =null;
		System.out.println("getaction");
		if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		}else if("insert".equals(actionName)) {
			action = new InsertAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else {
			action =new indexAction();
		}
		
		
		return action;
	}

}
