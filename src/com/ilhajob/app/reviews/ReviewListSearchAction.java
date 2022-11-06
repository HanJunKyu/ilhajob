package com.ilhajob.app.reviews;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.vo.CompApplyVO;
import com.ilhajob.app.reviews.dao.ReviewDAO;
import com.ilhajob.app.reviews.vo.ReviewVO;

public class ReviewListSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();
		ReviewDAO r_dao = new ReviewDAO();
		ReviewVO r_vo = new ReviewVO();
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray_result = new JSONArray();

		String review_career = req.getParameterValues("search")[0];
		String review_job = req.getParameterValues("search")[1];
		List<ReviewVO> review = new ArrayList<>();
		
		System.out.println(req.getParameterValues("search")[0]);
		System.out.println(req.getParameterValues("search")[1]);
		System.out.println(req.getParameterValues("search")[2]);
		// 페이징 처리
		String temp = req.getParameter("page");
		

		// 요청한 페이지가 없다면 default로 1페이지를 응답해주고,
		// 요청한 페이지가 있다면 해당 페이지로 응답해준다.
		int page = temp == null ? 1 : Integer.parseInt(temp);

		// 한 페이지 당 10개의 게시글이 보이도록 설정
		int pageSize = 10;

		// 한 페이지에서 가장 마지막 행 번호
		int endRow = page * pageSize;

		// 한 페이지에서 가장 첫번째 행 번호
		int startRow = endRow - (pageSize - 1);


		int totalCnt = 0;
		if(req.getParameterValues("search").length == 3) {
			String search = req.getParameterValues("search")[2];
			totalCnt = r_dao.getReviewSearchCnt(review_career, review_job, search);
		}else {
			totalCnt = r_dao.getReviewSearchCnt(review_career, review_job, null);
			
		}
		// 10의 배수로
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;

		int realEndPage = (totalCnt - 1) / pageSize + 1;
		

		// 실제 마지막 페이지와 연산으로 구한 마지막 페이지를 비교하여 일치하도록 해준다.
		endPage = endPage > realEndPage ? realEndPage : endPage;
		
		if(req.getParameterValues("search").length == 3) {
			String search = req.getParameterValues("search")[2];
			review = r_dao.getReviewSearch(startRow, endRow, review_career, review_job, search);
		}else {
			review = r_dao.getReviewSearch(startRow, endRow, review_career, review_job, null);
			
		}

		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("totalCnt", totalCnt);
		pageMap.put("realEndPage", realEndPage);
		pageMap.put("nowPage", page);
		pageMap.put("startPage", startPage);
		pageMap.put("endPage", endPage);

		JSONObject jsonPaging = new JSONObject(pageMap);


		for (int i = 0; i < review.size(); i++) {
			Map<String, String> reviewMap = new HashMap<>();
			reviewMap.put("company_name", review.get(i).getReview_company_name());
			reviewMap.put("job", review.get(i).getReview_job());
			reviewMap.put("interview_date", review.get(i).getReview_interview_date());
			reviewMap.put("career", review.get(i).getReview_career());
			reviewMap.put("evaluation", review.get(i).getReview_evaluation());
			reviewMap.put("level", review.get(i).getReview_level());
			reviewMap.put("result", review.get(i).getReview_result());
			reviewMap.put("type", review.get(i).getReview_type());
			reviewMap.put("num_of_people", review.get(i).getReview_num_of_people());
			reviewMap.put("review", review.get(i).getReview_review());
			reviewMap.put("interview_q1", review.get(i).getReview_interview_q1());
			reviewMap.put("interview_q2", review.get(i).getReview_interview_q2());
			reviewMap.put("interview_q3", review.get(i).getReview_interview_q3());
			reviewMap.put("tip", review.get(i).getReview_tip());
			JSONObject reviewJson = new JSONObject(reviewMap);
			jsonArray_result.add(reviewJson);
		}

		jsonArray_result.add(jsonPaging);

		out.println(jsonArray_result.toString());
		out.close();
		return null;

	}

}
