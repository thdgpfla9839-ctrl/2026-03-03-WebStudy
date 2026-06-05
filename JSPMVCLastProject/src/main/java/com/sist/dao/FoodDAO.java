package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
import com.sist.vo.*;

import oracle.net.ns.SessionAtts;
public class FoodDAO {

	private static SqlSessionFactory ssf;
	static
	{
		ssf = CreateSqlSessionFactory.getSsf(); // dao마다 테이블당 1개씩 create ~ 클래스 파일의 내용을 적어야 하는데 중복되므로 클래스로 빼 놓고 get메소드해서 불러오기로 바꿨어
	}
	
	// 기능
	
	// 1. 목록 출력
	public static List<FoodVO> foodListData(int start)
	{
		// 매개변수(=> parameterType과 매칭)와 리턴형 (=> resultType과 매칭)이 있다
		
		/* 
		 *  주석 추가하기
		 */
		
		List<FoodVO> list = new ArrayList<FoodVO>();
		SqlSession session = null;
		try
		{
			session = ssf.openSession();
			list = session.selectList("foodListData",start);
		}
		catch (Exception ex) 
		{
		    ex.printStackTrace();	
		}
		finally 
		{
			if(session!=null)
				session.close(); // pool 반환 후 재사용
		}
		return list;
		
		/*
		 *  마이바티스의 단점은 오류는 xml에 있기 때믄에 찾기가 어려워
		 *  그래서 지금은 예외처리를 해주면서 연습하자
		 */
	}
	
	public static int foodTotalPage() {
		int total = 0;
		SqlSession session = null;
		try
		{
			session = ssf.openSession();
			total = session.selectOne("foodTotalPage"); // 위에랑 여기만 달라짐
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally 
		{
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	 // detail
	// 원래대로라면 Cookie / 조회수 증가가 먼저인데 가볍게 사이트를 만들기 때문에 생략함
	public static FoodVO foodDetailData(int no)
	{
		FoodVO vo =  new FoodVO();
		SqlSession session = null;
		try
		{
			session =ssf.openSession();
			vo = session.selectOne("foodDetailData",no);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally 
		{
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
}
