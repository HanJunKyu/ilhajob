package com.ilhajob.app.bbs;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.bbs.dao.BbsDAO;
import com.ilhajob.app.bbs.vo.BbsVO;

public class BbsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		
		ActionForward forward = new ActionForward();
		BbsDAO b_dao = new BbsDAO();
		BbsVO b_vo = new BbsVO();
		PrintWriter out = resp.getWriter();
				
		//페이징 처리
		String temp = req.getParameter("page");
		
		
		
		
		//요청한 페이지가 없다면 default로 1페이지를 응답해주고,
		//요청한 페이지가 있다면 해당 페이지로 응답해준다.
		int page = temp == null ? 1 : Integer.parseInt(temp);
		
		//한 페이지 당 10개의 게시글이 보이도록 설정
		int pageSize = 10;
		
		//한 페이지에서 가장 마지막 행 번호
		int endRow = page * pageSize;
		
		//한 페이지에서 가장 첫번째 행 번호
		int startRow = endRow - (pageSize - 1);
		
		int totalCnt = b_dao.getBbsCnt();
		
		//10의 배수로
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		
		int realEndPage = (totalCnt - 1) / pageSize + 1;
		
		//실제 마지막 페이지와 연산으로 구한 마지막 페이지를 비교하여 일치하도록 해준다.
		endPage = endPage > realEndPage ? realEndPage : endPage;
		
		req.setAttribute("totalCnt", totalCnt);
		req.setAttribute("realEndPage", realEndPage);
		req.setAttribute("nowPage", page);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("bbsList", b_dao.getBbsList(null, startRow, endRow));
		
		forward.setRedirect(false);
		forward.setPath("/q_a_list_page.jsp");
		
		return forward;
	}
}




















