package com.ilhajob.app.bbs;

import java.io.File;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.bbs.dao.BbsDAO;
import com.ilhajob.app.bbs.dao.FilesDAO;
import com.ilhajob.app.bbs.vo.BbsVO;
import com.ilhajob.app.bbs.vo.FilesVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BbsModifyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ActionForward forward = null;
		
		BbsDAO b_dao = new BbsDAO();
		FilesDAO f_dao = new FilesDAO();
		
		MultipartRequest multi = null;
		
		String saveFolder = "C:\\web_1530_hjk\\jsp\\workspace\\ilhajob\\WebContent\\app\\upload";
		int fileSize = 5 * 1024 * 1024;
		
		try {
			BbsVO b_vo = new BbsVO();
			multi = new MultipartRequest(req, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			int bbs_num = Integer.parseInt(multi.getParameter("seq"));
			
			//업로드 폴더의 파일 삭제
			for(FilesVO file : f_dao.getDeatail(bbs_num)) {
				File f = new File(saveFolder, file.getFile_bbs_name());
				if(f.exists()) {
					f.delete();
				}
			}
			
			//기존 파일은 유지되지 않는다.
			
			//DB에서 삭제
			f_dao.deleteFiles(bbs_num);
			//새롭게 추가한 첨부파일 DB에 추가
			f_dao.insertFiles(bbs_num, multi);
			
			//수정된 게시글 제목과 내용, 게시글 번호 MODEL에 set
			b_vo.setBbs_num(bbs_num);
			b_vo.setBbs_title(multi.getParameter("bbs_title"));
			b_vo.setBbs_content(multi.getParameter("bbs_content"));
			b_vo.setBbs_job(multi.getParameter("bbs_job"));
			b_vo.setBbs_category(multi.getParameter("bbs_category"));
			
			//게시글 수정
			b_dao.updateBbs(b_vo);
			
			forward = new ActionForward();
			
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/bbs/BbsView.bo?seq=" + bbs_num);
			
		} catch (Exception e) {
			//자바스크립트로 안내 메세지 응답
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html;charset=UTF-8");
			out.println("<script>");
			out.println("alert('게시글 수정 실패. 다시 시도해주세요.')");
			out.println("</script>");
			out.close();
		}
		return forward;
	}
}















