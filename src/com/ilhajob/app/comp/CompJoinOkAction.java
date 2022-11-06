package com.ilhajob.app.comp;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.comp.vo.CompVO;


public class CompJoinOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		
		ActionForward forward = null;
		
		
		CompDAO c_dao = new CompDAO();
		CompVO c_vo = new CompVO();
		
		c_vo.setComp_type(req.getParameter("comp_type"));
		c_vo.setComp_crn(req.getParameter("comp_crn"));
		c_vo.setComp_name(req.getParameter("comp_name"));
		c_vo.setComp_ceo(req.getParameter("comp_ceo"));
		c_vo.setComp_location(req.getParameter("comp_location"));
		c_vo.setComp_user_name(req.getParameter("comp_user_name"));
		c_vo.setComp_id(req.getParameter("comp_id"));
		c_vo.setComp_pw(req.getParameter("comp_pw"));
		c_vo.setComp_email(req.getParameter("comp_email"));
		c_vo.setComp_phone(req.getParameter("comp_phone"));
		
		if(!c_dao.join(c_vo)){
			PrintWriter out = resp.getWriter();
			 resp.setContentType("text/html;charset=utf-8");
			 out.println("<script>");
			 out.println("alert('회원가입 실패. 잠시 후 다시 시도해주세요.')");
			 out.println("</script>");
			 out.close();
		}else {
			forward = new ActionForward();
			
			forward.setRedirect(false);
			forward.setPath("/comp/CompLogin.co");
		}
		return forward;
	}

}
