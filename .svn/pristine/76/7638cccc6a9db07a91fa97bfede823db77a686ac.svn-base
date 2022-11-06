package com.ilhajob.app.opening;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.comp.vo.CompApplyVO;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.opening.vo.OpeningVO;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserApplyVO;
import com.ilhajob.app.user.vo.UserVO;

public class OpeningApplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		UserDAO u_dao = new UserDAO();
		HttpSession session = req.getSession();
		UserApplyVO a_vo = new UserApplyVO();
		CompDAO c_dao = new CompDAO();
		CompApplyVO c_vo = new CompApplyVO();
		OpeningDAO o_dao = new OpeningDAO();
		ActionForward forward = new ActionForward();
		
		String user_id = (String)session.getAttribute("session_id");
		//String user_id = "dfs";
		int opn_num = Integer.parseInt(req.getParameter("opn_num"));		
		
		String recruitArea = req.getParameter("recruitArea");		
		
		a_vo.setJobs_num(opn_num);
		a_vo.setApply_area(recruitArea);
		a_vo.setUser_id(user_id);
		a_vo.setApply_status("미열람");
		
		if(u_dao.apply(a_vo)) {
			OpeningVO opn = o_dao.getOnlyData(opn_num);
			UserVO u_vo = u_dao.getUser(user_id);
			c_vo.setJobs_num(opn_num);
			c_vo.setComp_user_id(opn.getOpn_comp_id());
			c_vo.setApply_status("열람하기");
			c_vo.setApply_area(recruitArea);
			c_vo.setApply_name(u_vo.getUser_name());
			c_vo.setApply_id(user_id);
			c_dao.compApply(c_vo);
			
			forward.setRedirect(true);
			forward.setPath(req.getContextPath()+"/submitted.jsp");
		}else {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html;charset=utf-8");
			
			out.println("<script>");
			out.println("alert('지원 실패. 다시 시도해주세요.');");
			out.println("</script>");
			out.close();
			forward.setRedirect(false);
			forward.setPath("/opening/OpeningView.opn?opn_num="+opn_num);
			
		}
		
		return forward;
	}

}
