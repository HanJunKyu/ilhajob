package com.ilhajob.app.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;

public class UserLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		ActionForward forward = new ActionForward();
		
		UserDAO u_dao = new UserDAO();
		
		String id = req.getParameter("user_id");
		String pw = req.getParameter("user_pw");
		
		if(u_dao.login(id, pw)) {
			session.setAttribute("session_id", id);
			session.setAttribute("type", "user");
			forward.setRedirect(true);
			forward.setPath(req.getContextPath()+"/user/Main.us");
		}else {
			forward.setRedirect(false);
			forward.setPath("/login.jsp?login=false");
		}
		
		
		return forward;
	}

}
