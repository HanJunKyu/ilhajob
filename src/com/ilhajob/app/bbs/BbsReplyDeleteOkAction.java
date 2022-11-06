package com.ilhajob.app.bbs;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.bbs.dao.BbsDAO;

public class BbsReplyDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		BbsDAO r_dao = new BbsDAO();
		ActionForward forward = new ActionForward();
		
		int bbs_reply_num = Integer.parseInt(req.getParameter("bbs_reply_num"));
		int bbs_num = Integer.parseInt(req.getParameter("seq"));
		
		r_dao.deleteBbsReply(bbs_reply_num);
		
		forward.setRedirect(true);
		forward.setPath(req.getContextPath() + "/bbs/BbsView.bo?seq=" + bbs_num);
		
		return forward;
	}

}
