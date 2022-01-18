package com.poscoict.mysite.mvc.user;

import com.poscoict.mysite.board.WriteAction;
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
		}else if("updateform".equals(actionName)) {
			action = new UpdateFormAction();
		}else if("update".equals(actionName)) {
			action = new UpdateAction();
		}else {
			action=new UserAction();
		}
		
		
		return action;
	}

}
