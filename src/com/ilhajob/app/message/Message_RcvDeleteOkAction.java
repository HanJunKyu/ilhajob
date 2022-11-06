package com.ilhajob.app.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.message.dao.MessageDAO;

public class Message_RcvDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		MessageDAO m_dao = new MessageDAO();
		
		ActionForward forward = new ActionForward();
				
		int message_num = Integer.parseInt(req.getParameter("seq"));
		
		//게시글 삭제
		m_dao.deleteRcvMessage(message_num);

		forward.setRedirect(true);
		forward.setPath(req.getContextPath() + "/message/Message_RcvList.ms");
		
		return forward;
	}

}











