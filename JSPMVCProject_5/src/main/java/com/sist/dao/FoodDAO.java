package com.sist.dao;
/*
 * MyBatis를 하려면 아래 4가지가 기본으로 필요하다
 * => 
 *   1. Config.xml 만들기 => Connection 부분
 *   2. mapper.xml 만들기 => SQL문장이 들어가고 preparestatement / resultset
 *   3. VO  => 한 개의 정보를 저장하는 장소
 *   4. DAO => xml + 자바 연결
 * 
 */
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;
public class FoodDAO {

	// xml의 데이터를 저장하는 클래스
	private static SqlSessionFactory ssf;
	
	// xml 파싱
	static // 초기화 블록 => 자동 호출이 되고, 상속예외
	{
		try
		{
			// xml 읽기 => 한번에 모든 xml을 포함하고 있는 xml이 존재 => Config.xml
			Reader reader = Resources.getResourceAsReader("Config.xml");
			// 파싱된 내용 읽기 Config.xml에서 <property ~ 부분
			ssf = new SqlSessionFactoryBuilder().build(reader);
					
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	// 목록 불러오기
	public static List<FoodVO> foodListData(int start)
	{
		/*
		 * food-mapper 파일 속 문장을 실행하려함
		 */
		List<FoodVO> list = new ArrayList<FoodVO>();
		// 원래는 여기는 예외처리 하지 않아도 되는 클래스야
		// 근데 나는 아직 에러 잡지 못하니까 예외처리 해주고 가자
		
		SqlSession session = null;
		try
		{
			// session 생성 => preparestatement 만드는 거야
			session =ssf.openSession();
			
			// 값 받아오기
			list = session.selectList("foodListData",start); // id는 대소문자 구분한다
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close(); // 재사용할 수 있게 반환 (우리는 커넥션풀을 기반으로 사용중)
		}
		return list;
	}
	// 총페이지 구하기
	
	/*total = session.selectOne("foodTotalPage"); 를 부르면
	 * 실행되는 문장
	 * 
	 * =>
	 * 
	 */
	public static int foodTotalPage()
	{
		int total = 0;
		SqlSession session = null;
		try
		{
			// session 생성 => preparestatement 만드는 거야
			session =ssf.openSession();
			
			// 값 받아오기
			total = session.selectOne("foodTotalPage"); // id는 대소문자 구분한다
		}
		catch (Exception ex) 
		{
			ex.printStackTrace(); 
		}
		finally
		{
			if(session!=null)
				session.close(); // 재사용할 수 있게 반환 (우리는 커넥션풀을 기반으로 사용중)
		}
		return total; 
		// 이제 받은 데이터를 브라우저에 보내러 가자 MODEL - 푸드모델 클래스로
	} 
}
