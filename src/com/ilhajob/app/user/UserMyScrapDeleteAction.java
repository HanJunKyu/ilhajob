package com.ilhajob.app.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.opening.vo.OpeningVO;

public class UserMyScrapDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//스크랩 삭제 컨트롤러
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		//DAO
		OpeningDAO openingDAO = new OpeningDAO();
		
		//VO
		OpeningVO openingVO = null;
		
		
		//회원 아이디를 받기
		String user_id = (String)session.getAttribute("session_id");
		//삭제할 공고 번호
		int opn_num=Integer.parseInt(req.getParameter("opn_num"));
		
		//db 접근
		if(openingDAO.isScrapDup(user_id, opn_num)!=0) {
			openingDAO.deleteScrap(user_id, opn_num);
		}
		
		return null;
	}

}
