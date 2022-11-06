package com.ilhajob.app.opening;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.user.vo.UserScrapVO;

public class OpeningScrapDupAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		//DAO
		OpeningDAO openingDAO = new OpeningDAO();
		//VO
		UserScrapVO usVO = new UserScrapVO(); 
		//JSON
		JSONObject jsonObject = null;
		//JSONArray jsonArray = new JSONArray();
		PrintWriter out = resp.getWriter();
		
		//아이디와 공고번호 받기
		String user_id = req.getParameter("user_id");
		int opn_num=Integer.parseInt(req.getParameter("opn_num"));
		
		//db 조회
		int count=openingDAO.isScrapDup(user_id, opn_num);
		boolean isScrapDup=count!=0?true:false;
		
		//결과 반환
		jsonObject = new JSONObject();
		jsonObject.put("isScrapDup",isScrapDup);
		out.print(jsonObject.toJSONString());
		out.close();
		
		return null;
	}

}
