package com.ilhajob.app.opening;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.print.attribute.standard.PrinterResolution;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.opening.vo.OpeningVO;
import com.ilhajob.app.opening.vo.WorkLocationVO;

public class OpeningListAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		//ActionForward forward = new ActionForward();
		OpeningDAO openingDAO = new OpeningDAO();
		JSONObject jsonObject = null;
		JSONArray jsonArray = new JSONArray();
		PrintWriter out = resp.getWriter();
		
		//아이디 받아오기
		String opn_comp_id=req.getParameter("opn_comp_id");
		//tbody 개수
		int tr_length=Integer.parseInt(req.getParameter("tbody_cnt"));
		//게시글 개수 설정
		int show_length=5;
		//아이디에 해당하는 기업이 작성한 공고 개수 가져오기
		int total_cnt=openingDAO.getOpeningTotalCnt(opn_comp_id);
		
		//db 조회
		List<OpeningVO> result = openingDAO.listOpening(tr_length, show_length, opn_comp_id);
		
		//공고개수 세팅
		jsonObject = new JSONObject();
		jsonObject.put("total_cnt", total_cnt);
		jsonArray.add(jsonObject);
		
		//setAtrribute : 결과
		for(int i=0; i<result.size();i++) {
			jsonObject = new JSONObject();
			//필요한 정보 세팅
			jsonObject.put("opn_num", result.get(i).getOpn_num());
			jsonObject.put("opn_comp_name", result.get(i).getOpn_comp_name()); //기업 이름
			jsonObject.put("opn_title", result.get(i).getOpn_title());
			jsonObject.put("opn_edu", result.get(i).getOpn_edu());
			jsonObject.put("opn_career", result.get(i).getOpn_career());
			jsonObject.put("opn_work_type", result.get(i).getOpn_work_type());
			jsonObject.put("opn_end_date", result.get(i).getOpn_recruit_end());
			jsonObject.put("is_recruiting", result.get(i).getIs_recruiting());
			jsonObject.put("work_location1", result.get(i).getWork_location1()); //근무 장소
			jsonObject.put("work_location2", result.get(i).getWork_location2()); //근무 장소
			jsonObject.put("work_location3", result.get(i).getWork_location3()); //근무 장소
			//배열에 추가
			jsonArray.add(jsonObject);
		}
		
		//jsonArray 리턴
		out.print(jsonArray.toJSONString());
		out.close();
		
		return null;
	}

}
