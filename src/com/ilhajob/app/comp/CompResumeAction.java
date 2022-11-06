package com.ilhajob.app.comp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.comp.vo.CompApplyVO;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.opening.vo.OpeningVO;

public class CompResumeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		CompDAO c_dao = new CompDAO();
		OpeningVO o_vo = new OpeningVO();
		OpeningDAO o_dao = new OpeningDAO();
		List<String> title = new ArrayList<String>();
		List<String> id = new ArrayList<String>();
		List<Integer> opn_num = new ArrayList<>();
		
		String user_id = (String)session.getAttribute("session_id");
		
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
		
		int totalCnt = c_dao.getResumeCnt(user_id);
		
		//10의 배수로
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		
		int realEndPage = (totalCnt - 1) / pageSize + 1;
		
		//실제 마지막 페이지와 연산으로 구한 마지막 페이지를 비교하여 일치하도록 해준다.
		endPage = endPage > realEndPage ? realEndPage : endPage;
		
		List<CompApplyVO> resume = c_dao.getCompApply(user_id, null, startRow, endRow);
		
		req.setAttribute("totalCnt", totalCnt);
		req.setAttribute("realEndPage", realEndPage);
		req.setAttribute("nowPage", page);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		
		if(resume.size() != 0) {
			for(CompApplyVO bean : resume) {
				o_vo = o_dao.getOnlyData(bean.getJobs_num());
				title.add(o_vo.getOpn_title());
				opn_num.add(o_vo.getOpn_num());
			}
			req.setAttribute("title", title);
			req.setAttribute("resume", resume);
			req.setAttribute("opn_numList", opn_num);
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/comp_resume.jsp");
		return forward;
	}

}
