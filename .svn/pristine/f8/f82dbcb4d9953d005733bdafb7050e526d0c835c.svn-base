package com.ilhajob.app.bbs.dao;

import java.util.Enumeration;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ilhajob.app.bbs.vo.FilesVO;
import com.ilhajob.mybatis.config.SqlMapConfig;
import com.oreilly.servlet.MultipartRequest;

public class FilesDAO {
	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public FilesDAO() {
		sqlsession = sessionf.openSession(true);
	}
	
	public List<FilesVO> getDeatail(int bbs_num){
		return sqlsession.selectList("Files.getDetail", bbs_num);
	}
	
	public boolean insertFiles(int bbs_num, MultipartRequest multi) {
		Enumeration<String> files = multi.getFileNames();
		boolean check = true;
		
		FilesVO file = new FilesVO();
		file.setBbs_num(bbs_num);
		
		while(files.hasMoreElements()) {
			//사용자가 업로드한 파일명
			String data = files.nextElement();
			//사용자가 업로드한 파일명을 통해서 중복이 없는 시스템파일명을 가져온다.
			String systemName = multi.getFilesystemName(data);
			
			//슬롯 별로 검사하여 null이 아닐 때에만 DB에 INSERT한다.
			if(systemName == null) {continue;}
			file.setFile_bbs_name(systemName);

			if(sqlsession.insert("Files.insertFile", file) != 1) {
				//추가 실패 시 check에 false 대입.
				check = false;
				break;
			}
		}
		return check;
	}
	//첨부파일 삭제
		public void deleteFiles(int bbs_num) {
			sqlsession.delete("Files.deleteFiles", bbs_num);
		}
}



















