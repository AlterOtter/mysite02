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
		}else if("write".equals(actionName)) {
			action=new WriteAction();
		}else if("replyform".equals(actionName)) {
			action =new ReplayformAction();
		}else if("reply".equals(actionName)) {
			action = new ReplyAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else if("search".equals(actionName)) {
			action = new SearchAction();
		}else {
			action=new BoardAction();
		}
		
		
		return action;
	}

}
