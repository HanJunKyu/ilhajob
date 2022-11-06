package com.ilhajob.app.opening;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.opening.dao.OpeningDAO;

public class OpeningCustomSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		OpeningDAO openingDAO = new OpeningDAO();
		HttpSession session = req.getSession();
		
		//필요한 값 받아오기
		String user_id = (String)session.getAttribute("session_id");
		//조건 세팅
		
		
		//페이징 처리
		String temp = req.getParameter("page");
		//요청한 페이지가 없다면 default로 1페이지를 응답해주고,
		//한 페이지 당 5개의 게시글이 보이도록 설정
		int page = temp == null ? 1 : Integer.parseInt(temp);
		int pageSize = 5;
		//한 페이지에서 가장 마지막 행 번호
		int endRow = page * pageSize;
		//한 페이지에서 가장 첫번째 행 번호
		int startRow = endRow - (pageSize - 1);
		//총개수 가져오기
		int totalCnt = 2;
		System.out.println(totalCnt);
		//5의 배수로
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 4;
		int realEndPage = (totalCnt - 1) / pageSize + 1;
		//실제 마지막 페이지와 연산으로 구한 마지막 페이지를 비교하여 일치하도록 해준다.
		endPage = endPage > realEndPage ? realEndPage : endPage;
		
		//db 조회
		
		
		//결과값 세팅
		req.setAttribute("totalCnt", totalCnt);
		req.setAttribute("realEndPage", realEndPage);        
		req.setAttribute("nowPage", page);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		
		//결과 반환
		forward.setRedirect(false);
		forward.setPath("/full-search-opening.jsp");
		
		return forward;
	}

}
