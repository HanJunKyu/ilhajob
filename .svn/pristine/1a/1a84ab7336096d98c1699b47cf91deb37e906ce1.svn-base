package com.ilhajob.app.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;

public class UserMyInfoEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		UserDAO u_dao = new UserDAO();
		HttpSession session = req.getSession();
		
		String user_id = (String)session.getAttribute("session_id");
		
		req.setAttribute("user", u_dao.getUser(user_id));
		
		forward.setRedirect(false);
		forward.setPath("/my_info_edit.jsp");
		
		
		return forward;
	}

}
