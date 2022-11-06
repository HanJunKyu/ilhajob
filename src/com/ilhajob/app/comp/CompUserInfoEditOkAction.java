package com.ilhajob.app.comp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.comp.vo.CompVO;

public class CompUserInfoEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		CompDAO c_dao = new CompDAO();
		
		String user_id = (String)session.getAttribute("session_id");
		CompVO comp = c_dao.getCompInfo(user_id);
		
		comp.setComp_user_name(req.getParameter("name"));
		comp.setComp_email(req.getParameter("email"));
		comp.setComp_phone(req.getParameter("phone"));
		
		c_dao.editCompUserInfo(comp);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/comp/CompInfo.co");
		
		return forward;
	}

}
