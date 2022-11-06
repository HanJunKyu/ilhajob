package com.ilhajob.app.opening;

import java.io.PrintWriter;
import java.util.ArrayList;

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

public class OpeningWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		ActionForward forward = null;
		
		boolean check=true;
		
		//VO
		OpeningVO openingVO = new OpeningVO();
		RecruitmentAreaVO recruitmentAreaVO = null;
		PreferenceVO preferenceVO = null;
		RecruitmentInfoVO recruitmentInfoVO = new RecruitmentInfoVO();
		WorkLocationVO workLocationVO = new WorkLocationVO();
		OpeningDetailVO openingDetailVO = null;
		OpeningDetailTableVO openingDetailTableVO = null;
		OpeningDetailTableColumnVO openingDetailTableColumnVO = null;
		OpeningFilesVO openingFilesVO = new OpeningFilesVO();
		
		//DAO
		OpeningDAO openingDAO = new OpeningDAO();
		
		//이미지 인서트
		System.out.println("OpeningWrtieOkAction : app 폴더 변경하세요~");
		String saveFolder = "C:\\web\\temp\\workspace\\ilhajob\\WebContent\\app\\upload";
//		String saveFolder = "/Users/yulim/Documents/study/(6) JSP/workspace/ilhajob/WebContent/app/upload";
		int fileSize = 5 * 1024 * 1024; // 5M
		//메일 서버 객체(파일 업로드 및 다운로드)
		MultipartRequest multi = null;
		multi = new MultipartRequest(req, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		
		//openingVO : 중복 없는 데이터 인서트
		String comp_id=multi.getParameter("comp_id");
		//System.out.println(comp_id);
		openingVO.setOpn_comp_name(openingDAO.getCompName(comp_id));
		openingVO.setOpn_comp_id(comp_id);
		openingVO.setOpn_title(multi.getParameter("opening_title"));
		openingVO.setOpn_career(multi.getParameter("career"));
		openingVO.setOpn_edu(multi.getParameter("education"));
		openingVO.setOpn_work_type(multi.getParameter("work_form"));
		openingVO.setOpn_work_time(multi.getParameter("work_date"));
		openingVO.setOpn_salary(multi.getParameter("salary"));
		openingVO.setOpn_recruit_method(multi.getParameter("apply_method").equals("1")?"일하잡 자체 지원":"홈페이지 지원");
		openingVO.setOpn_recruit_name(multi.getParameter("manager_name"));
		openingVO.setOpn_recruit_email(multi.getParameter("manager_email"));
		openingVO.setOpn_recruit_contact(multi.getParameter("manager_contact"));
		openingVO.setOpn_recruit_start(multi.getParameter("real_start_time"));
		openingVO.setOpn_recruit_end(multi.getParameter("real_end_time"));
		if(multi.getParameter("apply_method").equals("2")) {openingVO.setOpn_url(multi.getParameter("page_url"));}
		if(!openingDAO.insertOnlyData(openingVO)){
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html;charset=utf-8");
			out.println("<script>");
			out.println("alert('공고 입력 실패. 잠시 후 다시 시도해주세요.')");
			out.println("</script>");
			out.close();
			check=false;
		}

		//가장 최근에 추가한 공고 번호 가져오기
		int opn_num=openingDAO.selectOpeningNum();
		
		//채용중인지 검사
		openingDAO.updateIsRecruiting(opn_num);
		
		
		//모집부문 recruitmentAreaVO
		String[] recruitmentAreas=multi.getParameterValues("recruitment_area");
		for(int i=0;i<recruitmentAreas.length;i++) {
			recruitmentAreaVO=new RecruitmentAreaVO();
			recruitmentAreaVO.setOpn_num(opn_num);
			recruitmentAreaVO.setRecruitment_area(recruitmentAreas[i]);
			//하나씩 인서트
			if(!openingDAO.insertRecruitmentArea(recruitmentAreaVO)) {
				PrintWriter out = resp.getWriter();
				resp.setContentType("text/html;charset=utf-8");
				out.println("<script>");
				out.println("alert('모집분야 입력 실패. 잠시 후 다시 시도해주세요.')");
				out.println("</script>");
				out.close();
				check=false;
			}
		}
		
		//우대사항 preferenceVO
		String[] preferences=multi.getParameterValues("preference");
		for(int i=0; i<preferences.length;i++) {
			preferenceVO=new PreferenceVO();
			preferenceVO.setOpn_num(opn_num);
			preferenceVO.setPreference(preferences[i]);
			//하나씩 인서트
			if(!openingDAO.insertPreference(preferenceVO)) {
				PrintWriter out = resp.getWriter();
				resp.setContentType("text/html;charset=utf-8");
				out.println("<script>");
				out.println("alert('우대사항 입력 실패. 잠시 후 다시 시도해주세요.')");
				out.println("</script>");
				out.close();
				check=false;
			}
		}
		
		//채용분야 recruitmentInfoVO
		recruitmentInfoVO.setOpn_num(opn_num);
		recruitmentInfoVO.setRecruitment_inf1(multi.getParameter("recruitment_inf1"));
		recruitmentInfoVO.setRecruitment_inf2(multi.getParameter("recruitment_inf2"));
		recruitmentInfoVO.setRecruitment_inf3(multi.getParameter("recruitment_inf3"));
		//인서트
		if(!openingDAO.insertRecruitmentInfo(recruitmentInfoVO)) {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html;charset=utf-8");
			out.println("<script>");
			out.println("alert('채용분야 입력 실패. 잠시 후 다시 시도해주세요.')");
			out.println("</script>");
			out.close();
			check=false;
		}
		
		//근무지역 workLocationVO
		workLocationVO.setOpn_num(opn_num);
		workLocationVO.setWork_location1(multi.getParameter("work_location1"));
		workLocationVO.setWork_location2(multi.getParameter("work_location2"));
		workLocationVO.setWork_location3(multi.getParameter("work_location3"));
		//인서트
		if(!openingDAO.insertWorkLocation(workLocationVO)) {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html;charset=utf-8");
			out.println("<script>");
			out.println("alert('근무지역 입력 실패. 잠시 후 다시 시도해주세요.')");
			out.println("</script>");
			out.close();
			check=false;
		}
		
		//상세내용 총 개수
		int detail_count=Integer.parseInt(multi.getParameter("detail_count"));
		
		
		//상세내용 인서트
		for(int i=0; i<detail_count; i++) {
			openingDetailVO=new OpeningDetailVO();
			openingDetailVO.setOpn_num(opn_num);
			openingDetailVO.setOpn_detail_num(i+1);
			openingDetailVO.setOpn_detail_title(multi.getParameter("d"+(i+1)));
			openingDetailVO.setOpn_detail_content(multi.getParameter("d"+(i+1)+"_text"));
			//인서트
			if(!openingDAO.insertDetail(openingDetailVO)) {
				PrintWriter out = resp.getWriter();
				resp.setContentType("text/html;charset=utf-8");
				out.println("<script>");
				out.println("alert('상세정보 입력 실패. 잠시 후 다시 시도해주세요.')");
				out.println("</script>");
				out.close();
				check=false;
			}
		}
		
		//상세테이블 총 개수
		int table_count=Integer.parseInt(multi.getParameter("table_count"));

		//테이블과 테이블 셀 인서트
		for(int i=0; i<table_count;i++) {
			openingDetailTableVO=new OpeningDetailTableVO();
			openingDetailTableVO.setOpn_num(opn_num);
			openingDetailTableVO.setOpn_detail_table_num(i+1);
			openingDetailTableVO.setopn_detail_table_title(multi.getParameter("t"+(i+1)));
			//테이블 인서트
			if(!openingDAO.insertDetailTable(openingDetailTableVO)) {
				PrintWriter out = resp.getWriter();
				resp.setContentType("text/html;charset=utf-8");
				out.println("<script>");
				out.println("alert('상세정보 테이블 입력 실패. 잠시 후 다시 시도해주세요.')");
				out.println("</script>");
				out.close();
				check=false;
			}
			//테이블 셀 인서트
			String cells[]=multi.getParameterValues("t"+(i+1)+"_cell");
			for(int j=0;j<cells.length/2;j++) {
				openingDetailTableColumnVO=new OpeningDetailTableColumnVO();
				openingDetailTableColumnVO.setOpn_num(opn_num);
				//detail_table_num을 조회하는 dao 필요
				int table_num=i+1;
				openingDetailTableColumnVO.setOpn_detail_table_num(table_num);
				openingDetailTableColumnVO.setRow_num(j+1);
				openingDetailTableColumnVO.setColumn1(cells[j*2]);
				openingDetailTableColumnVO.setColumn2(cells[(j*2)+1]);
				//테이블 셀 인서트
				if(!openingDAO.insertDetailTableColumn(openingDetailTableColumnVO)) {
					PrintWriter out = resp.getWriter();
					resp.setContentType("text/html;charset=utf-8");
					out.println("<script>");
					out.println("alert('상세정보 테이블 셀 입력 실패. 잠시 후 다시 시도해주세요.')");
					out.println("</script>");
					out.close();
					check=false;
				}
			}
		}
		
		
		//이미지 인서트
		if(!openingDAO.insertFiles(opn_num, multi)) {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html;charset=utf-8");
			out.println("<script>");
			out.println("alert('이미지 입력 실패. 잠시 후 다시 시도해주세요.')");
			out.println("</script>");
			out.close();
			check=false;
		}
		
		
		//최종 일괄 처리
		if(check) {
			//opn_num
			req.setAttribute("opn_num", opn_num);
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(req.getContextPath()+"/opening/OpeningView.opn?opn_num="+opn_num);
			return forward;
		}
		
		return null;
	}

}
