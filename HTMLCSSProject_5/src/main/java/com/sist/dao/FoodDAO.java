package com.sist.dao;

/*
 * 오늘의 목표: LIKE를 이용해서 검색하기
 *           SELECT + 페이지
 *         =>  1. 실제 검색하기 2. 버튼 이용하기
 *         
 *         프로젝트를 들어가기 전(벤치마킹도 중요하다 -> 전체적으로는 모방, 없는 기능은 추가하는 것이 새로운 사이트를 만드는 팁)
 *         1. 요구사항 분석하기 : 어떤 데이터가 필요한지(데이터베이스 설계), 어떤 화면이 필요한지(화면 UI 잡기)
 *         2. 데이터베이스 설계
 *         3. 화면 UI 잡기
 *         4. 구현
 *         5. 어느 부분이 빠졌는지, 분석된 내용 수정하는 테스트
 *         6. 최종 배포
 *  --------------------------------------------------------------------------
 *  DAO 작성하는 방향)
 *   1. 드라이버 등록 => 한번 수행 (생성자) Class.forName(Driver)
 *   2. 오라클 연결  => Connection conn=
 *                    DriverManager.getConnection(URL,user,pwd)
 *   3. SQL문장 생성 => String sql=""; => 오라클 문법 
 *   4. SQL문장 전송 => PreparedStatement ps=conn.preparedStatement(sql)
 *   5. 결과값 받기  => ResultSet rs=ps.executeQuery()
 *   6. List / VO에 값을 담는다 
 *      while(rs.next())
 *    {
 *       FoodVO vo=new FoodVO();
 *       vo.setName(...)
 *                               
 *       list.add(vo)
 *     }
 *    7. 닫기 : rs.close() 
 *             ps.close() 
 *             conn.close()
 * ------------------------------------------------------------------------
 * 에러 발생 팁) 
*            1. 소스를 보는 것이 아니다
*                --> output 창 => 에러 확인 ex.printStackTrace();
*            2. Driver 에러 
*            3. SQL문장 
*   ------------------> 순서로 디버깅             
 *  
 *         
 */
import java.util.*; // ArrayList , List를 이용하기 위해서 사용함
import java.sql.*; // Connection / PreparedStatement / ResultSet을 사용하려고 

public class FoodDAO {

	// 실무에서 공통으로 사용되는 클래스나 변수를 모아서 저장 => 보안이 필요해 그래서 private
	private Connection conn; // 오라클 연결
	private PreparedStatement ps; // 오라클 통신 (sql에 저장된 데이터 읽기)
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // 학원 오라클 연결 포트번호:1521

	// 1. 드라이버 등록(=> 이제 오라클이랑 연결할 거니까 이 통역 프로그램을 사용해 / 한번만 등록함 )

	public FoodDAO() 
	{
		try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
		    } 
		     catch (Exception ex) 
		     {
			    ex.printStackTrace();
		     }

	  }

	// 연결 해제하는 부분도 공통사용, 매번 반복사용하니 메소드화 시켜서 사용하자
	// 2. 오라클 연결 (url,username,password가 노출될 수 있음 그래서 우리는 getConnection()을 사용했지만
	// 실무에서는 ConnectionPool을 사용하더라)

	public void getconnection() 
	{
		try 
		{
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} 
		 catch (Exception ex) {}
	}

	// 3. 오라클 해제
	public void disConnection() 
	{
		try 
		   {
			  if (ps != null) // 열려있으면 ps.close 해줘라
				  ps.close();
			  if (conn != null)
				  conn.close();

		   } catch (Exception ex) {}
	}
	// 여기까지(디비연결과정)는 매번 반복되는 코드

	// 4. 기능
	// 4-1. 한식 / 중식... 이런식으로 분리하기
	public List<FoodVO> foodListData(int page, String type) // 데이터가 많아서 하나로 모아서 한번에 전송하는 list 사용
															// 출력되는 목록이 너무 많아서 페이지를 줄거야
	{
		List<FoodVO> list = new ArrayList<FoodVO>();

		try {
			  //  연결 
			  getconnection();
			  
			  // sql문장 => 오라클
			  String sql = "SELECT no,poster,name " + "FROM food " + "WHERE type LIKE '%'||?||'%' " + "ORDER BY no ASC "
					     + "OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";

			  // 미리 오라클로 sql문장을 전송
			ps = conn.prepareStatement(sql); // 미리 문장을 보내고 나중에 sql문장에 있는 ? 값을 채운다 => prepareStatement
			ps.setString(1, type);
			ps.setInt(2, (page * 12) - 12); // 페이지를 나눠주는 과정 / OFFSET의 시작하는 번호는 0번부터

			//실행 요청 => 결과값을 가지고 와라 => 저장하는 메모리 공간 ResultSet
			ResultSet rs = ps.executeQuery();

			 //6. 메모리에 출력된 첫번째 데이터 위치에 커서이동 => next()
			while (rs.next()) 
			{
				FoodVO vo = new FoodVO();
				
				// 오라클 데이터는 무조건 String으로 받을 수 있다
				vo.setNo(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setName(rs.getString(3));
				
				// 실수 => rs.getDouble() // rs.getDate()

				list.add(vo);
			}

			
		} 
		catch (Exception ex) 
		{
		  ex.printStackTrace(); // 에러가 나면 에러위치나 에러 복구할 수 있께 확인하게 해주는 메소드
		} 
		 finally 
		 {
			 // 닫기 => 실헹문장이 정상이거나 에러가 발생하더라도 무조건 수행하게 함
			 disConnection();
		}
		return list; // 여기에 값을 채운다

	}

	// 4-2. 페이지를 나눴으니 총페이지를 구해야겠지
	public int foodTotalPage(String type)
	{
		int total = 0;

		try 
		{
			getconnection();
			// sql 스킬 => 튜닝과 람다식
			String sql = "SELECT CEIL(COUNT(*)/12.0) " // 이렇게 따로 조건을 줘야
			             + "FROM food "                // 한식, 중식... 이렇게
					     + "WHERE type LIKE '%'||?||'%'"; // 나눠줘
																										

			ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close(); // 이제 list.jsp 채우러 간다
			            // ResultSet db연결자원이라 사용이 끝나면 닫아줘야함
		} 
		 catch (Exception ex) 
		{
			ex.printStackTrace();
		} 
		 finally 
		 {
			disConnection();
		  }

		   return total;
	}

	// 4-3. 검색
	public List<FoodVO> foodFindData(String column, String fd) {
		List<FoodVO> list = new ArrayList<FoodVO>();
		try 
		{
			getconnection();
			String sql = "SELECT no,poster,name " 
			+ "FROM food " 
			+ "WHERE " + column + " LIKE '%'||?||'%'";
			// where 다음에는 ?를 쓰지 않고 컬럼명이 들어가야 해
			// 컬럼이나 테이블명이 들어갈 때는 ''가 아닌 문자열 결합을 시킨다

			ps = conn.prepareStatement(sql);
			ps.setString(1, fd);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) 
			{
				FoodVO vo = new FoodVO();
				
				// 오라클 데이터는 무조건 String으로 받을 수 있다 
				vo.setNo(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setName(rs.getString(3));
				// 실수 => rs.getDouble() // rs.getDate()

				list.add(vo);
			}

		} catch (Exception ex) 
		{
			ex.printStackTrace();
		} 
		 finally 
		 {
			disConnection();
		 }

		  return list;
	}
}
