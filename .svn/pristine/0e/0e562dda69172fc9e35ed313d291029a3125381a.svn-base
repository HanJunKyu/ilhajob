package com.ilhajob.app.user.dao;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.json.simple.JSONObject;

import com.ilhajob.app.bbs.vo.FilesVO;
import com.ilhajob.app.user.vo.ResumeActivityVO;
import com.ilhajob.app.user.vo.ResumeCareerVO;
import com.ilhajob.app.user.vo.ResumeEduVO;
import com.ilhajob.app.user.vo.ResumeInfoVO;
import com.ilhajob.app.user.vo.ResumeIntroduceVO;
import com.ilhajob.app.user.vo.ResumeLisenseVO;
import com.ilhajob.app.user.vo.ResumeMilitaryVO;
import com.ilhajob.app.user.vo.ResumePortfolioVO;
import com.ilhajob.app.user.vo.ResumeUrlVO;
import com.ilhajob.app.user.vo.UserApplyVO;
import com.ilhajob.app.user.vo.UserVO;
import com.ilhajob.mybatis.config.SqlMapConfig;
import com.oreilly.servlet.MultipartRequest;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class UserDAO {

	private static final int KEY = 3;

	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession sql_session;

	public UserDAO() {
		// 세션 팩토리로 세션을 열어주고 모든 쿼리문은 auto 커밋으로 설정
		sql_session = sessionf.openSession(true);
	}

	// 회원가입
	public boolean join(UserVO user) {
		user.setUser_pw(encrypt(user.getUser_pw()));
		return sql_session.insert("User.join", user) == 1;
	}

	// 아이디검사
	public boolean checkId(String user_id) {
		return (Integer) sql_session.selectOne("User.checkId", user_id) == 1;
	}

	// 로그인
	public boolean login(String user_id, String user_pw) {
		HashMap<String, String> user = new HashMap<>();

		user.put("user_id", user_id);
		user.put("user_pw", encrypt(user_pw));

		return (Integer) sql_session.selectOne("User.login", user) == 1;
	}

	// 아이디 찾기
	public List<UserVO> findId(String user_name, String user_phone) {
		HashMap<String, String> user = new HashMap<>();

		user.put("user_name", user_name);
		user.put("user_phone", user_phone);

		return sql_session.selectList("User.findId", user);
	}

	// 비번 찾기
	public String findPw(String user_name, String user_phone, String user_id) {
		HashMap<String, String> user = new HashMap<>();

		user.put("user_name", user_name);
		user.put("user_phone", user_phone);
		user.put("user_id", user_id);

		if (sql_session.selectOne("User.findPw", user) != null) {

			return decrypt(sql_session.selectOne("User.findPw", user));
		} else {
			return null;
		}
	}

	// 회원정보 가져오기
	public UserVO getUser(String user_id) {
		return sql_session.selectOne("User.getUser", user_id);
	}

	// 지원하기
	public boolean apply(UserApplyVO a_vo) {
		return sql_session.insert("User.apply", a_vo) == 1;
	}

	// 지원목록
	public List<UserApplyVO> getApply(String id) {
		return sql_session.selectList("User.getApply", id);
	}

	// 열람 상태 변경
	public void editStatus(UserApplyVO a_vo) {
		sql_session.update("User.editStatus", a_vo);
	}

	// 지원완료 개수
	public int getApplyCnt(String user_id) {
		return sql_session.selectOne("User.getApplyCnt", user_id);
	}

	// 이력서 열람 개수
	public int getReadCnt(String user_id) {
		return sql_session.selectOne("User.getReadCnt", user_id);
	}

	// 지원완료 개수
	public int getScrapCnt(String user_id) {
		return sql_session.selectOne("User.getScrapCnt", user_id);
	}

	// 정보 수정
	public boolean editInfo(UserVO user) {

		String file = user.getUser_photo();
		boolean check = true;

		if (file != null) {

			user.setUser_photo(file);
			editUserPhoto(user);

		} else {

			deleteUserPhoto(user.getUser_id());
		}

		if (sql_session.update("User.editInfo", user) != 1) {
			check = false;
		}

		return check;

	}

	// 프로필 사진 삭제
	public void deleteUserPhoto(String user_id) {
		sql_session.update("User.deleteUserPhoto", user_id);
	}

	// 프로필 사진 수정
	public void editUserPhoto(UserVO user) {
		sql_session.update("User.editUserPhoto", user);
	}

	// 이력서 사진 삭제
	public void deleteUserResumePhoto(String user_id) {
		sql_session.update("User.deleteUserResumePhoto", user_id);
	}

	// 이력서 사진 수정
	public void editUserResumePhoto(ResumeInfoVO user) {
		sql_session.update("User.editUserResumePhoto", user);
	}

	// 이력서 인적사항 수정
	public boolean editResumeInfo(ResumeInfoVO user) {

		String file = user.getUser_resume_photo();
		boolean check = true;

		if (file != null) {

			user.setUser_resume_photo(file);
			editUserResumePhoto(user);

		} else {

			deleteUserResumePhoto(user.getUser_id());
		}

		if (sql_session.update("User.editResumeInfo", user) != 1) {
			check = false;
		}

		return check;
	}

	// 이력서 인적사항 작성
	public boolean insertResumeInfo(ResumeInfoVO i_vo) {
		return sql_session.insert("User.insertResumeInfo", i_vo) == 1;
	}

	// 이력서 학력 작성
	public boolean insertResumeEdu(ResumeEduVO e_vo) {
		return sql_session.insert("User.insertResumeEdu", e_vo) == 1;
	}

	// 이력서 경력 작성
	public boolean insertResumeCareer(ResumeCareerVO c_vo) {
		return sql_session.insert("User.insertResumeCareer", c_vo) == 1;
	}

	// 이력서 대외활동 작성
	public boolean insertResumeActivity(ResumeActivityVO a_vo) {
		return sql_session.insert("User.insertResumeActivity", a_vo) == 1;
	}

	// 이력서 자격증 작성
	public boolean insertResumeLisense(ResumeLisenseVO l_vo) {
		return sql_session.insert("User.insertResumeLisense", l_vo) == 1;
	}

	// 이력서 자소서 작성
	public boolean insertResumeIntroduce(ResumeIntroduceVO in_vo) {
		return sql_session.insert("User.insertResumeIntroduce", in_vo) == 1;
	}

	// 이력서 병역 작성
	public boolean insertResumeMilitary(ResumeMilitaryVO m_vo) {
		return sql_session.insert("User.insertResumeMilitary", m_vo) == 1;
	}

	// 이력서 포트폴리오 작성
	public boolean insertResumePortfolio(ResumePortfolioVO p_vo) {
//			Enumeration<String> files = multi.getFileNames();
//			boolean check = true;
//			
//			ResumePortfolioVO p_vo = new ResumePortfolioVO();
//			p_vo.setId(id);
//			
//			while(files.hasMoreElements()) {
//				//사용자가 업로드한 파일명
//				String data = files.nextElement();
//				//사용자가 업로드한 파일명을 통해서 중복이 없는 시스템파일명을 가져온다.
//				String systemName = multi.getFilesystemName(data);
//				
//				p_vo.setPortfolio_name(systemName);
//
//				if(sql_session.insert("User.insertResumePortfolio", p_vo) != 1) {
//					//추가 실패 시 check에 false 대입.
//					check = false;
//					break;
//				}
//			}
//			return check;

		return sql_session.insert("User.insertResumePortfolio", p_vo) == 1;
	}

	public boolean insertResumeUrl(ResumeUrlVO u_vo) {
		return sql_session.insert("User.insertResumeUrl", u_vo) == 1;
	}

	// 나이 구하기
	public int getAge(String birth) {
		return sql_session.selectOne("User.getAge", birth);
	}

	// 이력서 인적사항 가져오기
	public ResumeInfoVO getResumeInfo(String id) {
		return sql_session.selectOne("User.getResumeInfo", id);
	}

	// 이력서 학력사항 가져오기
	public List<ResumeEduVO> getResumeEdu(String id) {
		return sql_session.selectList("User.getResumeEdu", id);
	}

	// 이력서 경력사항 가져오기
	public List<ResumeCareerVO> getResumeCareer(String id) {
		return sql_session.selectList("User.getResumeCareer", id);
	}

	// 이력서 대외활동 가져오기
	public List<ResumeActivityVO> getResumeActivity(String id) {
		return sql_session.selectList("User.getResumeActivity", id);
	}

	// 이력서 자격증 가져오기
	public List<ResumeLisenseVO> getResumeLisense(String id) {
		return sql_session.selectList("User.getResumeLisense", id);
	}

	// 이력서 자소서 가져오기
	public String getResumeIntroduce(String id) {
		return sql_session.selectOne("User.getResumeIntroduce", id);
	}

	// 이력서 병역사항 가져오기
	public ResumeMilitaryVO getResumeMilitary(String id) {
		return sql_session.selectOne("User.getResumeMilitary", id);
	}

	public List<ResumePortfolioVO> getResumePortfolio(String id) {
		return sql_session.selectList("User.getResumePortfolio", id);
	}

	public List<ResumeUrlVO> getResumeUrl(String id) {
		return sql_session.selectList("User.getResumeUrl", id);
	}

	public void deleteResumePortfolio(String id) {
		sql_session.delete("User.deleteResumePortfolio", id);
	}

	public void deleteResumeInfo(String id) {
		sql_session.delete("User.deleteResumeInfo", id);
	}

	public void deleteResumeEdu(String id) {
		sql_session.delete("User.deleteResumeEdu", id);
	}

	public void deleteResumeCareer(String id) {
		sql_session.delete("User.deleteResumeCareer", id);
	}

	public void deleteResumeActivity(String id) {
		sql_session.delete("User.deleteResumeActivity", id);
	}

	public void deleteResumeLisense(String id) {
		sql_session.delete("User.deleteResumeLisense", id);
	}

	public void deleteResumeUrl(String id) {
		sql_session.delete("User.deleteResumeUrl", id);
	}

	public void deleteResumeMilitary(String id) {
		sql_session.delete("User.deleteResumeMilitary", id);
	}

	public void deleteResumeIntroduce(String id) {
		sql_session.delete("User.deleteResumeIntroduce", id);
	}

	// 암호화
	public String encrypt(String pw) {

		String en_pw = "";
		for (int i = 0; i < pw.length(); i++) {
			en_pw += (char) (pw.charAt(i) * KEY);
		}

		return en_pw;
	}

	// 복호화
	public String decrypt(String en_pw) {

		String de_pw = "";
		for (int i = 0; i < en_pw.length(); i++) {
			de_pw += (char) (en_pw.charAt(i) / KEY);
		}

		return de_pw;
	}

	// 인증번호 보내기
	public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

		String api_key = "NCSTYUU7D7GDWXNE";
		String api_secret = "JB3S4GAXFALFUGG01EKSJICS8I7HFGMR";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber); // 수신전화번호
		params.put("from", "01039022425"); // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
		params.put("type", "SMS");
		params.put("text", "[인증번호:" + cerNum + "]" + " 일하잡 인증번호 입니다.");
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}

	}

//		public static void main(String[] args) {
//			System.out.println("hh");
//			UserDAO u = new UserDAO();
//			u.certifiedPhoneNumber("01024782423", "5555");
//			
//		}

}
