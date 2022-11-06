package com.ilhajob.app.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.ResumeInfoVO;

public class UserResumeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		UserDAO u_dao = new UserDAO();
		
		String id = (String)session.getAttribute("session_id");
		
		if(u_dao.getResumeInfo(id) != null) {
			req.setAttribute("info", u_dao.getResumeInfo(id));
			req.setAttribute("age", u_dao.getAge(u_dao.getResumeInfo(id).getUser_birth()) );
		}
		
		if(u_dao.getResumeEdu(id).size() != 0) {
			req.setAttribute("edu", u_dao.getResumeEdu(id));
		}
		
		if(u_dao.getResumeCareer(id).size() != 0) {
			req.setAttribute("career", u_dao.getResumeCareer(id));
		}
		
		if(u_dao.getResumeActivity(id).size() != 0) {
			req.setAttribute("activity", u_dao.getResumeActivity(id));
		}
		
		if(u_dao.getResumeLisense(id).size() != 0) {
			req.setAttribute("lisense", u_dao.getResumeLisense(id));
		}
		
		if(u_dao.getResumeIntroduce(id) != null) {
			req.setAttribute("introduce", u_dao.getResumeIntroduce(id));
		}
		
		if(u_dao.getResumeMilitary(id) != null) {
			req.setAttribute("military", u_dao.getResumeMilitary(id));
		}
		if(u_dao.getResumePortfolio(id).size() != 0) {
			req.setAttribute("portfolio", u_dao.getResumePortfolio(id));
		}
		if(u_dao.getResumeUrl(id).size() != 0) {
			req.setAttribute("url", u_dao.getResumeUrl(id));
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/my_resume.jsp");
		return forward;
	}

}
