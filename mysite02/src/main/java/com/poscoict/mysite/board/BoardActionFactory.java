package com.poscoict.mysite.board;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("writeform".equals(actionName)) {
			action = new WriteformAction();
		}else if("modifyform".equals(actionName)) {
			action = new ModifyformAction();
		}else if("viewform".equals(actionName)) {
			action = new ViewformAction();
		}else {
			action=new BoardAction();
		}
		
		
		return action;
	}

}
