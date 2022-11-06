package com.ilhajob.app.reviews;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.reviews.dao.ReviewDAO;
import com.ilhajob.app.reviews.vo.ReviewVO;

public class ReviewWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		ReviewDAO r_dao = new ReviewDAO();
		ReviewVO r_vo = new ReviewVO();
		
		String user_id = (String)session.getAttribute("session_id");
		String month = null;
		
		r_vo.setUser_id(user_id);
		
		if(Integer.parseInt(req.getParameter("month")) > 6) {
			month = "하반기";
		}else {
			month = "상반기";
		}
		r_vo.setReview_interview_date(req.getParameter("year") + "년 " + month);
		r_vo.setReview_company_name(req.getParameter("comp_name"));
		r_vo.setReview_job(req.getParameter("job"));
		r_vo.setReview_career(req.getParameter("career"));
		r_vo.setReview_level(req.getParameter("level"));
		r_vo.setReview_result(req.getParameter("result"));
		r_vo.setReview_type(req.getParameter("type"));
		r_vo.setReview_evaluation(req.getParameter("evaluation"));
		r_vo.setReview_num_of_people(req.getParameter("num_of_people"));
		r_vo.setReview_review(req.getParameter("review"));
		r_vo.setReview_interview_q1(req.getParameter("review_interview_q1"));
		r_vo.setReview_interview_q2(req.getParameter("review_interview_q2"));
		r_vo.setReview_interview_q3(req.getParameter("review_interview_q3"));
		r_vo.setReview_tip(req.getParameter("tip"));
		
		r_dao.insertReview(r_vo);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(req.getContextPath() + "/review/ReviewListAction.re");
		return forward;
	}

}
