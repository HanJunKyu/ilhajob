package com.ilhajob.app.opening.dao;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ilhajob.app.comp.vo.CompVO;
import com.ilhajob.app.opening.vo.OpeningDetailTableColumnVO;
import com.ilhajob.app.opening.vo.OpeningDetailTableVO;
import com.ilhajob.app.opening.vo.OpeningDetailVO;
import com.ilhajob.app.opening.vo.OpeningFilesVO;
import com.ilhajob.app.opening.vo.OpeningVO;
import com.ilhajob.app.opening.vo.PreferenceVO;
import com.ilhajob.app.opening.vo.RecruitmentAreaVO;
import com.ilhajob.app.opening.vo.RecruitmentInfoVO;
import com.ilhajob.app.opening.vo.WorkLocationVO;
import com.ilhajob.app.user.vo.UserScrapVO;
import com.ilhajob.mybatis.config.SqlMapConfig;
import com.oreilly.servlet.MultipartRequest;

public class OpeningDAO {

	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession sql_session;

	public OpeningDAO() {
		// 세션 팩토리로 세션을 열어주고 모든 쿼리문은 auto 커밋으로 설정
		sql_session = sessionf.openSession(true);
	}

	//채용중인지 업데이트
	public void updateIsRecruiting(int opn_num) {
		sql_session.update("Opening.updateIsRecruiting1",opn_num);
		sql_session.update("Opening.updateIsRecruiting2",opn_num);
	}

	/* 공고 작성 */

	// 중복없는 데이터 인서트
	public boolean insertOnlyData(OpeningVO openingVO) {
		boolean result=sql_session.insert("Opening.insertOnlyData", openingVO) == 1;
		updateIsRecruiting(openingVO.getOpn_num());
		return result;
	}
	
	
	public int getRecruitingOpnCnt(String comp_id) {
		return sql_session.selectOne("Opening.getRecruitingOpnCnt", comp_id);
	}

	/*
	 * 중복있는 데이터 인서트 모집부문, 우대사항, 채용분야, 근무지역 순서 0) Controller에서 req에 답긴 name 값들을 문자열
	 * 배열로 담은 후, 반복문으로 하나씩 VO 객체에 담아 dao 메소드를 호출함 1) dao 매개변수로 들어오는 것은 VO 객체 2) 공고
	 * 번호, 하위 구분자와 함께 String 인서트
	 */
	// 모집부문
	public boolean insertRecruitmentArea(RecruitmentAreaVO recruitmentAreaVO) {
		return sql_session.insert("Opening.insertRecruitmentArea", recruitmentAreaVO) == 1;
	}

	// 우대사항
	public boolean insertPreference(PreferenceVO preferenceVO) {
		return sql_session.insert("Opening.insertPreference", preferenceVO) == 1;
	}

	// 채용분야
	public boolean insertRecruitmentInfo(RecruitmentInfoVO recruitmentInfoVO) {
		return sql_session.insert("Opening.insertRecruitmentInfo", recruitmentInfoVO) == 1;
	}

	// 근무지역
	public boolean insertWorkLocation(WorkLocationVO workLocationVO) {
		return sql_session.insert("Opening.insertWorkLocation", workLocationVO) == 1;
	}

	/*
	 * 상세정보 순서 1) dao 매개변수로 들어오는 것은 VO 객체 2) 인서트
	 */
	// 상세정보
	public boolean insertDetail(OpeningDetailVO openingDetailVO) {
		return sql_session.insert("Opening.insertDetail", openingDetailVO) == 1;
	}

	/*
	 * 상세 정보 테이블 순서 0) Controller에서 이중 반복문 사용 : 바깥 반복문에서 표를 하나씩 가져와 상세정보 테이블 dao 메소드
	 * 실행 : 안쪽 반복문에서 셀에 담긴 값들을 2차원 배열에 담아놓고 반복문으로 VO에 넣어서 dao 메소드 실행 1) 상세정보 테이블 셀
	 * 인서트 - dao 매개변수로 들어오는 것은 OpeningDetailTableColumnVO 객체 - 인서트 2) 상세정보 테이블 인서트 -
	 * dao 매개변수로 들어오는 것은 OpeningDetailTableVO 객체 - 인서트
	 */

	// 공고 번호 조회
	public int selectOpeningNum() {
		return sql_session.selectOne("Opening.selectOpeningNum");
	}

	// 상세정보 테이블
	public boolean insertDetailTable(OpeningDetailTableVO openingDetailTableVO) {
		return sql_session.insert("Opening.insertDetailTable", openingDetailTableVO) == 1;
	}

	// 상세정보 테이블 셀
	public boolean insertDetailTableColumn(OpeningDetailTableColumnVO openingDetailTableColumnVO) {
		return sql_session.insert("Opening.insertDetailTableColumn", openingDetailTableColumnVO) == 1;
	}

