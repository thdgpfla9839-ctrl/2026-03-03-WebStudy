package com.sist.dao;
import java.util.*;
/*
 * resultType="int" =====> 리턴형
 * parame
 * 
 * ===========================================
 * 
 * 데이터 읽기 : 
 * 
 * 커밋을 날리려면 괄호 안에 true라고 적는다
 * 
 */
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.SeoulVO;

import java.io.*;
public class SeoulDAO {

	private static SqlSessionFactory ssf;
	static
	{
		try
		{
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// 목록출력
	public static List<SeoulVO> seoulListData(Map map)
	{
		SqlSession session = ssf.openSession();
		List<SeoulVO> list=session.selectList("seoulListData",map);
		session.close();
		return list;
	}
	
	// 페이지 나누기
	public static int seoulTotalPage(Map map)
	{
		SqlSession session = ssf.openSession();
		int total=session.selectOne("seoulTotalPage",map);
		session.close();
		return total;
	}
	
	// 상세보기
	public static SeoulVO seoulDetailData(Map map)
	{
		SqlSession session =ssf.openSession(); // 혹은 여기에 true를 주면 문장 수행 후 커밋이 된다
		String t=(String)map.get("table"); //호텔에 hit가 없어서 상세보기가 안 불러와져 그래서 추가하는듯
		if(!t.endsWith("hotel"))
		      session.update("hitIncrement",map);
		session.commit(); // 이거 안 주는 대신에, 내가 원하는 위치에 커밋 주는 형식이 트랜젝션(일괄처리) 
		// 이렇게 일괄처리 해주면 catch절에 session.rollback()이 된다
		SeoulVO vo =session.selectOne("seoulDetailData",map); // vo 한개만 가져오니까 selectOne을 썼다 => 한줄 읽기인 row 단위로 출력될듯
		session.close();
		return vo; // 이제 모델로 처리된 내용 보내주러 가야지
	}
}
