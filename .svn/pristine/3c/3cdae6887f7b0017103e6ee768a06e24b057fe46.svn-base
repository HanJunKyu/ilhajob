package com.ilhajob.app.reviews;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.UserLogOutAction;

public class ReviewFrontController extends HttpServlet {

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

		if (command.equals("/review/ReviewWriteOkAction.re")) {
			try {
				forward = new ReviewWriteOkAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (command.equals("/review/ReviewListAction.re")) {
			try {
				forward = new ReviewListAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/review/ReviewListSearchAction.re")) {
			try {
				forward = new ReviewListSearchAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/404.jsp");
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				resp.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, resp);
			}
		}

	}

}
