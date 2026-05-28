package com.sist.dao;
import java.util.*; // 목록출력할 때 많이 사용 List객체
import java.sql.*; // Connection , prepareStatement , resultset
import java.sql.*; // DataSource =>데이터베이스 연결에 필요한 정보
import javax.naming.*; // context => pool에 저장된 connection 주소 얻기
import javax.sql.DataSource;
// pool => 메모리 공간(connection 생성 후 저장 공간)
// pool 안에 있는 connection 주소를 대여 => 사용 => pool 안으로 반환 후 재사용
// 이것을 라이브러리화 시킨 것이 마이바티스(마이바티스와 jpa는 커넥션 풀 기반)
public class BoardDAO {
   // 전역변수 잡기
	private Connection conn;
	private PreparedStatement ps;
	private static BoardDAO dao; // 싱글턴 => 메모리 누수현상을 방지하기 위함
	private final int ROW = 10; // 한번에 10개씩 가져온다
	
	
	// 1. pool 안에 있는 connection 대여 => 미리 오라클과 연결된 상태
	public void getConnection()
	{
		try {
			// 1. 탐섹기 형식으로 돼 있기 때문에 탐섹기 열기 => JDNI 초기화
			Context init = new InitialContext();
			// 2. c드라이버로 이동
			Context c = (Context)init.lookup("java://comp/env");
			// 3. 위에 폴더에서 커넥션 정보 찾아오기
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			// 4. 커넥션 대입
			conn=ds.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
	}
	// 2. 사용 후에 pool로 반환
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
		} catch (Exception ex) {
			// TODO: handle exception
		}
	}
	// 3. 메모리 누수현상 방지 => 싱글턴 => static은 공간이 한개만 생성이 가능하다
	public static BoardDAO newInstance()
	{
		if(dao==null)
			dao=new BoardDAO();
		return dao;
	}
	// 4. 기능
	// 4-1. 목록 출력 => 페이지 나누기
	
	
	/*
	 * try {
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			
		}
		finally 
		{
			disConnection();
		}
	 * */
	
	public List<BoardVO> boardListData(int page)
	{
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			// 1. 커넥션 객체 얻어오기
			getConnection();
			// 2. sql문장 생성
			String sql = "SELECT no,subject,name,TO_CHAR(regdate,'yyyy-mm-dd'),hit,group_tab "
					+"FROM jspReplyBoard "
					+"ORDER BY group_id DESC,group_step ASC "
					+"OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			
			// 3. 오라클에 전송
			ps=conn.prepareStatement(sql);
			
			// 4. 실행 전 ?에 값을 채운다
			ps.setInt(1, (page*ROW)-ROW); // 첫번째 ?
			ps.setInt(2, ROW); // 두번쨰 ?
			
			// 5. 실행요청 후 결과값을 받아서 저장
			ResultSet rs = ps.executeQuery();
			
			// 6. 이 값을 리스트에 담는다 => 이 담긴게 브라우저로 전송된다(jsp를 이용)
			while(rs.next()) // 메모리에 출력된 첫번쨰 위치에 커서를 갖다 놓는다
			{
				// sql 6개 데이터 가져온다 한번에
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
				
				list.add(vo);
			}
			rs.close();
		} 
		catch (Exception ex)
         {
			ex.printStackTrace();
			// TODO: handle exception
		}
		finally {
			// 반환
			disConnection();
		}
		return list;
	}
	// 4-1-1. 총페이지 구하기
	public int boardRowCount()
	{
		int count=0;
		
		try {
			getConnection();
			String sql ="SELECT COUNT(*) FROM jspReplyBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			// TODO: handle exception
		}
		finally {
			disConnection();
		}
		return count;
	}
	// 4-2. 게시물 추가
	// 4-3. 상세보기 => 조회수 증가 / 실제 데이터
	// 4-4. 수정하기 
	//////////////////////////////////////////// 일반 게시판 형태
	// 4-5. 답변 올리기 => 아마도 sql문장 4개 나올듯
	// 4-6. 삭제하기   => sql문장 수 만큼 수행
	//////////////////////////////////////////// 트랜젝션 처리(sql문장이 여러개가 만들어질듯) => insert / update / delete가 있을 떄만 트렌젝션 처리를 한다
}
