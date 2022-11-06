package com.ilhajob.app.bbs;

import java.io.File;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.bbs.dao.BbsDAO;
import com.ilhajob.app.bbs.dao.FilesDAO;
import com.ilhajob.app.bbs.vo.FilesVO;

public class BbsDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		BbsDAO b_dao = new BbsDAO();
		FilesDAO f_dao = new FilesDAO();
		
		ActionForward forward = new ActionForward();
		
		String saveFolder = "C:\\web_1530_hjk\\jsp\\workspace\\ilhajob\\WebContent\\app\\upload";
		
		int bbs_num = Integer.parseInt(req.getParameter("seq"));
		
		//파일 삭제(upload 경로 파일 삭제)
		for(FilesVO file : f_dao.getDeatail(bbs_num)) {
//			File f = new File(saveFolder, file.getFile_name());
			File f = new File(saveFolder + "\\" + file.getFile_bbs_name());
			//값의 유무 검사 + 삭제
			if(f.exists()) {
				f.delete();
			}
		}
		
		//파일 삭제(DB 테이블에 있는 파일 삭제)
		f_dao.deleteFiles(bbs_num);
		
		//게시글 삭제
		b_dao.deleteBbs(bbs_num);

		forward.setRedirect(true);
		forward.setPath(req.getContextPath() + "/bbs/BbsList.bo");
		
		return forward;
	}

}











