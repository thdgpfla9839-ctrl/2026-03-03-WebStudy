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
	//-------------------------------------------------------
	/*
	 * SELECT no,subject,name,TO_CHAR(regdate,'yyyy-mm-dd'),hit,num
    *    FROM (SELECT no,subject,name,regdate,hit,rownum as num
    *    FROM (SELECT no,subject,name,regdate,hit
    *    FROM jspReplyBoard ORDER BY group_id DESC,group_step ASC))
    *    WHERE num BETWEEN ? AND ?
	 */
	//-------------------------------------------------------
	
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
					+"ORDER BY group_id DESC,group_step ASC " // ** 답변끼리 모으고 순서대로 출력할 수 있게 해주는 코드 아주 중요하다, 컬럼을 갖고 조절한다 
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
	 public void boardInsert(BoardVO vo)
	   {
		   try
		   {
			   // Connection 주소값 얻기 
			   getConnection();
			   // SQL문장 
			   String sql="INSERT INTO jspReplyBoard(no,name,subject,content,pwd,group_id) "
					     +"VALUES(jrb_no_seq.nextval,?,?,?,?,"
					     +"(SELECT NVL(MAX(group_id)+1,1) FROM jspReplyBoard))";
			   // JOIN => select만 사용 , subquery => DML전체 사용이 가능 
			   // JOIN => table+table => 필요한 데이터 추출 
			   // SubQuery => SQL+SQL => 한개의 SQL을 만든다 
			   // 오라클 전송 
			   ps=conn.prepareStatement(sql);
			   // ?에 값을 채운다 
			   ps.setString(1, vo.getName());
			   ps.setString(2, vo.getSubject());
			   ps.setString(3, vo.getContent());
			   ps.setString(4, vo.getPwd());
			   // 실행 요청 
			   ps.executeUpdate(); // Commit포함 => insert / update / delete 
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();
		   }
	   }
	// 4-3. 상세보기 => 조회수 증가 / 실제 데이터
	 public BoardVO baordDetail(int no)
	   {
		   BoardVO vo=new BoardVO();
		   try
		   {
			   getConnection();
			   String sql="UPDATE jspReplyBoard SET "
					     +"hit=hit+1 "
					     +"WHERE no=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, no);
			   ps.executeUpdate();
			   
			   // 실제 데이터 읽기 
			   sql="SELECT no,name,subject,content,hit,"
				  +"TO_CHAR(regdate,'yyyy-MM-dd hh24:mi:ss') "
				  +"FROM jspReplyBoard "
				  +"WHERE no=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, no);
			   
			   // 결과값 
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   vo.setNo(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setSubject(rs.getString(3));
			   vo.setContent(rs.getString(4));
			   vo.setHit(rs.getInt(5));
			   vo.setDbday(rs.getString(6));
			   rs.close();
			   // Model => 요청 처리하는 메소드 => 해당 데이터 전송 : request/session
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();
		   }
		   return vo;
	   }
	// 4-4. 수정하기 
	 public BoardVO baordUpdateData(int no)
	   {
		   BoardVO vo=new BoardVO();
		   try
		   {
			   getConnection();
			   String sql="SELECT no,name,subject,content "
				  +"FROM jspReplyBoard "
				  +"WHERE no=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, no);
			   
			   // 결과값 
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   vo.setNo(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setSubject(rs.getString(3));
			   vo.setContent(rs.getString(4));
			  
			   rs.close();
			   // Model => 요청 처리하는 메소드 => 해당 데이터 전송 : request/session
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();
		   }
		   return vo;
	   }
	   
	   public boolean boardUpdate(BoardVO vo)
	   {
		   boolean bCheck=false;
		   try
		   {
			   getConnection();
			   String sql="SELECT pwd FROM jspReplyBoard "
					     +"WHERE no=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, vo.getNo());
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   String db_pwd=rs.getString(1);
			   rs.close();
			   
			   // 본인 여부 확인 
			   if(db_pwd.equals(vo.getPwd()))
			   {
				   bCheck=true;
				   
				   // 수정
				   sql="UPDATE jspReplyBoard SET "
					  +"name=?,subject=?,content=? "
					  +"WHERE no=?";
				   ps=conn.prepareStatement(sql);
				   ps.setString(1, vo.getName());
				   ps.setString(2, vo.getSubject());
				   ps.setString(3, vo.getContent());
				   ps.setInt(4, vo.getNo());
				   ps.executeUpdate();
			   }
			   
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();//반환
		   }
		   return bCheck;
	   }
	//////////////////////////////////////////// 일반 게시판 형태
	// 4-5. 답변 올리기 => 아마도 sql문장 4개 나올듯
	   // 일괄처리 => 동시 Commit / 동시 Rollback
	   // 비절차언어 => 에러 무시 => 다음 문장을 수행 => 한번 처리 => 트랜젝션 
	   // 위치 지정 savepoint
	   public void boardreply(int pno,BoardVO vo)
	   {
		   try
		   {
			   getConnection();
			   conn.setAutoCommit(false);
			   // 답변 대상의 정보 읽기 
			   String sql="SELECT group_id,group_step,group_tab "
					     +"FROM jspReplyBoard "
					     +"WHERE no=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, pno);
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   int gi=rs.getInt(1);
			   int gs=rs.getInt(2);
			   int gt=rs.getInt(3);
			   rs.close();
			   /*              gi  gs  gt
			    *    AAAAA      1   0   0
			    *     =>EEEEE   1   1   1
			    *     =>BBBBB   1   2   1
			    *      =>CCCCC  1   3   2
			    *       =>DDDDD 1   4   3
			    *     
			    *     
			    */
			   // *** 답변 => 핵심 (새 답변의 위치=> 최신 답변을 가장 위로 위치하게 함) => 답변형 게시판 만들 떄 가장 핵심이 되는 코드
			   sql="UPDATE jspReplyBoard SET "
				  +"group_step=group_step+1 "
				  +"WHERE group_id=? AND group_step>?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, gi);
			   ps.setInt(2, gs);
			   ps.executeUpdate();
			   
			   // insert => 답변을 추가하는 과정
			   sql="INSERT INTO jspReplyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) "
				  +"VALUES(jrb_no_seq.nextval,?,?,?,?,?,?,?,?)";
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, vo.getName());
			   ps.setString(2, vo.getSubject());
			   ps.setString(3, vo.getContent());
			   ps.setString(4, vo.getPwd());
			   ps.setInt(5, gi);
			   ps.setInt(6, gs+1);
			   ps.setInt(7, gt+1);
			   ps.setInt(8, pno);
			   ps.executeUpdate();
			   // update => 답변의 개수 체크 => depth(댓글의 개수) 증가
			   sql="UPDATE jspReplyBoard SET "
					   +"depth=depth+1 "
					   +"WHERE no=?";
			   ps =conn.prepareStatement(sql);
			   ps.setInt(1, pno);
			   ps.executeUpdate();
			   conn.commit(); //모든 명령을 정상 수행 => 일괄처리
			   
		// => sql문장이 한개로 수행하는 것이 아니라 한개 수행되거나 여러개의 sql문장이 수행될 수 있다
	    /*
	     *  여러개의 sql문장이 나오먄 순서확인이 가장 중요하다
	     *  1. 상위 게시물의 답변 정보를 가져온다(답변정보 : group_id :같은 답변끼리 모아둔것, group_step: 답변 안에 출력 순서를 지정, group_tab: 답변들의 간격)
	     *     => root : 상위 게시물
	     *     => depth : 답변 개수 
	     *     => 위 두가지는 삭제 시 사용한다
	     *     => 비밀번호가 일치해야 삭제 가능
	     *     => depth가 0일 경우에만 삭제 가능
	     *     => depth가 0이 아니면 제목 / 내용이 변경된다 => 관리자가 삭제한 게시물에 해당됨 => 이 명령어는 관리자가 관리한다 => 비활성화
	     *        
	     *  		   
	     */
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
			   try
			   {
				   conn.rollback(); // 모든 명령을 취소
			   }catch(Exception e) {}
		   }
		   finally
		   {
			   try
			   {
				   conn.setAutoCommit(true);
			   }catch(Exception ex) {}
			   disConnection();
		   }
	   }
	// 4-6. 삭제하기   => sql문장 수 만큼 수행
	//////////////////////////////////////////// 트랜젝션 처리(sql문장이 여러개가 만들어질듯) => insert / update / delete가 있을 떄만 트렌젝션 처리를 한다
 public boolean boardDelete(int no, String pwd)
 {
	 // 코드 순서
	 // 1. 비밀번호 검색
	 // 2. 삭제할 수 있는 게시물인지 확인 => depth가 0이냐/ 답변이 없냐
	 // 2-1. depth가 0일 때 => delete
	 // 2-2. depth가 0이 아닐 때 => update(제목 / 내용 변경) => 게시물 유지
	 
	 
	 boolean bCheck=false; // history.back() => delete.jsp
	 try {
		 getConnection();
		 conn.setAutoCommit(false); // 커밋해제
		 String sql="SELECT pwd,root,depth "
				 +"FROM jspReplyBoard "
				 +"WHERE no=?";
		 
		 ps=conn.prepareStatement(sql);
		 
		 ps.setInt(1, no);
		 
		 ResultSet rs = ps.executeQuery();
		 rs.next();
		 String db_pwd = rs.getString(1);
		 int root = rs.getInt(2);
		 int depth = rs.getInt(3);
		 rs.close();
		 
		 // 비밀번호 체크
		 if(db_pwd.equals(pwd))
		 {
			 bCheck=true; // 이동 => list.jsp
			 if(depth==0) // 게시물 안에 답변이 안 달린 경우
			 {
				 sql = "DELETE FROM jspReplyBoard "
						 +"WHERE no=?";
				 ps= conn.prepareStatement(sql);
				 ps.setInt(1, no);
				 ps.executeUpdate(); // 오토커밋이 걸림, 커밋이 날라가면 롤백이 불가능함
				 
			 }
			 else  // 게시물에 답변이 달린 경우
			 {
				String msg="관리자가 삭제한 게시물입니다";
				// 제목과 내용 관리자가 ~ 로 바꾸려고 함
				sql="UPDATE jspReplyBoard SET "
						+"subject=? , content=? "
						+"WHERE no=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, msg);
				ps.setString(2, msg);
				ps.setInt(3, no);
				
				ps.executeUpdate();
			 }
			 
			 sql ="UPDATE jspReplyBoard SET "
					 +"depth=depth-1 "
					 +"WHERE no=?";
			 
			 ps=conn.prepareStatement(sql);
			 ps.setInt(1, root);
			 ps.executeUpdate();
		 }
		 conn.commit();
		
	} catch (Exception ex) {
		ex.printStackTrace();
		// 에러가 발생하면 sql문장 전체 취소해라
		
		try 
		    {
			conn.rollback();
		    } 
		     catch (Exception e) {}
		
	}finally {
		// 사용 후에 pool에 반황 => pool은 커넥션 저장공간
		// 오토커밋 원상복귀
		try 
		   {
			conn.setAutoCommit(true);
		   } 
		catch (Exception ex) {}
		
		disConnection();
	}
	 return bCheck;
 }
}
