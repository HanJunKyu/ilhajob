package com.ilhajob.app.bbs;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.app.bbs.dao.BbsDAO;
import com.ilhajob.app.bbs.vo.BbsVO;
import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.bbs.dao.BbsDAO;
import com.ilhajob.app.bbs.dao.FilesDAO;
import com.ilhajob.app.bbs.vo.BbsVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class BbsWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		FilesDAO f_dao = new FilesDAO();
		BbsDAO b_dao = new BbsDAO();
		BbsVO b_vo = new BbsVO();
		//리눅스 서버에 프로젝트 업로드 시 아래의 경로를 사용해준다.
		//request.getServletContext().getRealPath("/") + "\\upload폴더명"
		String saveFolder = "/Users/yulim/Documents/study/(6) JSP/workspace/ilhajob/WebContent/app/upload";
		
		int fileSize = 5 * 1024 * 1024; // 5M
		
		//MultipartRequest를 사용하기 위해서는 WEB-INF/lib에 cos.jar를 반드시 추가해주어야 한다.
		//BuildPath에 넣지 않아도 된다!
		
		//메일 서버 객체(파일 업로드 및 다운로드)
		MultipartRequest multi = null;
		
		//외부에서 file type의 데이터가 전달된다면 MultipartRequest객체로 전달받아야 하고,
		//file type이 아니더라도 같이 전달된 데이터들도 마찬가지로 MultipartRequest로 처리해주어야 한다.
		
		//DefaultFileRenamePolicy : 파일 업로드 및 다운로드 정책(같은 이름이 존재하면 자동으로 이름이 변경되도록 한다).
		multi = new MultipartRequest(req, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		b_vo.setBbs_title(multi.getParameter("bbs_title"));
		b_vo.setBbs_content(multi.getParameter("bbs_content"));
		b_vo.setUser_id(multi.getParameter("user_id"));
		b_vo.setBbs_job(multi.getParameter("bbs_job"));
		b_vo.setBbs_category(multi.getParameter("bbs_category"));
		
		//TABLE_BOARD 테이블에 게시글 추가
		if(b_dao.insertBbs(b_vo)) {
			//TABLE_FILES 테이블에 게시글 추가
			if(f_dao.insertFiles(b_dao.getBbsSeq(), multi)) {
				ActionForward forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath(req.getContextPath() + "/bbs/BbsList.bo");
				
				return forward;
			}
		}
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=utf-8");
		
		out.println("<script>");
		out.println("alert('게시글 등록 실패. 다시 시도해주세요.');");
		out.println("</script>");
		out.close();
		
		return null;
	}

}














