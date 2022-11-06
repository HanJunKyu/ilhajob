package com.ilhajob.app.opening;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.opening.dao.OpeningDAO;
import com.ilhajob.app.opening.vo.OpeningDetailTableColumnVO;
import com.ilhajob.app.opening.vo.OpeningDetailTableVO;
import com.ilhajob.app.opening.vo.OpeningDetailVO;
import com.ilhajob.app.opening.vo.OpeningFilesVO;
import com.ilhajob.app.opening.vo.OpeningVO;
import com.ilhajob.app.opening.vo.PreferenceVO;
import com.ilhajob.app.opening.vo.RecruitmentAreaVO;
import com.ilhajob.app.opening.vo.RecruitmentInfoVO;
import com.ilhajob.app.opening.vo.WorkLocationVO;

public class OpeningDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		//DAO
		OpeningDAO openingDAO = new OpeningDAO();
		
		//VO
		OpeningVO openingVO = null;
		List<RecruitmentAreaVO> recruitmentAreaList= null;
		List<PreferenceVO> preferenceList = null;
		RecruitmentInfoVO recruitmentInfoVO = new RecruitmentInfoVO();
		WorkLocationVO workLocationVO = new WorkLocationVO();
		List<OpeningDetailVO> openingDetailList = null;
		List<OpeningDetailTableVO> openingDetailTableList = null;
		ArrayList<List<OpeningDetailTableColumnVO>> openingDetailTableColumnList = new ArrayList<List<OpeningDetailTableColumnVO>>();
		List<OpeningFilesVO> openingFilesList=null;
		
		int opn_num=Integer.parseInt(req.getParameter("opn_num"));
		
		//존재하는지 확인후 삭제
		
		//db조회
		openingVO=openingDAO.getOnlyData(opn_num);
		recruitmentAreaList=openingDAO.getRecruitmentArea(opn_num);
		preferenceList=openingDAO.getPreference(opn_num);
		recruitmentInfoVO=openingDAO.getRecruitmentInfo(opn_num);
		workLocationVO=openingDAO.getWorkLocation(opn_num);
		openingDetailList=openingDAO.getOpnDetail(opn_num);
		openingDetailTableList=openingDAO.getDetailTable(opn_num);
		openingFilesList=openingDAO.getImg(opn_num);
		for(int i=0;i<openingDetailTableList.size();i++) {
			List<OpeningDetailTableColumnVO> temp;
			temp=openingDAO.getDetailTableColumn(opn_num, i+1);
			openingDetailTableColumnList.add(temp);
		}
		
		//삭제
		if(recruitmentAreaList!=null) {openingDAO.deleteRecruitmentArea(opn_num);}
		if(preferenceList!=null) {openingDAO.deletePreference(opn_num);}
		if(recruitmentAreaList!=null) {openingDAO.deleteRecruitmentInfo(opn_num); }
		if(workLocationVO!=null) {openingDAO.deleteWorkLocation(opn_num); }
		if(openingDetailList!=null) {openingDAO.deleteOpnDetail(opn_num); }
		if(openingDetailTableColumnList!=null) {openingDAO.deleteOpnDetailTableColumn(opn_num); }
		if(openingDetailTableList!=null) {openingDAO.deleteOpnDetailTable(opn_num); }
		if(openingFilesList!=null) {openingDAO.deleteFiles(opn_num); }
		if(openingVO!=null) {openingDAO.deleteOpn(opn_num); }
		
		return null;
	}

}
