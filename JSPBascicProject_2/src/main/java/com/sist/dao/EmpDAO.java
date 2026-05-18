package com.sist.dao;
// 조인이 많이 걸리는 곳은 => 상세보기, 장바구니, 회원관리, 댓글, 예약처리, 구매 부분

import java.util.*;
import java.sql.*;
public class EmpDAO {
	   // 필요한 객체 => 전체적으로 사용 
	   private Connection conn;
	   private PreparedStatement ps;
	   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	   
	   // 드라이버 등록 => 한번만 수행 
	   public EmpDAO()
	   {
		   try
		   {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
		   }catch(Exception ex) 
		   {
			   ex.printStackTrace();
		   }
	   }
	   // 오라클 연동 
	   public void getConnection()
	   {
		   try
		   {
			   conn=DriverManager.getConnection(URL,"hr","happy");
		   }catch(Exception ex) {}
	   }
	   // 오라클 닫기
	   public void disConnection()
	   {
		   try
		   {
			   if(ps!=null) ps.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex) {}
	   }
	   //////////////////////// 공통 기반 => MyBatis / JPA에서는 사용하지 않는다
	   public List<EmpVO> empListData()
	   {
		   List<EmpVO> list=
				   new ArrayList<EmpVO>(); 
		   /*
		    *   EmpVO : 사원 한명에 대한 정보 
		    *   List : 사원 여러명을 저장 
		    */
		   try
		   {
			   getConnection();
			   String sql="SELECT empno,ename,job,TO_CHAR(hiredate,'yyyy-mm-dd'),"
					     +"sal,dname,loc "
					     +"FROM emp JOIN dept "
					     +"ON emp.deptno=dept.deptno "
					     +"ORDER BY sal DESC";
			   // inner join => JOIN ~ ON 
			   // 같은 이름의 컬럼은 반드시 구분한다 : 테이블.컬럼 , 별칭.컬럼 
			   // 전송 
			   ps=conn.prepareStatement(sql);
			   // 결과값 받기 
			   ResultSet rs=ps.executeQuery();
			   while(rs.next())
			   {
				   // 오라클에서 가지고 온 데이터 담기 
				   EmpVO vo=new EmpVO();
				   vo.setEmpno(rs.getInt(1));
				   vo.setEname(rs.getString(2));
				   vo.setJob(rs.getString(3));
				   vo.setDbday(rs.getString(4));
				   vo.setSal(rs.getInt(5));
				   vo.getDvo().setDname(rs.getString(6));
				   vo.getDvo().setLoc(rs.getString(7));
				   list.add(vo);
			   }
			   rs.close();
		   }catch(Exception ex)
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