package com.ilhajob.app.comp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.opening.dao.OpeningDAO;

public class CompMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		CompDAO c_dao = new CompDAO();
		OpeningDAO o_dao = new OpeningDAO();
		
		String user_id = (String)session.getAttribute("session_id");
		
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		String date = format1.format(time.getTime());
		
		//채용 진행중인 공고 개수
		int opn_cnt = o_dao.getRecruitingOpnCnt(user_id);
				
		req.setAttribute("comp_name", c_dao.getCompInfo(user_id).getComp_name());
		req.setAttribute("apply_cnt", c_dao.getApplyCnt(user_id));
		req.setAttribute("read_cnt", c_dao.getReadCnt(user_id));
		req.setAttribute("opn_cnt", opn_cnt);
		req.setAttribute("endOpnCnt", c_dao.getEndOpnCnt(user_id, date));
		
		ActionForward forward = new ActionForward();
		
		
		forward.setRedirect(false);
		forward.setPath("/comp_main.jsp");
		
		return forward;
	}

}
