package com.sist.commons;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.*;
import java.io.*;
public class CreateSqlSessionFactory {

	private static SqlSessionFactory ssf;
	static
	{
		try
		{
			// xml 읽기
			Reader reader = Resources.getResourceAsReader("Config.xml");
			
			// 필요한 데이터만 추출 => 파싱
			ssf = new SqlSessionFactoryBuilder().build(reader); // 알아서 Map에 저장 앞은 id 뒤는 sql문장
			
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	// 세터는 필요없고 게터만 소스 - 게터세터에서 불러옴
	public static SqlSessionFactory getSsf() {
		return ssf;
	}
	
}
