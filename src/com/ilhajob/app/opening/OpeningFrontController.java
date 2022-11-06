package com.ilhajob.app.opening;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.ActionForward;

public class OpeningFrontController extends HttpServlet{

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
	
		//Action 매핑
		switch(command) {
		case "/opening/OpeningModify.opn" :
			try {
				forward = new OpeningModifyAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningWrite.opn" :
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/write_opening.jsp");
			break;
		case "/opening/OpeningWriteOk.opn" :
			try {
				forward = new OpeningWriteOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningList.opn" :
			try {
				forward = new OpeningListAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningView.opn" :
			try {
				forward = new OpeningViewAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningDelete.opn" :
			try {
				forward = new OpeningDeleteAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningSearch.opn" :
			try {
				forward = new OpeningSearchAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningCustomSearch.opn" :
			try {
				forward = new OpeningCustomSearchAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningMore.opn" :
			try {
				forward = new OpeningMoreAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningCompMore.opn" :
			try {
				forward = new OpeningCompMoreAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningCompInfo.opn" :
			try {
				forward = new OpeningCompInfoAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningApply.opn" :
			try {
				forward = new OpeningApplyAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningApplyOk.opn" :
			try {
				forward = new OpeningApplyOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningScrap.opn" :
			try {
				forward = new OpeningScrapAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/opening/OpeningScrapDup.opn" :
			try {
				forward = new OpeningScrapDupAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/error/404.jsp");
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