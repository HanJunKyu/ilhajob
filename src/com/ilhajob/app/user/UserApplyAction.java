package com.ilhajob.app.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.opening.vo.OpeningVO;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserApplyVO;

public class UserApplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		UserDAO u_dao = new UserDAO();
		OpeningVO o_vo = new OpeningVO();
		OpeningDAO o_dao = new OpeningDAO();
		List<String> title = new ArrayList<String>();
		List<String> comp_name = new ArrayList<String>();
		List<String> comp_id = new ArrayList<>();
		List<Integer> opn_num = new ArrayList<>();
		
		String user_id = (String)session.getAttribute("session_id");
		List<UserApplyVO> apply = u_dao.getApply(user_id);
		
		if(apply.size() != 0) {
			for(UserApplyVO bean : apply) {
				o_vo = o_dao.getOnlyData(bean.getJobs_num());
				title.add(o_vo.getOpn_title());	
				comp_name.add(o_vo.getOpn_comp_name());
				comp_id.add(o_vo.getOpn_comp_id());
				opn_num.add(o_vo.getOpn_num());
			}
			req.setAttribute("apply", apply);
			req.setAttribute("title", title);
			req.setAttribute("comp_name", comp_name);
			req.setAttribute("comp_idList", comp_id);
			req.setAttribute("opn_numList", opn_num);
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/my_apply.jsp");
		return forward;
	}

}
