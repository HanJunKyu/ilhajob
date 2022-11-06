package com.ilhajob.app.bbs.dao;

import java.util.HashMap;



import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ilhajob.app.bbs.vo.BbsReplyVO;
import com.ilhajob.app.bbs.vo.BbsVO;
import com.ilhajob.mybatis.config.SqlMapConfig;
import com.ilhajob.app.bbs.vo.BbsReplyVO;

public class BbsDAO {
	
	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public BbsDAO() {
		sqlsession = sessionf.openSession(true);
	}
	
	//페이지 별 게시글 목록 
	public List<BbsVO> getBbsList(String bbs_category, int startRow, int endRow) {
		HashMap<String, Object> pageMap = new HashMap<>();
		
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("bbs_category", bbs_category);
		
		System.out.println(pageMap);
		
		return bbs_category == null ? sqlsession.selectList("Bbs.listAll", pageMap) :
			sqlsession.selectList("Bbs.getBbsRadioList", bbs_category);
	}
	
	//***게시글 번호로 해당 게시글 가져오기
		public BbsVO getDetail(int bbs_num) {
			return sqlsession.selectOne("Bbs.getDetail", bbs_num);
	}
	//게시글 전체 개수
	public int getBbsCnt() {
		return sqlsession.selectOne("Bbs.getBbsCnt");
	}
	
	//조회수
	public void updateBbsReadCount(int bbs_num) {
		sqlsession.update("Bbs.updateBbsReadCount", bbs_num);
	}
	
	//게시글 번호
	public int getBbsSeq() {
		return sqlsession.selectOne("Bbs.getSeq");
	}
	
	
	//게시글 추가
	public boolean insertBbs(BbsVO bbs) {
		return sqlsession.insert("Bbs.insertBbs", bbs) == 1;
	}
	//게시글 삭제
	public void deleteBbs(int bbs_num) {
		sqlsession.delete("Bbs.deleteBbs", bbs_num);
	}
		
	//게시판 수정(게시글 번호(기존), 게시글 제목(수정), 게시글 내용(수정))
	public void updateBbs(BbsVO b_vo) {
		sqlsession.update("Bbs.updateBbs", b_vo);
	}
/* 댓글 */
	
	//댓글 전체 목록
	public List<BbsReplyVO> getBbsReplyList(int bbs_num) {
		return sqlsession.selectList("Bbs.getBbsReplyList", bbs_num);
	}
	
	//댓글 추가
	public boolean insertBbsReply(BbsReplyVO r_vo) {
		return sqlsession.insert("Bbs.insertBbsReply", r_vo) == 1;
	}
	
	//댓글 삭제
	public void deleteBbsReply(int bbs_reply_num) {
		sqlsession.delete("Bbs.deleteBbsReply", bbs_reply_num);
	}
	
	//댓글 수정
	public void updateBbsReply(BbsReplyVO r_vo) {
		sqlsession.update("Bbs.updateBbsReply", r_vo);
	}
	
	//댓글 삭제
	public void deleteBbsReplyAll(int bbs_num) {
		sqlsession.delete("Bbs.deleteBbsReplyAll", bbs_num);
	}
}
















