package com.ilhajob.app.opening;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.vo.CompVO;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.opening.vo.OpeningVO;
import com.ilhajob.app.opening.vo.WorkLocationVO;

public class OpeningSearchAction implements Action {
	
	//검색 페이지 메인
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		OpeningDAO openingDAO = new OpeningDAO();
		
		//키워드 세팅
		String keyword = req.getParameter("keyword");
		String work_location = req.getParameter("location");
		String recruitment_area = req.getParameter("job");
		
		int startRow=1;
		int endRow=5;
		
		//db 조회
		List<OpeningVO> result1 = openingDAO.searchAll(keyword, work_location, recruitment_area, startRow, endRow);
		List<CompVO> result2 = openingDAO.searchComp(keyword, startRow, endRow);
		ArrayList<WorkLocationVO> workLocationList = new ArrayList<>();
		for(int i=0;i<result1.size();i++) {
			workLocationList.add(openingDAO.getWorkLocation(result1.get(i).getOpn_num()));
		}
		//개수 전달
		int result1_cnt=openingDAO.searchAllCount(keyword, work_location, recruitment_area);
		int result2_cnt=openingDAO.searchCompCount(keyword);
		
		//결과값 세팅
		req.setAttribute("result1", result1);
		req.setAttribute("result2", result2);
		req.setAttribute("workLocationList", workLocationList);
		req.setAttribute("result1_cnt", result1_cnt);
		req.setAttribute("result2_cnt", result2_cnt);
		req.setAttribute("keyword", keyword);
		req.setAttribute("work_location", work_location);
		req.setAttribute("recruitment_area", recruitment_area);
		
		//결과 반환
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/job-search-page.jsp");
		
		return forward;
			
	}

}
