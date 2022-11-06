package com.ilhajob.app.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;

public class UserMyMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		UserDAO u_dao = new UserDAO();
		HttpSession session = req.getSession();
		
		String user_id = (String)session.getAttribute("session_id");
		
		if(user_id != null) {
			
			int applyCnt = u_dao.getApplyCnt(user_id);
			int readCnt = u_dao.getReadCnt(user_id);
			int scrapCnt = u_dao.getScrapCnt(user_id);
			
			req.setAttribute("user_name", u_dao.getUser(user_id).getUser_name());
			req.setAttribute("user_photo", u_dao.getUser(user_id).getUser_photo());
			req.setAttribute("applyCnt", applyCnt);
			req.setAttribute("readCnt", readCnt);
			req.setAttribute("scrapCnt", scrapCnt);
			
			forward.setRedirect(false);
			forward.setPath("/my_main.jsp");
		}else {
			forward.setRedirect(false);
			forward.setPath("/login.jsp");
			
		}
		
		return forward;
	}

}
