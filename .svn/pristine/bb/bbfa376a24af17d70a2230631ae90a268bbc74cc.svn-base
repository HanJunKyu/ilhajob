package com.ilhajob.app.bbs;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.ActionForward;

public class BbsFrontController extends HttpServlet{

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
		case "/bbs/BbsList.bo" :
			try {
				forward = new BbsListAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/bbs/BbsList_.bo" :
			try {
				forward = new BbsList_Action().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/bbs/BbsView.bo" :
			try {
				forward = new BbsViewAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/bbs/BbsWrite.bo" :
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/q_a_write_page.jsp");
			break;
		case "/bbs/BbsWriteOk.bo":
			try {
				forward = new BbsWriteOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/bbs/BbsDeleteOk.bo":
			try {
				forward = new BbsDeleteOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/bbs/BbsModify.bo":
			try {
				forward = new BbsModifyAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/bbs/BbsModifyOk.bo":
			try {
				forward = new BbsModifyOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/bbs/FileDownload.bo":
			try {
				forward = new FileDownloadAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/bbs/BbsReplyOk.bo":
			try {
				forward = new BbsReplyOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/bbs/BbsReplyDeleteOk.bo":
			try {
				forward = new BbsReplyDeleteOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/bbs/BbsReplyModifyOk.bo":
			try {
				forward = new BbsReplyModifyOkAction().execute(req, resp);
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




















