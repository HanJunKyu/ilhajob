package com.ilhajob.app.message;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.message.dao.MessageDAO;
import com.ilhajob.app.message.vo.MessageVO;

public class MessageWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		MessageDAO m_dao = new MessageDAO();
		MessageVO m_vo = new MessageVO();

		m_vo.setMessage_send_id(req.getParameter("message_send_id"));
		m_vo.setMessage_rcv_id(req.getParameter("message_rcv_id"));
		m_vo.setMessage_title(req.getParameter("message_title"));
		m_vo.setMessage_content(req.getParameter("message_content"));

		// Message에 게시글 추가
		if (m_dao.insertSendMessage(m_vo) && m_dao.insertRcvMessage(m_vo)) {
				
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/message/Message_RcvList.ms");
			forward.setPath(req.getContextPath() + "/message/Message_SendList.ms");

			return forward;
		}

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=utf-8");

		out.println("<script>");
		out.println("alert('다시 시도해주세요.');");
		out.println("</script>");
		out.close();

		return null;
	}

}
