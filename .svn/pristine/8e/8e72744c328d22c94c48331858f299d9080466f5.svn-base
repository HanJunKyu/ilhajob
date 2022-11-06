package com.ilhajob.app.user;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;
import com.ilhajob.app.user.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserMyInfoEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		UserDAO u_dao = new UserDAO();
		HttpSession session = req.getSession();
		
		
		String saveFolder = "/Users/yulim/Documents/study/(6) JSP/workspace/ilhajob/WebContent/app/upload";
		//String saveFolder = "C:\\web\\temp\\workspace\\ilhajob\\WebContent\\app\\upload";
				
		int fileSize = 5 * 1024 * 1024;
		MultipartRequest multi = null;
		
		multi = new MultipartRequest(req, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String user_id = (String)session.getAttribute("session_id");
		UserVO user = u_dao.getUser(user_id);
		String check = multi.getParameter("check");
		
		String user_photo = user.getUser_photo();
		String new_photo = multi.getFilesystemName("photo");

		// 사진을 선택하지 않았다
		if (new_photo == null) {
			// check가 noChange 즉 사진을 수정하지 않았다는 의미로 기존 유저포토 그대로 반환
			if (check.equals("noChange")) {
				user.setUser_photo(user_photo);

				// check가 remove 즉 사진을 삭제했다는걸 의미하므로 유저 사진 삭제
			} else if (check.equals("remove")) {

				File f = new java.io.File(saveFolder, user_photo);
				f.delete();
				user.setUser_photo(new_photo);

			}
			// 사진을 선택했고 기존 사진과 선택한 사진이 다르다 즉 사진을 변경했다
		} else if (user_photo != new_photo) {
			if (user_photo != null) {

				File f = new java.io.File(saveFolder, user_photo);
				f.delete();
				user.setUser_photo(new_photo);
			} else {
				user.setUser_photo(new_photo);

			}

		}
						
		user.setUser_name(multi.getParameter("name"));
		user.setUser_email(multi.getParameter("email"));
		user.setUser_phone(multi.getParameter("phone"));
		
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=utf-8");
		
		
		if(!u_dao.editInfo(user)){			
			

			 out.println("<script>");
			 out.println("alert('정보 수정 실패. 잠시후 다시 시도해 주세요')");
			 out.println("</script>");
			 out.close();
		}else {
			
			out.println("완료");
		}
	
		return null;
	}

}
