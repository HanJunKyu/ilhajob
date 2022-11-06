package com.ilhajob.app.bbs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.bbs.dao.BbsDAO;
import com.ilhajob.app.bbs.dao.FilesDAO;
import com.ilhajob.app.bbs.vo.BbsVO;
import com.ilhajob.app.bbs.vo.FilesVO;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserVO;

public class BbsViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		
		FilesDAO f_dao = new FilesDAO();
		BbsDAO b_dao = new BbsDAO();
		BbsVO b_vo = new BbsVO();
		HttpSession session = req.getSession();
		
		int bbs_num = Integer.parseInt(req.getParameter("seq"));
		String saveFolder = "C:\\web_1530_hjk\\jsp\\workspace\\ilhajob\\WebContent\\app\\upload";
		b_vo = b_dao.getDetail(bbs_num);
		UserDAO u_dao = new UserDAO();
		String user_id = (String)session.getAttribute("session_id");
		
		List<FilesVO> filesList = f_dao.getDeatail(bbs_num);
		
		if(b_vo != null) {
			UserVO u_vo = u_dao.getUser(user_id);
			b_dao.updateBbsReadCount(bbs_num);
			req.setAttribute("boardBean", b_vo);
			req.setAttribute("replyBeanList", b_dao.getBbsReplyList(bbs_num));
			req.setAttribute("user_photo", u_vo.getUser_photo());
			req.setAttribute("path", saveFolder);
			if(filesList != null) {
				req.setAttribute("filesBeanList", filesList);
			}
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/q_a_view_page.jsp");
			return forward;
		}
		
		return null;
	}

}














