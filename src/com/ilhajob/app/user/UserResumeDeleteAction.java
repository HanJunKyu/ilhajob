package com.ilhajob.app.user;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.ResumePortfolioVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserResumeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		UserDAO u_dao = new UserDAO();
		
		String saveFolder = "/Users/yulim/Documents/study/(6) JSP/workspace/ilhajob/WebContent/app/upload";
		ActionForward forward = null;
		
			
			String id = (String)session.getAttribute("session_id");
			
			//업로드 폴더의 파일 삭제
			for(ResumePortfolioVO file : u_dao.getResumePortfolio(id)) {
				File f = new File(saveFolder, file.getPortfolio_name());
				if(f.exists()) {
					f.delete();
				}
			}
			
			if(u_dao.getResumeInfo(id).getUser_resume_photo() != null) {
				
				File f = new File(saveFolder, u_dao.getResumeInfo(id).getUser_resume_photo());
				if(f.exists()) {
					f.delete();
				}
			}
				
			//DB에서 삭제
			u_dao.deleteResumePortfolio(id);
			u_dao.deleteResumeInfo(id);
			u_dao.deleteResumeCareer(id);
			u_dao.deleteResumeActivity(id);
			u_dao.deleteResumeEdu(id);
			u_dao.deleteResumeLisense(id);
			u_dao.deleteResumeIntroduce(id);
			u_dao.deleteResumeUrl(id);
			u_dao.deleteResumeMilitary(id);
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/user/MyResume.us");
			return forward;

	}
}
