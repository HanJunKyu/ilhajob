package com.ilhajob.app.opening;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class OpeningViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		//DAO
		OpeningDAO openingDAO = new OpeningDAO();
		int opn_num=Integer.parseInt(req.getParameter("opn_num"));
		
		//VO
		String comp_name=null;
		OpeningVO openingVO = null;
		List<RecruitmentAreaVO> recruitmentAreaList= null;
		List<PreferenceVO> preferenceList = null;
		RecruitmentInfoVO recruitmentInfoVO = new RecruitmentInfoVO();
		WorkLocationVO workLocationVO = new WorkLocationVO();
		List<OpeningDetailVO> openingDetailList = null;
		List<OpeningDetailTableVO> openingDetailTableList = null;
		ArrayList<List<OpeningDetailTableColumnVO>> openingDetailTableColumnList = new ArrayList<List<OpeningDetailTableColumnVO>>();
		List<OpeningFilesVO> openingFilesList=null;
		
		
		//조회수 증가
		openingDAO.updateReadCount(opn_num);

		//db조회
		openingDAO.updateIsRecruiting(opn_num);
		openingVO=openingDAO.getOnlyData(opn_num);
		recruitmentAreaList=openingDAO.getRecruitmentArea(opn_num);
		preferenceList=openingDAO.getPreference(opn_num);
		workLocationVO=openingDAO.getWorkLocation(opn_num);
		openingDetailList=openingDAO.getOpnDetail(opn_num);
		openingDetailTableList=openingDAO.getDetailTable(opn_num);
		openingFilesList=openingDAO.getImg(opn_num);
		
		//테이블 개수만큼 반복
		for(int i=0;i<openingDetailTableList.size();i++) {
			//i+1 : 테이블 번호
			//List<OpeningDetailTableColumnVO> : 하나의 테이블에 해당하는 셀들
			//ArrayList<List<OpeningDetailTableColumnVO>> : 하나의 공고에 속한 테이블의 전체 셀들
			List<OpeningDetailTableColumnVO> temp;
			temp=openingDAO.getDetailTableColumn(opn_num, i+1);
			openingDetailTableColumnList.add(temp);
		}
		
		
		//응답
		if(openingVO!=null) {
			//중복없는데이터
			req.setAttribute("openingBean", openingVO);
			//모집분야
			if(recruitmentAreaList!=null) {
				req.setAttribute("recruitmentAreaBeans", recruitmentAreaList);
			}
			//우대사항
			if(preferenceList!=null) {
				req.setAttribute("preferenceBeans", preferenceList);
			}
			//채용분야
			if(recruitmentInfoVO!=null) {
				req.setAttribute("recruitmentInfoBean", recruitmentInfoVO);
			}
			//근무지역
			if(workLocationVO!=null) {
				req.setAttribute("workLocationBean", workLocationVO);
			}
			//상세정보
			if(openingDetailList!=null) {
				req.setAttribute("openingDetailBeans", openingDetailList);
			}
			//상세정보 테이블, 셀
			if(openingDetailTableList!=null) {
				req.setAttribute("openingDetailTableBeans", openingDetailTableList);
				if(openingDetailTableColumnList!=null) {
					req.setAttribute("openingDetailTableColumnBeans", openingDetailTableColumnList);
				}
			}
			//첨부파일
			if(openingFilesList!=null) {
				req.setAttribute("openingFilesBeans", openingFilesList);
			}
			
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/job-opening-page.jsp");
			return forward;
		}
		
		
		return null;
	}

}
