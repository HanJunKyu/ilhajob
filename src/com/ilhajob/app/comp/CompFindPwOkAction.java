package com.ilhajob.app.comp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.user.dao.UserDAO;

public class CompFindPwOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		
		CompDAO c_dao = new CompDAO();
		
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String id = req.getParameter("id");
		
		String user_pw = c_dao.findPw(name, phone, id);
		req.setAttribute("user_name", name);
		if(user_pw != null) {
			req.setAttribute("user_pw", user_pw);
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/comp_found_pw.jsp");
		return forward;
	}

}
