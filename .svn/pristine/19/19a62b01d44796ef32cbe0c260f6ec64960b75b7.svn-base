package com.ilhajob.app.opening;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.opening.dao.OpeningDAO;

public class OpeningScrapAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		//DAO
		OpeningDAO openingDAO = new OpeningDAO();
		//VO
		//아이디, 공고 넘버 받기
		String user_id=req.getParameter("user_id");
		int opn_num=Integer.parseInt(req.getParameter("opn_num"));
		
		//스크랩 추가
		openingDAO.insertScrap(user_id, opn_num);
		
		return null;
	}

}
