package com.ilhajob.app.opening;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.opening.vo.RecruitmentAreaVO;
import com.ilhajob.app.user.dao.UserDAO;

public class OpeningApplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		OpeningDAO o_dao = new OpeningDAO();
		List<RecruitmentAreaVO> recruitmentAreaList= null;
		HttpSession session = req.getSession();
		UserDAO u_dao = new UserDAO();
		ActionForward forward = null;

		String user_id = (String)session.getAttribute("session_id");

		String opn_num = req.getParameter("opn_num");
		recruitmentAreaList = o_dao.getRecruitmentArea(Integer.parseInt(opn_num));
		//공고를 작성한 기업의 이름 가져오기
		String comp_name=o_dao.getCompNameByOpnNum(opn_num);
		
		if(u_dao.getResumeInfo(user_id) != null) {
			String title = u_dao.getResumeInfo(user_id).getUser_resume_title();
			req.setAttribute("title", title);
		}
		
		req.setAttribute("opn_num", opn_num);
		req.setAttribute("list", recruitmentAreaList);
		req.setAttribute("comp_name", comp_name);
		
		forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/do_apply.jsp");
		
		return forward;
	}

}
