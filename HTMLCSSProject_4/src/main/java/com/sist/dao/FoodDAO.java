package com.sist.dao;

import java.util.*;
import java.sql.*;

/*
 *  같은 소스가 계속 반복됨 -> 메소드를 이용해라
 *  같은 소스가 여러개 클래스에서 반복이 되면 -> 클래스에서 모아서 사용 => 공통모듈 : 스트링에서 AOP(자동호출)
 */
public class FoodDAO {
	// 공통으로 사용되는 객체
	private Connection conn; // 오라클 연결
	private PreparedStatement ps; // 오라클 통신 (SQL => 저장 데이터 읽기)
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";

	// 1. 드라이버 등록
	public FoodDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 2. 오라클 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception ex) {
		}
	}

	// 3. 오라클 해제
	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
		}
	}
	/*
	 * FoodVO는 맛집 한개에 대한 정보임 이걸 12개를 담아서 브라우저로 전송할 때
	 */

	// 4. 기능
	// 4-1. 목록 -> 페이지 나눔 -> 한 파일에 12개 출력 -> 사용자가 맛집 12개를 보려라
	public List<FoodVO> foodListData(int page) {
		// 매개변수는 사용자가 보내준다
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql = "SELECT no,poster,name " 
			            + "FROM food " 
					    + "ORDER BY no ASC "
					    + "OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
			ps = conn.prepareStatement(sql);
		// 	4. ?가 있는 경우 값을 먼저 채워준다
			ps.setInt(1, (page*12)-12);
			ResultSet rs = ps.executeQuery();
			// 출력물 가장 위에 커서를 이동시킨다 -> next()이용
			while(rs.next())
			{
				FoodVO vo = new FoodVO();
						vo.setNo(rs.getInt(1));
						vo.setPoster(rs.getString(2));
						vo.setName(rs.getNString(3));
						
						list.add(vo);
			}
			rs.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return list;

	}
	// 총페이지
	public int foodTotalPage()
	{
		int total =0;
		try
		{
			getConnection();
			String sql = "SELECT CEIL(count(*)/12.0) FROM food";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); // ? 없으니 바로 실행
			// 데이터가 출력된 위치로 커서 이동시키기
			rs.next();
			total = rs.getInt(1);
			rs.close(); // 이제 리스트 목록 출력하려 리스트.jsp이동
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			disConnection();
		}
		return total;
	}
	// 4-2. 상세보기
	/*
	 * private int no;
	   private double score;
	   private String name,type,phone,address,parking,poster,time,content,theme,price;
	 */
	public FoodVO foodDetailData(int no) // <a href="detal.jsp?no=<%=vo.getNo()%>"> 이 부분의 괄호 값이 여기 int no에 들어가야한다
	{
		FoodVO vo = new FoodVO();
		try
		{
			getConnection();
			String sql ="SELECT no,name,poster,score,"
					    +"type,phone,address,parking,time,"
					    +"content,theme,price "
					    +"FROM food "
					    +"WHERE no=?"; // no는 중복이 없는 pk라서 데이터는 딱 하나만 갖고 온다
			
			   ps = conn.prepareStatement(sql);
			
				ps.setInt(1,no);
				ResultSet rs = ps.executeQuery();
				rs.next();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setScore(rs.getDouble(4));
				vo.setType(rs.getString(5));
				vo.setPhone(rs.getString(6));
				vo.setAddress(rs.getString(7));
				vo.setParking(rs.getString(8));
				vo.setTime(rs.getString(9));
				vo.setContent(rs.getString(10));
				vo.setTheme(rs.getString(11));
				vo.setPrice(rs.getString(12));
				
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo;
	}
}