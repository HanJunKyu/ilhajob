package com.ilhajob.app.comp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.comp.vo.CompVO;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserVO;

public class CompInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		CompDAO c_dao = new CompDAO();
		String user_id = (String) session.getAttribute("session_id");
		String saveFolder = "/Users/yulim/Documents/study/(6) JSP/workspace/ilhajob/WebContent/app/upload";
		//String saveFolder = "C:\\web\\temp\\workspace\\ilhajob\\WebContent\\app\\upload";
		
		if (user_id != null) {

			CompVO c_vo = c_dao.getCompInfo(user_id);
			
			req.setAttribute("comp_photo", c_vo.getComp_photo());
			req.setAttribute("comp_type", c_vo.getComp_type());
			req.setAttribute("comp_name", c_vo.getComp_name());
			req.setAttribute("comp_ceo", c_vo.getComp_ceo());
			req.setAttribute("comp_location", c_vo.getComp_location());
			req.setAttribute("comp_crn", c_vo.getComp_crn());
			
			
			Integer income = c_vo.getComp_income();
			String doe = c_dao.getDoe(user_id);
			String capital = c_vo.getComp_capital();
			String worker = c_vo.getComp_workercnt();
			String tip = c_vo.getComp_tip();
			
			req.setAttribute("comp_income", (income != null) ? income : "-");
			req.setAttribute("comp_doe", (doe != null) ? doe : "-");
			req.setAttribute("comp_capital", (capital != null) ? capital : "-");
			req.setAttribute("comp_worker", (worker != null) ? worker : "-");
			req.setAttribute("comp_tip", (tip != null) ? tip : "-");
			req.setAttribute("path", saveFolder);
			
			req.setAttribute("user_name", c_vo.getComp_user_name());
			req.setAttribute("user_id", c_vo.getComp_id());
			req.setAttribute("user_phone", c_vo.getComp_phone());
			req.setAttribute("user_email", c_vo.getComp_email());
		

			forward.setRedirect(false);
			forward.setPath("/comp_info.jsp");
		} else {
			forward.setRedirect(false);
			forward.setPath("/login.jsp");

		}

		return forward;
	}

}
