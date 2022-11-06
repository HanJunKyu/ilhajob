package com.ilhajob.app.comp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.comp.vo.CompVO;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserVO;

public class CompFindIdOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		
		CompDAO c_dao = new CompDAO();
		
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		List<CompVO> user_id = c_dao.findId(name, phone);
		for(CompVO end : user_id) {
			System.out.println(end.getComp_id());
			System.out.println(end.getComp_join_date());
		}
		if(user_id.size() != 0) {
			req.setAttribute("user_id", user_id);
			req.setAttribute("user_name", name);
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/comp_found_id.jsp");
		return forward;
	}

}
