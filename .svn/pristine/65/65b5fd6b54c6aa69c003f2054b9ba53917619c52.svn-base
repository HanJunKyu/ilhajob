package com.ilhajob.app.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserVO;

public class UserMyInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		UserDAO u_dao = new UserDAO();
		String user_id = (String)session.getAttribute("session_id");
		String saveFolder = "/Users/yulim/Documents/study/(6) JSP/workspace/ilhajob/WebContent/app/upload";
		//String saveFolder = "C:\\web\\temp\\workspace\\ilhajob\\WebContent\\app\\upload";
		
		if (user_id != null) {
			
			UserVO u_vo = u_dao.getUser(user_id);
			
			req.setAttribute("user_name", u_vo.getUser_name());
			req.setAttribute("user_id", u_vo.getUser_id());
			req.setAttribute("user_phone", u_vo.getUser_phone());
			req.setAttribute("user_email", u_vo.getUser_email());
			req.setAttribute("user_photo", u_vo.getUser_photo());
			req.setAttribute("path", saveFolder);
		
			forward.setRedirect(false);
			forward.setPath("/my_info.jsp");
		}else {
			forward.setRedirect(false);
			forward.setPath("/login.jsp");
			
		}
		
		
		return forward;
	}

}
