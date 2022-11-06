package com.ilhajob.app.comp;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.comp.vo.CompApplyVO;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.opening.vo.OpeningVO;

public class CompStatusResumeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		CompDAO c_dao = new CompDAO();
		OpeningDAO o_dao = new OpeningDAO();
		
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray_result = new JSONArray();
		
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
		int totalCnt = 0;
		
		if(req.getParameterValues("status") != null) {
			
			if(req.getParameterValues("status").length == 2) {
				
				totalCnt = c_dao.getResumeCnt(user_id);
			}else {
				totalCnt = c_dao.getStatusResumeCnt(user_id, req.getParameterValues("status")[0]);
			}
		}
		
		//10의 배수로
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		
		int realEndPage = (totalCnt - 1) / pageSize + 1;
		
		
		//실제 마지막 페이지와 연산으로 구한 마지막 페이지를 비교하여 일치하도록 해준다.
		endPage = endPage > realEndPage ? realEndPage : endPage;

		
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("totalCnt", totalCnt);
		pageMap.put("realEndPage", realEndPage);
		pageMap.put("nowPage", page);
		pageMap.put("startPage", startPage);
		pageMap.put("endPage", endPage);
		
		JSONObject jsonPaging = new JSONObject(pageMap);
		
		String[] status = null;
		List<CompApplyVO> resume = new ArrayList<>();
		if(req.getParameterValues("status") != null) {
			
			status = req.getParameterValues("status");
			if(status.length == 2) {		
				resume = c_dao.getCompApply(user_id, null, startRow, endRow);
			}else {
				resume = c_dao.getCompApply(user_id, status[0], startRow, endRow);			
			}
			for (int i = 0; i < resume.size(); i++) {
				Map<String, Object> resumeMap = new HashMap<>();
				resumeMap.put("apply_date", resume.get(i).getApply_date());
				resumeMap.put("apply_name", resume.get(i).getApply_name());			
				resumeMap.put("title", o_dao.getOnlyData(resume.get(i).getJobs_num()).getOpn_title());
				resumeMap.put("apply_area", resume.get(i).getApply_area());			
				resumeMap.put("apply_id", resume.get(i).getApply_id());			
				resumeMap.put("num", resume.get(i).getNum());			
				resumeMap.put("status", resume.get(i).getApply_status());	
				JSONObject resumeJson = new JSONObject(resumeMap);
				jsonArray_result.add(resumeJson);
				
			}
		}else {
			JSONObject resumeJson = null;
			jsonArray_result.add(resumeJson);
		}
		
		jsonArray_result.add(jsonPaging);
		
		out.println(jsonArray_result.toString());
		out.close();
		return null;
	}

}
