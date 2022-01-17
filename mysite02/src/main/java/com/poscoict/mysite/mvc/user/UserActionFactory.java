package com.poscoict.mysite.mvc.user;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("joinform".equals(actionName)) {
			action=new JoinformAction();
		}else if("join".equals(actionName)) {
			action =new JoinAction();
		}else if("login".equals(actionName)) {
			action =new LoginAction();
		}else if("logout".equals(actionName)){
			action =new LogoutAction();
		}else {
			action=new UserAction();
		}
		
		
		return action;
	}

}
