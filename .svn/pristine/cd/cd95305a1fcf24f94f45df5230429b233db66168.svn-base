package com.ilhajob.app.comp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;

public class CompLogOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		ActionForward forward = new ActionForward();
		
		//세션 삭제
		session.invalidate();
		
		//BoardList.bo로 이동
		forward.setRedirect(true);
		forward.setPath(req.getContextPath() + "/user/Main.us");
		return forward;
	}

}
