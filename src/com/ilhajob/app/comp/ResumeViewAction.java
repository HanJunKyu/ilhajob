package com.ilhajob.app.comp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.comp.vo.CompApplyVO;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.ResumeInfoVO;
import com.ilhajob.app.user.vo.UserApplyVO;

public class ResumeViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		
		
		UserDAO u_dao = new UserDAO();
		CompDAO c_dao = new CompDAO();
		UserApplyVO a_vo = new UserApplyVO();
		
		int num = Integer.parseInt(req.getParameter("num"));
		String id = req.getParameter("id");
		CompApplyVO c_vo = c_dao.getCompApplyInfo(num);
		
		c_dao.editStatus(num);
		
		a_vo.setApply_area(c_vo.getApply_area());
		a_vo.setJobs_num(c_vo.getJobs_num());
		u_dao.editStatus(a_vo);
		
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
		forward.setPath("/resume.jsp");
		return forward;
	}

}
