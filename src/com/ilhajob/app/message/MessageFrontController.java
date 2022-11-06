package com.ilhajob.app.message;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.ActionForward;

public class MessageFrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		
		switch(command) {
		case "/message/Message_SendList.ms" :
			try {
				forward = new Message_SendListAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/message/Message_RcvList.ms" :
			try {
				forward = new Message_RcvListAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/message/MessageWrite.ms" :
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/message.jsp");
			break;
		case "/message/MessageReturn.ms" :
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/return_message.jsp");
			break;
		case "/message/MessageWriteOk.ms":
			try {
				forward = new MessageWriteOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/message/Message_Check1.ms" :
			try {
				forward = new Message_Check1Action().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/message/Message_Check2.ms" :
			try {
				forward = new Message_Check2Action().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/message/Message_SendDeleteOk.ms":
			try {
				forward = new Message_SendDeleteOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;	
		case "/message/Message_RcvDeleteOk.ms":
			try {
				forward = new Message_RcvDeleteOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;	
		default:
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/404.jsp");
		}
		
		//일괄처리
		if(forward != null) {
			if(forward.isRedirect()) {
				resp.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, resp);
			}
		}
	}
	
}




