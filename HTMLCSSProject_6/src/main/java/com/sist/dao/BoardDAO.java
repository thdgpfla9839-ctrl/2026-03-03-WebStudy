package com.sist.dao;
// 인라인뷰, OFFSET 이 부분 면접에서 나올 가능성 있으니 개념 확실하게 잡기

import java.util.*; // collection에서 list를 사용할 예정 데이터를 모아서 저장 후 한번에 전송
import java.sql.*; // 오라클 연동을 하기 위해서 => connection,statement,resultset 사용할 거야

public class BoardDAO {

	// 클래스 전체에서 필요한 객체 / 변수 / 상수를 선언하는 위치
	private Connection conn; // 오라클 연결
	private PreparedStatement ps; // 오라클 통신 (sql에 저장된 데이터 읽기)
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";

	// 1. 드라이버 등록
	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------
	// 1) 사용하는 변수들은 보안이 필요하므로 private을 해준다
	// 2) 클래스나 생성자, 메소드는 다른 클래스와 연결을 해줘야 하므로 public을 해준다
	// --------------------------------------------------------------------------

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
		} catch (Exception e) {
		}
	}

	// --------------------------------------------------------------

	// 기능 설정 (목록 / 상세보기 / 추가 / 수정 / 삭제)
	// 4-1. 목록 => 페이징은 무조건 해야한다 (한눈에 화면을 볼 수 있게 해줘야함)
	// 4-2. 게시물 (게시물 1개 boardVO)
	public List<BoardVO> boardListData(int page) {
		// 사용자가 원하는 페이지 전송 => 해당 페이지를 오라클에서 가져와서 리스트를 이용해서 전송한다
		// 출력에 필요한 내용 : 리턴형
		// 사용자로부터 받는 값 : 매개변수
		List<BoardVO> list = new ArrayList<BoardVO>();

		try {
			getConnection();
			String sql = "SELECT no,subject,name,TO_CHAR(regdate,'yyyy-mm-dd'),hit " + "FROM jspBoard "
					+ "ORDER BY no DESC " + "OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";

			// 강사님 주석처럼 sql문장을 쓸 수도 있음

			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page * 10) - 10); // 항상 0번 부터 시작해서 0부터 10씩 페이지를 자른다고 생각
			ResultSet rs = ps.executeQuery();
			while (rs.next()) // 출력물이 처음부터 한줄 씩 아래로 내려가면서 값을 읽어온다
			{
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));

				list.add(vo);
			}
			rs.close();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}

	// 4-2. 총페이지 구하기
	public int boardRowCount() {
		int count = 0;
		try {
			getConnection();
			String sql = "SELECT COUNT(*) FROM jspBoard";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return count;
	}
	
	// 4-3. 상세보기 => where절 쓰는 연습
	// 4-4. 추가 => insert 자바는 Autocommit을 가지고 있어서 자동으로 커밋이 된다
	public void boardInsert(BoardVO vo)
	{
		try {
			getConnection();
			String sql = "INSERT INTO jspBoard(name,subject,content,pwd) "
					     +"VALUES(?,?,?,?)";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate(); // 변경사항 업데이트되게끔
		

		} 
		 catch (Exception e) 
		
		{
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
	}
}
