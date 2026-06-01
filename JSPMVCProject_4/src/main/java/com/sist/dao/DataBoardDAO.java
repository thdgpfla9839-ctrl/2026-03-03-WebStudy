package com.sist.dao;

/*
 * 데이터베이스 연동 - jdbc연결
 * 
 * 근데 jdbc -> dbcp -> 이번에는 orm을 사용할 거야
 * 
 * ORM :데이터베이스를 처리하는 라이브러리 집합
 *      mybatis / jpa를 이용
 * ------------------------------------
 * xml 이용하는 방식 : jsp 사이트
 * annotaion :spring-boot
 *----------------------------------------
 *
 * 1) 마이바티스(3버전) - Ibatis(2버전) : 오픈소스 그룹
 *                                   구글에서 인수 후 마이바티스로 바뀜
 *                                   
 *--------------------------------------------------------------------------
 * MyBatis : sql을 작성 후 마이바티스로 전송
 *           자동으로 처리 (연결 / 전송 / 실행 / 결과값 담기)
 *                                              
 * 설정파일 => xml ) sql 문장
 *                 vo 설정
 *                 ?에 값 채우기
 *                 => 이러한 반복적인 구조를 없애고 자동으로 해준다
 *--------------------------------------------------------------------------
 * 동작구조)
 * 
 * 1. Config.xml => 설정할 때 Connection과 관련 => 한개만 설정
 * 2. mapper.xml => preparestatement / resultset => 테이블당 1개 설정
 * 
 * => 이렇게 두가지가 있다
 * 
 * 1. Config.xml을 이용해서 getConnection() / disConnection() 두가지를 만든다
 * 2. mapper를 읽어서 JSBC를 이용해서 처리한다
 *     
 *----------------------------------------------------------------------------
 * 장점)
 * 
 * 1. sql 문장 직접 제어 => 개발자가하는 부분
 *    성능 최적화 => 튜닝
 *    복잡한 sql 문장 제어
 * 
  ----------------------------------------------------------------------------
 * 단점)
 * 
 * 1. sql 문장을 직접 제어 => jpa에서는 sql문장을 자동화
 *    db 변경 시 수정
 *    객체 지향 ORM이 약하다
 *    
 *----------------------------------------------------------------------------                     
 *    => sql 문장은 동일
 *       동적 쿼리(= 다중 검색 )를 이용해야 한다
 *       #{ } : ? 에 값을 채워준다, 양쪽에 ''를 붙인다
 *       ${ } : '' 없이 처리된다 , table명이나 column명을 입력할 때 이용한다
 *       
 *   => resultType : 결과값 담기 => resultSet 부분
 *   => parameterType : ?에 값이 들어간다
 *                      => ?가 여러개인 경우 VO를 이용한다
 *                      => vo가 없는 변수가 여러개인 경우에는 Map을 이용한다    
 *                      
 * --------------------------------------------------------------------------
 * SqlSessionFactory : sqlSession을 생성해주고 XML 파싱해주는 역할을 함
 * 
 * SqlSession : preparestatement / resultset 담당                     
 */
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.DataBoardVO;

import java.io.*;

public class DataBoardDAO {

	// 1. xml 파싱하기
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			// classpath영역에 파일이 저장됨 => 경로명 없이 파일명만 지정
			// src/main/java => spring에서도 자동인식
			ssf = new SqlSessionFactoryBuilder().build(reader);
			/*
			 * 저장은 자체 내에서 저장됨
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 2. 기능 만들기
	public static List<DataBoardVO> databoardListData(int start) {
		List<DataBoardVO> list = new ArrayList<DataBoardVO>();
		SqlSession session = null; // conn 생성
		try {
			session = ssf.openSession();
			list = session.selectList("databoardListData", start); // sql 문장 대신 databoardListData 이라는 아이디명칭을 넣어준 것
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null)
				session.close(); // 반환 connection
		}
		return list;
	}

	public static int databoardTotalPage() {

		int total = 0;
		SqlSession session = null; // conn 생성
		try {
			session = ssf.openSession();
			total = session.selectOne("databoardTotalPage");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null)
				session.close(); // 반환 connection
		}
		return total;

	}

//	public static int databoardTotalPage2() {
//
//		int total = 0;
//		SqlSession session = ssf.openSession();
//
//		total = session.selectOne("databoardTotalData");
//		session.close();
//
//		return total; 
	 // 에러 잡을 수 있으면 예외처리 안 써도 됨
//
//	}
}
