package com.ilhajob.app.reviews.dao;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ilhajob.app.bbs.vo.BbsVO;
import com.ilhajob.app.reviews.vo.ReviewVO;
import com.ilhajob.mybatis.config.SqlMapConfig;

public class ReviewDAO {
	
	private static final int KEY = 3;

	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession sql_session;
	
	public ReviewDAO() {
		// 세션 팩토리로 세션을 열어주고 모든 쿼리문은 auto 커밋으로 설정
		sql_session = sessionf.openSession(true);
	}
	
	public boolean insertReview(ReviewVO review) {
		return sql_session.insert("Review.insertReview", review) == 1;
	}

	public List<ReviewVO> getReview(int startRow, int endRow) {
		HashMap<String, Integer> pageMap = new HashMap<>();
		
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		
		return sql_session.selectList("Review.getReview", pageMap);
	}
	
	public List<ReviewVO> getReviewSearch(int startRow, int endRow, String review_career, String review_job, String search) {
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("review_career", review_career);
		map.put("review_job", review_job);
		map.put("search", search);
		
		return sql_session.selectList("Review.getReviewSearch", map);
	}
	
	public int getReviewCnt() {
		return sql_session.selectOne("Review.getReviewCnt");
		
	}
	public int getReviewSearchCnt(String review_career, String review_job, String search) {
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("review_career", review_career);
		map.put("review_job", review_job);
		map.put("search", search);
		
		return sql_session.selectOne("Review.getReviewSearchCnt", map);
	}
}
