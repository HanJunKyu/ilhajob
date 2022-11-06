package com.ilhajob.app.bbs;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.bbs.dao.BbsDAO;

public class BbsModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		BbsDAO b_dao = new BbsDAO();
		ActionForward forward = new ActionForward();
		
		//상세보기에서 보고있던 게시글 번호를 전달받은 후
		int bbs_num = Integer.parseInt(req.getParameter("seq"));
		
		//수정할 페이지에 뿌려줄 데이터를 req에 담아서 전달해준다.
		req.setAttribute("boardBean", b_dao.getDetail(bbs_num));
		
		//req를 유지하기 위해서 forward방식을 사용한다.
		forward.setRedirect(false);
		//응답 페이지에서는 boardBean을 통해서 기존 게시글 정보를 표현할 수 있게 된다.
		forward.setPath("/q_a_modify_page.jsp");
		
		return forward;
	}

}
