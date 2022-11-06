package com.ilhajob.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserVO;

public class UserJoinOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ActionForward forward = null;
		
		UserDAO u_dao = new UserDAO();
		UserVO u_vo = new UserVO();
		
		u_vo.setUser_id(req.getParameter("user_id"));
		u_vo.setUser_pw(req.getParameter("user_pw"));
		u_vo.setUser_name(req.getParameter("user_name"));
		u_vo.setUser_phone(req.getParameter("user_phone"));
		u_vo.setUser_email(req.getParameter("user_email"));
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=utf-8");
		if(!u_dao.join(u_vo)){
			 out.println("<script>");
			 out.println("alert('회원가입 실패. 잠시 후 다시 시도해주세요.')");
			 out.println("</script>");
			 out.close();
		}else {
					
			forward = new ActionForward();
			
			forward.setRedirect(false);
			forward.setPath("/user/UserLogin.us");
		}
		return forward;
	}

}
