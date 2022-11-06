package com.ilhajob.app.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserVO;

public class UserFindIdOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		
		UserDAO u_dao = new UserDAO();
		
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		List<UserVO> user_id = u_dao.findId(name, phone);
		if(user_id.size() != 0) {
			req.setAttribute("user_id", user_id);
			req.setAttribute("user_name", name);
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/found_id.jsp");
		return forward;
	}

}
