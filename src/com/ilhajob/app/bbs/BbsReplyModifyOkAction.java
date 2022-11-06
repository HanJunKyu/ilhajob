package com.ilhajob.app.bbs;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.bbs.dao.BbsDAO;
import com.ilhajob.app.bbs.vo.BbsReplyVO;

public class BbsReplyModifyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		
		BbsDAO r_dao = new BbsDAO();
		BbsReplyVO r_vo = new BbsReplyVO();
		ActionForward forward = new ActionForward();
		
	
		int bbs_reply_num = Integer.parseInt(req.getParameter("bbs_reply_num"));
		int bbs_num = Integer.parseInt(req.getParameter("seq"));
		String bbs_reply_content = req.getParameter("bbs_reply_content" + req.getParameter("num"));
		
		System.out.println("댓글 내용 : " + bbs_reply_content);
		
		r_vo.setBbs_reply_num(bbs_reply_num);
		r_vo.setBbs_reply_content(bbs_reply_content);
		
		r_dao.updateBbsReply(r_vo);
		
		forward.setRedirect(true);
		forward.setPath(req.getContextPath() + "/bbs/BbsView.bo?seq="+bbs_num);
		
		return forward;
	}

}
