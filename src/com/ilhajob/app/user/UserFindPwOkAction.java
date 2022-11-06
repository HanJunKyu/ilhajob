package com.ilhajob.app.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;

public class UserFindPwOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		
		UserDAO u_dao = new UserDAO();
		
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String id = req.getParameter("id");
		
		String user_pw = u_dao.findPw(name, phone, id);
		req.setAttribute("user_name", name);
		if(user_pw != null) {
			req.setAttribute("user_pw", user_pw);
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/found_pw.jsp");
		return forward;
	}

}
