package com.ilhajob.mybatis.config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	private static SqlSessionFactory sqlsession_f;
	
	//static 초기화 블럭
	//프로그램 실행 시 단 한번만 실행되는 영역
	static {
		try {
			String resource = "./com/ilhajob/mybatis/config/config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlsession_f = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			
		} catch (IOException e) {
			System.out.println(e);
			throw new RuntimeException("초기화 문제 발생, SqlMapConfig.java");
		}
	}
	
	public static SqlSessionFactory getSqlMapInstance() {
		return sqlsession_f;
	}
	
}

















