package com.ilhajob.app.bbs;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.bbs.dao.BbsDAO;
import com.ilhajob.app.bbs.vo.BbsVO;

public class BbsList_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		BbsDAO b_dao = new BbsDAO();
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray_result = new JSONArray();
		JSONObject jsonPaging = new JSONObject();
		// 페이징 처리
		String temp = req.getParameter("page");
		System.out.println(temp);
		
		// 요청한 페이지가 없다면 default로 1페이지를 응답해주고,
		// 요청한 페이지가 있다면 해당 페이지로 응답해준다.
		int page = temp == null ? 1 : Integer.parseInt(temp);

		// 한 페이지 당 10개의 게시글이 보이도록 설정
		int pageSize = 10;

		// 한 페이지에서 가장 마지막 행 번호
		int endRow = page * pageSize;

		// 한 페이지에서 가장 첫번째 행 번호
		int startRow = endRow - (pageSize - 1);

		int totalCnt = b_dao.getBbsCnt();

		// 10의 배수로
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		int realEndPage = (totalCnt - 1) / pageSize + 1;

		// 실제 마지막 페이지와 연산으로 구한 마지막 페이지를 비교하여 일치하도록 해준다.
		endPage = endPage > realEndPage ? realEndPage : endPage;

		String radioTemp = req.getParameter("bbs_category");
		List<BbsVO> bbsList = b_dao.getBbsList(radioTemp, startRow, endRow);
		System.out.println(bbsList);

		for (int i = 0; i < bbsList.size(); i++) {
			Map<String, Object> boardMap = new HashMap<>();
			boardMap.put("bbs_num", bbsList.get(i).getBbs_num());
			boardMap.put("bbs_title", bbsList.get(i).getBbs_title());
			boardMap.put("bbs_content", bbsList.get(i).getBbs_content());
			boardMap.put("user_id", bbsList.get(i).getUser_id());
			boardMap.put("bbs_date", bbsList.get(i).getBbs_date());
			boardMap.put("bbs_readcount", bbsList.get(i).getBbs_readcount());
			boardMap.put("bbs_job", bbsList.get(i).getBbs_job());
			boardMap.put("bbs_category", bbsList.get(i).getBbs_category());

			
			JSONObject bbs = new JSONObject(boardMap);
			jsonArray_result.add(bbs);
		}
		
		jsonPaging.put("totalCnt", totalCnt);
		jsonPaging.put("realEndPage", realEndPage);
		jsonPaging.put("nowPage", page);
		jsonPaging.put("startPage", startPage);
		jsonPaging.put("endPage", endPage);
		jsonArray_result.add(jsonPaging);
		
		out.println(jsonArray_result.toString());
		out.close();
		return null;
	}
}
