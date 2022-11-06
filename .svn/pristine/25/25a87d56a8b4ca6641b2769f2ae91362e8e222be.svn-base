package com.ilhajob.app.bbs;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilhajob.action.Action;
import com.ilhajob.action.ActionForward;

public class FileDownloadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		//요청된 파일이 저장되어 있는 경로
		String savePath = "C:\\web_1530_hjk\\jsp\\workspace\\ilhajob\\WebContent\\app\\upload";
		
		//사용자가 다운로드를 요청한 파일의 이름을 전달받는다.
		String file_name = req.getParameter("file_bbs_name");
		
		//Stream을 사용하기 때문에 충돌을 방지하기 위해서 사전에 객체 생성
		PrintWriter writer = resp.getWriter();
		
		//파일 불러오기
		InputStream in = null;
		
		//다운로드 구현하기
		OutputStream out = null;
		
		//해당 파일의 정보를 담는 객체
		File file = null;
		
		boolean check = true;
		
		try {
			//해당 경로에 있는 사용자가 요청한 파일 정보를 전달한다.
			file = new File(savePath, file_name);
			
			//해당 파일을 byte로 읽어온다.
			in = new FileInputStream(file);
			
		} catch (FileNotFoundException e) {
			//해당 경로에 파일이 존재하지 않으면 check에 false 대입
			check = false;
			System.out.println(e + " FileDownload.java 오류");
		}
		
		//브라우저 정보를 가져온다.
		String client = req.getHeader("User-Agent");
		
		resp.reset();
		resp.setContentType("application/octet-stream");	//파일 다운로드 타입 설정
		resp.setHeader("Content-Description", "JSP Generated Data"); //컨텐트 설명 수정
		
		
		if(check) {
			try {
				//해당 파일의 인코딩 설정
				file_name = new String(file_name.getBytes("UTF-8"), "ISO-8859-1");

				//인터넷 익스플로러로 다운로드 요청 시
				if(client.indexOf("MSIE") != -1) {
					//헤더 설정
					resp.setHeader("Content-Disposition", "attachment;filename="+file_name);
				}else {
					//그 외 브라우저일 경우 파일명 앞 뒤로 큰 따옴표를 붙여준다.
					resp.setHeader("Content-Disposition", "attachment;filename=\"" + file_name + "\"");
					//컨텐트 타입을 Header에 다시 한 번 설정해준다.
					resp.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
				}
				
				resp.setHeader("Content-Length", "" + file.length());
				
				//충돌이 발생할 수 있는 공유 공간을 비워준다.
				writer.flush();
				
				//다운로드 준비
				out = resp.getOutputStream();
				
				//해당 파일의 길이만큼 byte배열을 선언한다.
				//5M 파일이라면 file.length는 5 * 1024 * 1024이다.
				byte b[] = new byte[(int)(file.length())];
				
				//다운로드한 바이트의 내용을 저장할 변수
				int len = 0;
				
				//read()
				//InputStream 객체에 담긴 파일 내용을 전달한 byte[]배열에 저장해준다.
				//저장한 후 해당 바이트 값을 리턴한다.
				//만약 읽은 바이트 값이 0이라면 파일을 모두 읽은 것이다.
				//따라서 파일의 내용을 반복하여 읽어 온 후 더 이상 읽어올 내용이 없을 때 까지 무한 반복
				//offset : 상대 주소 (C언어 구조체)
				while((len = in.read(b)) > 0) {
					out.write(b, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(in != null) {
						in.close();
					}
					if(out != null) {
						out.close();
					}
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		}
		return null;
	}
}

















