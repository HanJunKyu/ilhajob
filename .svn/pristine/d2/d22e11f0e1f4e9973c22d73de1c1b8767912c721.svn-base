package com.ilhajob.app.comp.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ilhajob.app.comp.vo.CompApplyVO;
import com.ilhajob.app.comp.vo.CompVO;
import com.ilhajob.app.opening.vo.OpeningVO;
import com.ilhajob.app.user.vo.UserApplyVO;
import com.ilhajob.mybatis.config.SqlMapConfig;

public class CompDAO {

	private static final int KEY = 3;

	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession sql_session;

	public CompDAO() {
		// 세션 팩토리로 세션을 열어주고 모든 쿼리문은 auto 커밋으로 설정
		sql_session = sessionf.openSession(true);
	}

	// 회원가입
	public boolean join(CompVO comp) {
		comp.setComp_pw(encrypt(comp.getComp_pw()));
		return sql_session.insert("Comp.join", comp) == 1;
	}

	// 아이디검사
	public boolean checkId(String comp_id) {
		return (Integer) sql_session.selectOne("Comp.checkId", comp_id) == 1;
	}

	public boolean login(String comp_id, String comp_pw) {
		HashMap<String, String> comp = new HashMap<>();

		comp.put("comp_id", comp_id);
		comp.put("comp_pw", encrypt(comp_pw));

		return (Integer) sql_session.selectOne("Comp.login", comp) == 1;
	}

	// 아이디 찾기
	public List<CompVO> findId(String comp_name, String comp_phone) {
		HashMap<String, String> user = new HashMap<>();

		user.put("comp_name", comp_name);
		user.put("comp_phone", comp_phone);

		return sql_session.selectList("Comp.findId", user);
	}

	// 비번 찾기
	public String findPw(String comp_name, String comp_phone, String comp_id) {
		HashMap<String, String> user = new HashMap<>();

		user.put("comp_name", comp_name);
		user.put("comp_phone", comp_phone);
		user.put("comp_id", comp_id);

		if (sql_session.selectOne("Comp.findPw", user) != null) {

			return decrypt(sql_session.selectOne("Comp.findPw", user));

		} else {
			return null;
		}
	}

	// 기업정보 가져오기
	public CompVO getCompInfo(String user_id) {
		return sql_session.selectOne("Comp.getCompInfo", user_id);
	}

	public String getDoe(String user_id) {
		return sql_session.selectOne("Comp.getDoe", user_id);
	}

	// 기업정보 수정
	public boolean editCompInfo(CompVO comp) {

		String file = comp.getComp_photo();
		boolean check = true;

		if (file != null) {

			comp.setComp_photo(file);
			editCompPhoto(comp);

		} else {

			deleteCompPhoto(comp.getComp_id());
		}

		if (sql_session.update("Comp.editCompInfo", comp) != 1) {
			check = false;
		}

		return check;

	}

	public boolean editCompUserInfo(CompVO comp) {
		return sql_session.update("Comp.editCompUserInfo", comp) != 1;
	}

	// 프로필 사진 삭제
	public void deleteCompPhoto(String user_id) {
		sql_session.update("Comp.deleteCompPhoto", user_id);
	}

	// 프로필 사진 수정
	public void editCompPhoto(CompVO comp) {
		sql_session.update("Comp.editCompPhoto", comp);
	}

	// 지원목록 삽입
	public boolean compApply(CompApplyVO c_vo) {
		return sql_session.insert("Comp.compApply", c_vo) == 1;
	}
	
	
	// 지원목록 개수 가져오기
	public int getResumeCnt(String id) {
		return sql_session.selectOne("Comp.getResumeCnt", id);
	}
	
	// 지원목록 필터링된 개수 가져오기
	public int getStatusResumeCnt(String id, String status) {
		HashMap<String, String> map = new HashMap<>();
		map.put("comp_user_id", id);
		map.put("status", status);
		
		return sql_session.selectOne("Comp.getStatusResumeCnt", map);
	}
	
	// 지원목록 가져오기
	public List<CompApplyVO> getCompApply(String id, String status, int startRow, int endRow) {
		HashMap<String, Object> pageMap = new HashMap<>();
		
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("status", status);
		pageMap.put("comp_user_id", id);
		
		return status == null ? sql_session.selectList("Comp.getCompApply", pageMap) : 
			sql_session.selectList("Comp.getStatusCompApply", pageMap);
	}


	// 지원정보 가져오기
	public CompApplyVO getCompApplyInfo(int num) {
		return sql_session.selectOne("Comp.getCompApplyInfo", num);
	}

	// 열람 상태 변경
	public void editStatus(int num) {
		sql_session.update("Comp.editStatus", num);
	}
	
	//지원자 수 
	public int getApplyCnt(String id) {
		return sql_session.selectOne("Comp.getApplyCnt", id);
	}
	
	//미열람 개수 
	public int getReadCnt(String id) {
		return sql_session.selectOne("Comp.getReadCnt", id);
	}
	
	//오늘마감 공고 개수 
	public int getEndOpnCnt(String id, String date) {
		HashMap<String, String> map = new HashMap<>();
		map.put("comp_user_id", id);
		map.put("date", date);
		
		return sql_session.selectOne("Comp.getEndOpnCnt", map);
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

}