	/* 이미지 */
	// 이미지 인서트
	public boolean insertFiles(int opn_num, MultipartRequest multi) {
		Enumeration<String> files = multi.getFileNames();
		boolean check = true;

		OpeningFilesVO file = new OpeningFilesVO();
		file.setOpn_num(opn_num);

		while (files.hasMoreElements()) {
			// 사용자가 업로드한 파일명
			String data = files.nextElement();
			// 사용자가 업로드한 파일명을 통해서 중복이 없는 시스템파일명을 가져온다.
			String systemName = multi.getFilesystemName(data);
			// 헤더이미지 여부

			// 슬롯 별로 검사하여 null이 아닐 때에만 DB에 INSERT한다.
			if (systemName == null) {
				continue;
			}
			file.setOpn_file_name(systemName);
			if (sql_session.insert("OpeningFiles.insertOpeningFiles", file) != 1) {
				// 추가 실패 시 check에 false 대입
				check = false;
				break;
			}
		}
		return check;
	}

	// 공고에 해당하는 이미지
	public List<OpeningFilesVO> getImg(int opn_num) {
		return sql_session.selectList("OpeningFiles.getOpeningFiles", opn_num);
	}

	// 이미지 삭제하기
	public void deleteFiles(int opn_num) {
		sql_session.delete("OpeningFiles.deleteOpeningFiles", opn_num);
	}

	/* 공고 페이지 조회 */

	// 조회수
	public void updateReadCount(int opn_num) {
		sql_session.update("Opening.updateReadCount", opn_num);
	}

	// 중복없는 데이터
	public OpeningVO getOnlyData(int opn_num) {
		updateIsRecruiting(opn_num);
		return sql_session.selectOne("Opening.getOnlyData", opn_num);
	}

	// 모집분야
	public List<RecruitmentAreaVO> getRecruitmentArea(int opn_num) {
		return sql_session.selectList("Opening.getRecruitmentArea", opn_num);
	}

	// 우대사항
	public List<PreferenceVO> getPreference(int opn_num) {
		return sql_session.selectList("Opening.getPreference", opn_num);
	}

	// 채용분야
	public RecruitmentInfoVO getRecruitmentInfo(int opn_num) {
		return sql_session.selectOne("Opening.getRecruitmentInfo", opn_num);
	}

	// 근무지역
	public WorkLocationVO getWorkLocation(int opn_num) {
		return sql_session.selectOne("Opening.getWorkLocation", opn_num);
	}

	// 상세정보
	public List<OpeningDetailVO> getOpnDetail(int opn_num) {
		return sql_session.selectList("Opening.getOpnDetail", opn_num);
	}

	// 상세정보 테이블
	public List<OpeningDetailTableVO> getDetailTable(int opn_num) {
		return sql_session.selectList("Opening.getDetailTable", opn_num);
	}

	// 상세정보 테이블 셀
	public List<OpeningDetailTableColumnVO> getDetailTableColumn(int opn_num, int detail_table_num) {
		HashMap<String, Integer> setter = new HashMap<>();
		setter.put("opn_num", opn_num);
		setter.put("detail_table_num", detail_table_num);
		return sql_session.selectList("Opening.getDetailTableColumn", setter);
	}

	// 가장 최근 공고번호
	public int getOpnNum() {
		return sql_session.selectOne("Opening.getOpnNum");
	}

	// 기업이름 가져오기
	public String getCompName(String comp_user_id) {
		return sql_session.selectOne("Opening.getCompName", comp_user_id);
	}

	/* 기업마이페이지 나의 공고 리스트 */
	public List<OpeningVO> listOpening(int tr_length, int show_length, String opn_comp_id) {
		HashMap<String, Object> setter = new HashMap<>();
		setter.put("opn_comp_id", opn_comp_id);
		setter.put("start_row", ((tr_length - 1) * show_length) + 1);
		setter.put("end_row", tr_length * show_length);
		List<OpeningVO> temp=sql_session.selectList("Opening.listOpening", setter);
		for(int i=0;i<temp.size();i++) {
			updateIsRecruiting(temp.get(i).getOpn_num());
		}
		List<OpeningVO> list=sql_session.selectList("Opening.listOpening", setter);
		return list;
	}
	
	public OpeningVO listOpeningByOpnNum(int opn_num) {
		return sql_session.selectOne("Opening.listOpeningByOpnNum", opn_num);
	}

	// 공고 최종 개수
	public int getOpeningTotalCnt(String opn_comp_id) {
		return sql_session.selectOne("Opening.getOpeningTotalCnt", opn_comp_id);
	}

	/* 삭제 */
	public void deleteRecruitmentArea(int opn_num) {
		sql_session.delete("Opening.deleteRecruitmentArea", opn_num);
	}

	public void deletePreference(int opn_num) {
		sql_session.delete("Opening.deletePreference", opn_num);
	}

