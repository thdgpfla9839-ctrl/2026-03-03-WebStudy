package com.sist.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

/*
 *<select id="foodListData" resultType="FoodVO" parameterType="int">
  SELECT no,poster,name
  FROM food
  ORDER BY no ASC
  OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
  </select>
 */
/*
 * ajax는 값 자체를 json으로 바꿔서 전송 / jsp는 request에 값을 담아서 전송한다
 */
public class FoodDAO {

	private static SqlSessionFactory ssf;
	static
	{
		try
		{
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<FoodVO> foodListData(int start)
	{
		// 예외처리 안 하면 에러 찾기가 어렵다 => 프로젝트 만들 떄는 예외처리 하면서 만들자
		SqlSession session =ssf.openSession();
		List<FoodVO> list = session.selectList("foodListData",start);
		session.close();
		return list; // 모델로 가서 데이터 보내보자 
		
	}
	
	public static int foodTotalPage()
	{
		SqlSession session =ssf.openSession();
		int total = session.selectOne("foodTotalPage");
		session.close();
		return total;
	}
	
	public static FoodVO foodDetailData(int no)
	{
		SqlSession session =ssf.openSession();
		FoodVO vo = session.selectOne("foodDetailData",no);
		session.close();
		return vo;
	} // 모델에서 여기 데이터 보내기
}
