package com.ilhajob.app.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.ResumeViewAction;


public class UserFrontController extends HttpServlet{
	
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
		
		if(command.equals("/user/UserJoin.us")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/join.jsp");
		}else if(command.equals("/user/UserJoinOk.us")) {
			try {
				forward = new UserJoinOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/UserCheckIdOk.us")) {
			try {
				forward = new UserCheckIdOk().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/MyMain.us")) {
			try {
				forward = new UserMyMainAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/MyInfo.us")) {
			try {
				forward = new UserMyInfoAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/MyInfoEdit.us")) {
			try {
				forward = new UserMyInfoEditAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/UserInfoEditOk.us")) {
			try {
				forward = new UserMyInfoEditOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/MyResume.us")) {
			try {
				forward = new UserResumeAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/ResumeView.us")) {
			try {
				forward = new ResumeViewAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/UserResumeWrite.us")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/resume_write.jsp");
		}else if(command.equals("/user/UserResumeWriteOk.us")) {
			try {
				forward = new UserResumeWriteOk().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/ResumeEdit.us")) {
			try {
				forward = new UserResumeEditAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/UserResumeEditOk.us")) {
			try {
				forward = new UserResumeEditOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/ResumeDelete.us")) {
			try {
				forward = new UserResumeDeleteAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/MyApply.us")) {
			try {
				forward = new UserApplyAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/FileDownload.us")) {
			try {
				forward = new FileDownloadAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/SendSMS.us")) {
			try {
				forward = new SendSMS().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/Main.us")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/index.jsp");

		}else if(command.equals("/user/UserLogin.us")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/login.jsp");

		}else if(command.equals("user/UserFindId.us")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/login.jsp");

		}else if(command.equals("/user/UserFindIdOkAction.us")) {
			try {
				forward = new UserFindIdOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/UserFindPwOkAction.us")) {
			try {
				forward = new UserFindPwOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/UserLoginOk.us")) {
			try {
				forward = new UserLoginOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/UserLogOut.us")) {
			try {
				forward = new UserLogOutAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/MyScrap.us")) {
			try {
				forward = new UserMyScrapAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/user/MyScrapDelete.us")) {
			try {
				forward = new UserMyScrapDeleteAction().execute(req, resp);
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
















