package com.ilhajob.app.comp;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.comp.dao.CompDAO;
import com.ilhajob.app.comp.vo.CompVO;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CompInfoEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		CompDAO c_dao = new CompDAO();
		HttpSession session = req.getSession();

		String saveFolder = "/Users/yulim/Documents/study/(6) JSP/workspace/ilhajob/WebContent/app/upload";
		//String saveFolder = "C:\\web\\temp\\workspace\\ilhajob\\WebContent\\app\\upload";
		int fileSize = 20 * 1024 * 1024;
		MultipartRequest multi = null;

		multi = new MultipartRequest(req, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

		String user_id = (String) session.getAttribute("session_id");
		CompVO comp = c_dao.getCompInfo(user_id);
		String check = multi.getParameter("check");
		String comp_photo = comp.getComp_photo();
		String new_photo = multi.getFilesystemName("photo");

		// 사진을 선택하지 않았다
		if (new_photo == null) {
			// check가 noChange 즉 사진을 수정하지 않았다는 의미로 기존 유저포토 그대로 반환
			if (check.equals("noChange")) {
				comp.setComp_photo(comp_photo);

				// check가 remove 즉 사진을 삭제했다는걸 의미하므로 유저 사진 삭제
			} else if (check.equals("remove")) {

				File f = new java.io.File(saveFolder, comp_photo);
				f.delete();
				comp.setComp_photo(new_photo);

			}
			// 사진을 선택했고 기존 사진과 선택한 사진이 다르다 즉 사진을 변경했다
		} else if (comp_photo != new_photo) {
			if (comp_photo != null) {

				File f = new java.io.File(saveFolder, comp_photo);
				f.delete();
				comp.setComp_photo(new_photo);
			} else {
				comp.setComp_photo(new_photo);

			}

		}

		comp.setComp_type(multi.getParameter("comp_type"));
		comp.setComp_name(multi.getParameter("comp_name"));
		comp.setComp_ceo(multi.getParameter("comp_ceo"));
		comp.setComp_crn(multi.getParameter("comp_crn"));
		comp.setComp_location(multi.getParameter("comp_location"));
		comp.setComp_income(Integer.parseInt(multi.getParameter("comp_income")));
		comp.setComp_doe(multi.getParameter("comp_doe"));
		comp.setComp_capital(multi.getParameter("comp_capital"));
		comp.setComp_workercnt(multi.getParameter("comp_worker"));

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=utf-8");

		if (!c_dao.editCompInfo(comp)) {

			out.println("<script>");
			out.println("alert('정보 수정 실패. 잠시후 다시 시도해 주세요')");
			out.println("</script>");
			out.close();
		} else {

			out.println("완료");
		}

		return null;

	}

}
