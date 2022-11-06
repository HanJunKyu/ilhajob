package com.ilhajob.app.bbs;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.bbs.dao.BbsDAO;
import com.ilhajob.app.bbs.vo.BbsReplyVO;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserVO;

public class BbsReplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ActionForward forward = null;
		HttpSession session = req.getSession();
		BbsReplyVO r_vo = new BbsReplyVO();
		BbsDAO r_dao = new BbsDAO();
		
		//게시글 번호, 작성자, 댓글 내용
		
		int bbs_num = Integer.parseInt(req.getParameter("seq"));
		String user_id = (String)session.getAttribute("session_id");
		String bbs_reply_content = req.getParameter("content");
		
		r_vo.setBbs_num(bbs_num);
		r_vo.setUser_id(user_id);
		r_vo.setBbs_reply_content(bbs_reply_content);
		if(r_dao.insertBbsReply(r_vo)) {
			forward = new ActionForward();
			
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/bbs/BbsView.bo?seq="+bbs_num);
		}
		
		return forward;
	}

}
