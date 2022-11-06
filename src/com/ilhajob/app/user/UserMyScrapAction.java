package com.ilhajob.app.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.opening.vo.OpeningVO;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserScrapVO;

import oracle.sql.OpaqueDescriptor;

public class UserMyScrapAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//개인 마이페이지 리스트
		
		req.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		UserDAO u_dao = new UserDAO();
		OpeningDAO o_dao = new OpeningDAO();
		UserScrapVO u_vo = new UserScrapVO();
		OpeningVO o_vo = new OpeningVO();
		HttpSession session = req.getSession();
		
		//회원 아이디를 받기
		String user_id = (String)session.getAttribute("session_id");
		
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
		int totalCnt = o_dao.getScrapCount(user_id);
		//5의 배수로
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 4;
		int realEndPage = (totalCnt - 1) / pageSize + 1;
		//실제 마지막 페이지와 연산으로 구한 마지막 페이지를 비교하여 일치하도록 해준다.
		endPage = endPage > realEndPage ? realEndPage : endPage;
		
		//회원 아이디에 해당하는 스크랩 5개씩 가져오기
		List<UserScrapVO> scrap_list = o_dao.getScrapList(user_id, startRow, endRow);
		ArrayList<OpeningVO> scrap_opening_list = new ArrayList<>();
		//스크랩 전체 컬럼에 있는 opn_num 가져오기
		for(int i=0; i<scrap_list.size();i++) {
			//opn_num에 해당하는 openingVO List로 싹 가져오기
			int temp_num = scrap_list.get(i).getJobs_num();
			scrap_opening_list.add(o_dao.listOpeningByOpnNum(temp_num));
		}
		
		//결과 전송
		req.setAttribute("scrap_opening_list", scrap_opening_list);
		req.setAttribute("totalCnt", totalCnt);
		req.setAttribute("realEndPage", realEndPage);        
		req.setAttribute("nowPage", page);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		
		forward.setRedirect(false);
		forward.setPath("/my_scrap.jsp");
		
		return forward;
	}

}
