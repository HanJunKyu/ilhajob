package com.ilhajob.app.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.message.dao.MessageDAO;
import com.ilhajob.app.message.vo.MessageVO;

public class Message_Check1Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		MessageDAO m_dao = new MessageDAO();
		MessageVO m_vo = new MessageVO();
		
		int message_num = Integer.parseInt(req.getParameter("seq"));
		
		m_vo = m_dao.getSendDetail(message_num);
				
		if(m_vo != null) {
			req.setAttribute("messageBean", m_vo);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/check_message1.jsp");
			return forward;
		}
		
		return null;
	}

}


