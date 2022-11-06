package com.ilhajob.app.opening;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.comp.vo.CompVO;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.opening.vo.OpeningVO;

public class OpeningCompInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		
		//아이디 받아오기
		String comp_id=req.getParameter("comp_id");
		
		//DAO
		OpeningDAO openingDAO = new OpeningDAO();
		CompDAO compDAO = new CompDAO();
		
		//VO
		List<OpeningVO> openingList=null;
		CompVO compVO=null;
		System.out.println("OpeningWrtieOkAction : app 폴더 변경하세요~");
		String saveFolder = "C:\\web\\temp\\workspace\\ilhajob\\WebContent\\app";
		
		//db 조회
		if(comp_id!=null){
			compVO = openingDAO.getCompInfo(comp_id);
			openingList = openingDAO.getCompOpening(comp_id);
		
			req.setAttribute("compVO", compVO);
			req.setAttribute("openingList", openingList);
		
			forward.setRedirect(false);
			forward.setPath("/show_comp_info.jsp");
		
			return forward;
		} else {
			forward.setRedirect(false);
			forward.setPath("/login.jsp");
		}

		return forward;
	}

}
