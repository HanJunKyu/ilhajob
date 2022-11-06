package com.ilhajob.app.user;

import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;
import com.ilhajob.app.user.dao.UserDAO;

public class SendSMS implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		String phoneNumber = req.getParameter("phone");
		UserDAO u_dao = new UserDAO();
		PrintWriter out = resp.getWriter();
		
		resp.setContentType("text/html;charset=utf-8");
				
		Random random  = new Random();
        String cerNum = "";
        for(int i=0; i<6; i++) {
            String ran = Integer.toString(random.nextInt(10));
            cerNum+=ran;
        }

        System.out.println(cerNum);
//        u_dao.certifiedPhoneNumber(phoneNumber,cerNum);
        out.println(cerNum);
		out.close();

		
		return null;
	}

}
