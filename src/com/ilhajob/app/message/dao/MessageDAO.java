package com.ilhajob.app.message.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ilhajob.app.message.vo.MessageVO;
import com.ilhajob.mybatis.config.SqlMapConfig;

public class MessageDAO {
	
	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public MessageDAO() {
		sqlsession = sessionf.openSession(true);
	}
	
	//페이지 별 보낸 게시글 목록
	public List<MessageVO> getMessageSendList(String session_id, int startRow, int endRow) {
		HashMap<String, Object> pageMap = new HashMap<>();
				
		pageMap.put("session_id", session_id);
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		
		return sqlsession.selectList("Message.getMessageSendList", pageMap);
	}

	//페이지 별 받은 게시글 목록
	public List<MessageVO> getMessageRcvList(String session_id, int startRow, int endRow) {
		HashMap<String, Object> pageMap = new HashMap<>();
		
		pageMap.put("session_id", session_id);
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		
		return sqlsession.selectList("Message.getMessageRcvList", pageMap);
	}
	
	//보낸게시글 전체 개수
	public int getSendMessageCnt() {
		return sqlsession.selectOne("Message.getSendMessageCnt");
	}
	
	//받은게시글 전체 개수
	public int getRcvMessageCnt() {
		return sqlsession.selectOne("Message.getRcvMessageCnt");
	}
	
	//송신게시글 번호
	public int getSendMessageSeq() {
		return sqlsession.selectOne("Message.getSendMessageSeq");
	}

	//수신게시글 번호
	public int getRcvMessageSeq() {
		return sqlsession.selectOne("Message.getRcvMessageSeq");
	}
		
	//보내기 게시글 추가
	public boolean insertSendMessage(MessageVO message) {
		return sqlsession.insert("Message.insertSendMessage", message) == 1;
	}

	//받은 게시글 추가
	public boolean insertRcvMessage(MessageVO message) {
		return sqlsession.insert("Message.insertRcvMessage", message) == 1;
	}
	
	//보낸게시글 상세보기
	public MessageVO getSendDetail(int message_num) {
		return sqlsession.selectOne("Message.getSendDetail", message_num);
	}

	//받은게시글 상세보기
	public MessageVO getRcvDetail(int message_num) {
		return sqlsession.selectOne("Message.getRcvDetail", message_num);
	}
	
	//보낸게시글 삭제
	public void deleteSendMessage(int message_num) {
		sqlsession.delete("Message.deleteSendMessage", message_num);
	}
	//받은게시글 삭제
	public void deleteRcvMessage(int message_num) {
		sqlsession.delete("Message.deleteRcvMessage", message_num);
	}
}

