package com.ilhajob.app.comp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.user.dao.UserDAO;

public class CompInfoEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();
		CompDAO c_dao = new CompDAO();
		HttpSession session = req.getSession();

		String user_id = (String) session.getAttribute("session_id");
		req.setAttribute("comp", c_dao.getCompInfo(user_id));
		req.setAttribute("comp_doe", c_dao.getDoe(user_id));

		forward.setRedirect(false);
		forward.setPath("/comp_info_edit.jsp");

		return forward;
	}

}
