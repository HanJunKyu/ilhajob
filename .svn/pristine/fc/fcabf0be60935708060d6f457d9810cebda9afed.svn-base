package com.ilhajob.app.comp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.UserCheckIdOk;
import com.ilhajob.app.user.UserJoinOkAction;
import com.ilhajob.app.user.UserLogOutAction;
import com.ilhajob.app.user.UserLoginOkAction;

public class CompFrontController extends HttpServlet{
	
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
		
		if(command.equals("/comp/CompJoin.co")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/join_comp.jsp");
		}else if(command.equals("/comp/CompJoinOk.co")) {
			try {
				forward = new CompJoinOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompCheckIdOk.co")) {
			try {
				forward = new CompCheckIdOk().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/Main.co")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/index.jsp");

		}else if(command.equals("/comp/CompLogin.co")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/comp_login.jsp");

		}else if(command.equals("/comp/CompLoginOk.co")) {
			try {
				forward = new CompLoginOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompMain.co")) {
			try {
				forward = new CompMainAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompInfo.co")) {
			try {
				forward = new CompInfoAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompResume.co")) {
			try {
				forward = new CompResumeAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompOpn.co")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/comp_apply.jsp");
		}else if(command.equals("/comp/CompStatusResume.co")) {
			try {
				forward = new CompStatusResumeAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/ResumeView.co")) {
			try {
				forward = new ResumeViewAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompInfoEditAction.co")) {
			try {
				forward = new CompInfoEditAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompUserInfoEditAction.co")) {
			try {
				forward = new CompUserInfoEditAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompInfoEditOk.co")) {
			try {
				forward = new CompInfoEditOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompUserInfoEditOk.co")) {
			try {
				forward = new CompUserInfoEditOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompFindIdOkAction.co")) {
			try {
				forward = new CompFindIdOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompFindPwOkAction.co")) {
			try {
				forward = new CompFindPwOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/comp/CompLogOut.co")) {
			try {
				forward = new CompLogOutAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/404.jsp");
		}
		
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