	public void deleteRecruitmentInfo(int opn_num) {
		sql_session.delete("Opening.deleteRecruitmentInfo", opn_num);
	}

	public void deleteWorkLocation(int opn_num) {
		sql_session.delete("Opening.deleteWorkLocation", opn_num);
	}

	public void deleteOpnDetail(int opn_num) {
		sql_session.delete("Opening.deleteOpnDetail", opn_num);
	}

	public void deleteOpnDetailTable(int opn_num) {
		sql_session.delete("Opening.deleteOpnDetailTable", opn_num);
	}

	public void deleteOpnDetailTableColumn(int opn_num) {
		sql_session.delete("Opening.deleteOpnDetailTableColumn", opn_num);
	}

	public void deleteOpnFiles(int opn_num) {
		sql_session.delete("Opening.deleteOpnFiles", opn_num);
	}

	public void deleteOpn(int opn_num) {
		sql_session.delete("Opening.deleteOpn", opn_num);
	}

	// 기업 공고 개수 가져오기
	public int getOpeningCnt(String comp_user_id) {
		return sql_session.selectOne("Opening.getOpeningCnt", comp_user_id);
	}

	/* 키워드 검색 */
	// 통합 검색
	public List<OpeningVO> searchAll(String keyword, String work_location, String recruitment_area, int startRow,
			int endRow) {
		HashMap<String, String> setter = new HashMap<>();
		setter.put("keyword", keyword);
		setter.put("work_location", work_location);
		setter.put("recruitment_area", recruitment_area);
		setter.put("start", "" + startRow);
		setter.put("end", "" + endRow);
		List<OpeningVO> temp=sql_session.selectList("Opening.searchAll", setter);
		for(int i=0;i<temp.size();i++) {
			updateIsRecruiting(temp.get(i).getOpn_num());
		}
		List<OpeningVO> list=sql_session.selectList("Opening.searchAll", setter);
		return list;
	}

	// 기업 검색
	public List<CompVO> searchComp(String keyword, int startRow, int endRow) {
		HashMap<String, String> setter = new HashMap<>();
		setter.put("keyword", keyword);
		setter.put("start", "" + startRow);
		setter.put("end", "" + endRow);
		return sql_session.selectList("Opening.searchComp", setter);
	}

	// 통합검색 개수
	public int searchAllCount(String keyword, String work_location, String recruitment_area) {
		HashMap<String, String> setter = new HashMap<>();
		setter.put("keyword", keyword);
		setter.put("work_location", work_location);
		setter.put("recruitment_area", recruitment_area);
		return sql_session.selectOne("Opening.searchAllCount", setter);
	}

	// 기업검색 개수
	public int searchCompCount(String keyword) {
		return sql_session.selectOne("Opening.searchCompCount", keyword);
	}

	// 기업정보 가져오기
	public CompVO getCompInfo(String comp_id) {
		return sql_session.selectOne("Opening.getCompInfo", comp_id);
	}

	public List<OpeningVO> getCompOpening(String comp_id) {
		return sql_session.selectList("Opening.getCompOpening", comp_id);
	}
	
	//공고 넘버로 기업 이름 조회하기
	public String getCompNameByOpnNum(String opn_num) {
		return sql_session.selectOne("Opening.getCompNameByOpnNum", opn_num);
	}
	// 스크랩
	public boolean insertScrap(String user_id, int jobs_num) {
		HashMap<String, Object> setter = new HashMap<>();
		setter.put("user_id", user_id);
		setter.put("opn_num", jobs_num);
		return sql_session.insert("Opening.insertScrap", setter) == 1;
	}
	//스크랩 중복검사 : 참이면 중복
	public int isScrapDup(String user_id, int opn_num) {
		HashMap<String, Object> setter = new HashMap<>();
		setter.put("user_id", user_id);
		setter.put("opn_num", opn_num);
		return sql_session.selectOne("Opening.isScrapDup", setter);
	}
	//스크랩 리스트 조회
	public List<UserScrapVO> getScrapList(String user_id, int start,int end){
		HashMap<String, Object> setter = new HashMap<>();
		setter.put("user_id", user_id);
		setter.put("start", start);
		setter.put("end", end);
		List<UserScrapVO> temp=sql_session.selectList("Opening.getScrapList", setter);
		for(int i=0;i<temp.size();i++) {
			updateIsRecruiting(temp.get(i).getJobs_num());
		}
		List<UserScrapVO> list=sql_session.selectList("Opening.getScrapList", setter);
		return list;
	}
	//스크랩 리스트 개수
	public int getScrapCount(String user_id) {
		return sql_session.selectOne("Opening.getScrapCount", user_id);
	}
	//스크랩 삭제
	public void deleteScrap(String user_id, int opn_num) {
		HashMap<String, Object> setter = new HashMap<>();
		setter.put("user_id", user_id);
		setter.put("opn_num", opn_num);
		sql_session.delete("Opening.deleteScrap", setter);
	}

}
