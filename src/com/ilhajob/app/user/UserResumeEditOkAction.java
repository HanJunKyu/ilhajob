package com.ilhajob.app.user;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.ResumeActivityVO;
import com.ilhajob.app.user.vo.ResumeCareerVO;
import com.ilhajob.app.user.vo.ResumeEduVO;
import com.ilhajob.app.user.vo.ResumeInfoVO;
import com.ilhajob.app.user.vo.ResumeIntroduceVO;
import com.ilhajob.app.user.vo.ResumeLisenseVO;
import com.ilhajob.app.user.vo.ResumeMilitaryVO;
import com.ilhajob.app.user.vo.ResumePortfolioVO;
import com.ilhajob.app.user.vo.ResumeUrlVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserResumeEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("session_id");
		UserDAO u_dao = new UserDAO();
		ResumeInfoVO i_vo = u_dao.getResumeInfo(id);
		ResumeEduVO e_vo = new ResumeEduVO();
		ResumeCareerVO c_vo = new ResumeCareerVO();
		ResumeActivityVO a_vo = new ResumeActivityVO();
		ResumeLisenseVO l_vo = new ResumeLisenseVO();
		ResumeIntroduceVO in_vo = new ResumeIntroduceVO();
		ResumeMilitaryVO m_vo = new ResumeMilitaryVO();
		ResumePortfolioVO p_vo = new ResumePortfolioVO();
		ResumeUrlVO u_vo = new ResumeUrlVO();
		
		String saveFolder = "C:\\web_1530_hjk\\jsp\\workspace\\ilhajob\\WebContent\\app\\upload";
		//String saveFolder = "C:\\web\\temp\\workspace\\ilhajob\\WebContent\\app\\upload";
				
		int fileSize = 5 * 1024 * 1024;
		MultipartRequest multi = null;
		ActionForward forward = null;
		
		try {
			multi = new MultipartRequest(req, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			String check = multi.getParameter("check");
			
			
			// 사진을 선택하지 않았다
			if(multi.getFilesystemName("photo") == null)  {
				//check가 noChange 즉 사진을 수정하지 않았다는 의미로 기존 유저포토 그대로 반환
				if(check.equals("noChange")) {
					i_vo.setUser_resume_photo(i_vo.getUser_resume_photo());
				
				//check가 remove 즉 사진을 삭제했다는걸 의미하므로 유저 사진 삭제
				}else if(check.equals("remove")) {
					
					File f = new java.io.File(saveFolder, i_vo.getUser_resume_photo());
					f.delete();
					i_vo.setUser_resume_photo(multi.getFilesystemName("photo"));

				}
			//사진을 선택했고 기존 사진과 선택한 사진이 다르다 즉 사진을 변경했다
			}else if(i_vo.getUser_resume_photo() != multi.getFilesystemName("photo")) {
				if(i_vo.getUser_resume_photo() != null) {
					
					File f = new java.io.File(saveFolder, i_vo.getUser_resume_photo());
					f.delete();
					i_vo.setUser_resume_photo(multi.getFilesystemName("photo"));
				}else {
					i_vo.setUser_resume_photo(multi.getFilesystemName("photo"));
					
				}
				
			}
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html;charset=utf-8");
				
			//DB에서 삭제
			u_dao.deleteResumePortfolio(id);
//			u_dao.deleteResumeInfo(id);
			u_dao.deleteResumeCareer(id);
			u_dao.deleteResumeActivity(id);
			u_dao.deleteResumeEdu(id);
			u_dao.deleteResumeLisense(id);
			u_dao.deleteResumeIntroduce(id);
			u_dao.deleteResumeUrl(id);
			u_dao.deleteResumeMilitary(id);
			
			
			//인적사항 추가
			i_vo.setUser_id(id);
			i_vo.setUser_name(multi.getParameter("name"));
			i_vo.setUser_email(multi.getParameter("email"));
			i_vo.setUser_birth(multi.getParameter("birth"));
			i_vo.setUser_gender(multi.getParameter("gender"));
			i_vo.setUser_phone(multi.getParameter("phone"));
			i_vo.setUser_address(multi.getParameter("address"));
			i_vo.setUser_address_detail(multi.getParameter("address_detail"));
			i_vo.setUser_resume_title(multi.getParameter("title"));
			u_dao.editResumeInfo(i_vo);
			
			if(multi.getParameter("education_name") != null) {
				
				String[] e_name = multi.getParameterValues("education_name");
				String[] e_status = multi.getParameterValues("education_status");
				String[] e_start = multi.getParameterValues("education_start");
				String[] e_end = multi.getParameterValues("education_end");
				String[] e_major = multi.getParameterValues("education_major");
				String[] e_grade = multi.getParameterValues("education_grade");
				
				for (int i = 0; i < e_name.length; i++) {
					e_vo.setId(id);
					e_vo.setEdu_name(e_name[i]);
					e_vo.setEdu_status(e_status[i]);
					e_vo.setEdu_start_date(e_start[i]);
					e_vo.setEdu_end_date(e_end[i]);
					e_vo.setEdu_major(e_major[i]);
					e_vo.setEdu_grade(e_grade[i]);
					u_dao.insertResumeEdu(e_vo);
				}
			}
			
			if(multi.getParameter("career_name") != null) {
				String[] c_name = multi.getParameterValues("career_name");
				String[] c_start = multi.getParameterValues("career_start");
				String[] c_end = multi.getParameterValues("career_end");
				String[] c_rank = multi.getParameterValues("career_rank");
				String[] c_position = multi.getParameterValues("career_position");
				String[] c_detail = multi.getParameterValues("career_detail");
				
				for (int i = 0; i < c_name.length; i++) {
					c_vo.setId(id);
					c_vo.setCareer_name(c_name[i]);
					c_vo.setCareer_start_date(c_start[i]);
					c_vo.setCareer_end_date(c_end[i]);
					c_vo.setCareer_rank(c_rank[i]);
					c_vo.setCareer_position(c_position[i]);
					c_vo.setCareer_detail(c_detail[i]);
					u_dao.insertResumeCareer(c_vo);
				}
			}
			
			if(multi.getParameter("activity_place") != null) {
				String[] a_place = multi.getParameterValues("activity_place");
				String[] a_type = multi.getParameterValues("activity_type");
				String[] a_start = multi.getParameterValues("activity_start");
				String[] a_end = multi.getParameterValues("activity_end");
				String[] a_detail = multi.getParameterValues("activity_detail");
				
				for (int i = 0; i < a_place.length; i++) {
					a_vo.setId(id);
					a_vo.setActivity_place(a_place[i]);
					a_vo.setActivity_type(a_type[i]);
					a_vo.setActivity_start_date(a_start[i]);
					a_vo.setActivity_end_date(a_end[i]);
					a_vo.setActivity_detail(a_detail[i]);
					u_dao.insertResumeActivity(a_vo);
				}
			}
			
			if(multi.getParameter("lisense_name") != null) {
				String[] l_name = multi.getParameterValues("lisense_name");
				String[] l_type = multi.getParameterValues("lisense_type");
				String[] l_publisher = multi.getParameterValues("lisensePublisher");
				String[] l_date = multi.getParameterValues("lisense_date");
				String[] l_result = multi.getParameterValues("lisense_result");
				
				for (int i = 0; i < l_name.length; i++) {
					l_vo.setId(id);
					l_vo.setLisense_name(l_name[i]);
					l_vo.setLisense_type(l_type[i]);
					l_vo.setLisense_publisher(l_publisher[i]);
					l_vo.setLisense_date(l_date[i]);
					l_vo.setLisense_result(l_result[i]);
					u_dao.insertResumeLisense(l_vo);
				}
			}
			
			if(multi.getParameter("introduce") != null) {
				in_vo.setId(id);
				in_vo.setIntroduce(multi.getParameter("introduce"));
				u_dao.insertResumeIntroduce(in_vo);
			}
			
			if(multi.getParameter("military_name") != null) {
				m_vo.setId(id);
				m_vo.setMilitary_name(multi.getParameter("military_name"));
				m_vo.setMilitary_type(multi.getParameter("military_type"));
				m_vo.setMilitary_rank(multi.getParameter("military_rank"));
				m_vo.setMilitary_start_date(multi.getParameter("military_start"));
				m_vo.setMilitary_end_date(multi.getParameter("military_end"));
				m_vo.setMilitary_reason(multi.getParameter("military_reason"));
				u_dao.insertResumeMilitary(m_vo);
			}
			
			if(multi.getParameter("portfolio_type") != null) {
				
//			String[] p_name = multi.getParameterValues("portfolio_name");
				Enumeration<String> files = multi.getFileNames();
				String[] p_type = multi.getParameterValues("portfolio_type");
				
				for (int i = 0; i < p_type.length; i++) {
					String data = files.nextElement();
					String systemName = multi.getFilesystemName(data);
//				if(systemName == multi.getFilesystemName("photo")) {continue;}
					p_vo.setId(id);
					p_vo.setPortfolio_name(systemName);
					p_vo.setPortfolio_type(p_type[i]);
					u_dao.insertResumePortfolio(p_vo);
				}
			}
			
			if(multi.getParameter("url") != null) {
				String[] url = multi.getParameterValues("url");
				String[] u_type = multi.getParameterValues("url_type");
				
				for (int i = 0; i < url.length; i++) {
					
					u_vo.setId(id);
					u_vo.setUrl(url[i]);
					u_vo.setUrl_type(u_type[i]);
					u_dao.insertResumeUrl(u_vo);
				}
			}
//			forward = new ActionForward();
//			forward.setRedirect(true);
//			forward.setPath(req.getContextPath() + "/user/MyResume.us");
			
			out.println("완료");
			
		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html;charset=UTF-8");
			out.println("<script>");
			out.println("alert('이력서 수정 실패. 다시 시도해주세요.')");
			out.println("</script>");
			out.close();
		}
		
		
		return forward;
		
	}

}
