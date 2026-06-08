package com.sist.dao;
import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/* 
 * MyBatis의 CRUD
 * 
 *  <select> <update> <insert> <delete>
 *                                  | delete()
 *                         | insert()
 *                | update()
 *         | = List selectList()
 *         | = VO   selectOne()
 *     = JOIN / SubQuery() => <resultMap>
 *     = 동적 쿼리 
 *       <if> <forEach> <where> <choose> <trim> : 다중 검색 
 *     = Annotation 사용 
 *     = MyBatis의 단점 
 *       SQL문장을 한개만 처리 
 *       => 여러개 처리 
 *          <select> : Procedure형식 => 회원 탈퇴 
 *           BEGIN 
 *           {
 *           }
 *           END
 *          </select>
 */

public class EmpDAO {
  private static SqlSessionFactory ssf;
  static
  {
	  try
	  {
		  // xml 파싱하는 부분
		  Reader reader = Resources.getResourceAsReader("Config.xml");
		  
		  // mapper.xml이 포함
		  ssf = new SqlSessionFactoryBuilder().build(reader);
	  }
	  catch (Exception ex)
	  {
		  ex.printStackTrace();
	  }
	 
  }
  
       //기능
  
  public static List<EmpVO> empListData()
  {
	  List<EmpVO> list = new ArrayList<EmpVO>();
	  
	  // 연결 => 커넥션 주소 읽기
	  SqlSession session = null;
	  try
	  {
		  session = ssf.openSession();
		  list=session.selectList("empListData");
	  }
	  catch (Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally 
	  {
		if(session!=null)
			session.close(); // POOL로 반환
	  } return list;
	  // jsp로 값을 받아올거야 모델패키지로 가자
  }
}
