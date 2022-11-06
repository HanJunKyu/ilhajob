package com.ilhajob.app.comp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;

public class CompLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		ActionForward forward = new ActionForward();
		
		CompDAO c_dao = new CompDAO();
		
		String id = req.getParameter("comp_id");
		String pw = req.getParameter("comp_pw");
		
		if(c_dao.login(id, pw)) {
			session.setAttribute("session_id", id);
			session.setAttribute("type", "company");
			forward.setRedirect(true);
			forward.setPath(req.getContextPath()+"/comp/Main.co");
		}else {
			forward.setRedirect(false);
			forward.setPath("/comp_login.jsp?login=false");
		}
		
		
		return forward;
	}

}
