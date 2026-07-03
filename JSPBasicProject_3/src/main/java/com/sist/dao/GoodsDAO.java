package com.sist.dao;
// => 반드시 싱글턴 사용 => new 
/*
 *    jsp 한개당 => _jspService(request,response)를 따로 가지고 있다 
 *                           ------------------- 톰캣에 의해 값이 채워진다 
 *    => 호출마다 request/response가 달라진다 => 초기화
 */
import java.util.*;
import java.sql.*;
public class GoodsDAO {
   private Connection conn;
   private PreparedStatement ps;
   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   private static GoodsDAO dao;
   
   // 드라이버 등록 
   public GoodsDAO()
   {
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   }
   // 연결 
   public void getConnection()
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
	   }catch(Exception ex) {}
   }
   // 닫기 
   public void disConnection()
   {
	   try
	   {
		   if(ps!=null) ps.close();
		   if(conn!=null) conn.close();
	   }catch(Exception ex) {}
   }
   // 싱글턴 생성 => new를 사용하지 않고 재사용이 가능 => 메모리 절약 
   public static GoodsDAO newInstance()
   {
	   if(dao==null)
		   dao=new GoodsDAO();
	   return dao;
   }
   // 기능 : javascript => jquery = ajax = vuejs = vuex = pinia 
   // reactjs = redux = tanstack-query (typescript) = nodejs 
   // ====> nextjs 
   // 1. 직접 구현 
   public List<GoodsVO> goodsListData(int page)
   {
	   List<GoodsVO> list=new ArrayList<GoodsVO>();
	   try
	   {
		   // 연결 
		   getConnection();
		   // SQL 
		   String sql="SELECT no,goods_name,goods_poster,goods_price "
				     +"FROM goods_all "
				     +"ORDER BY no ASC "
				     +"OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
		   // 오라클 전송 
		   ps=conn.prepareStatement(sql);
		   // ? 에 값을 채운다 
		   ps.setInt(1, (page*12)-12);
		   // 결과값 받기 
		   ResultSet rs=ps.executeQuery();
		   // List에 담기 
		   while(rs.next())
		   {
			   GoodsVO vo=new GoodsVO();
			   vo.setNo(rs.getInt(1));
			   vo.setGoods_name(rs.getString(2));
			   vo.setGoods_poster(rs.getString(3));
			   vo.setGoods_price(rs.getString(4));
			   
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
   // 총페이지 
   public int goodsTotalPage()
   {
	   int total=0;
	   try
	   {
		   getConnection();
		   String sql="SELECT CEIL(COUNT(*)/12.0) FROM goods_all";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   total=rs.getInt(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
	   return total;
   }
   /*
    *   NO                                        NOT NULL NUMBER
 GOODS_NAME                                NOT NULL VARCHAR2(1000)
 GOODS_SUB                                          VARCHAR2(1000)
 GOODS_PRICE                               NOT NULL VARCHAR2(50)
 GOODS_DISCOUNT                                     NUMBER
 GOODS_FIRST_PRICE                                  VARCHAR2(20)
 GOODS_DELIVERY                            NOT NULL VARCHAR2(20)
 GOODS_POSTER                                       VARCHAR2(260)
 HIT                                                NUMBER
    */
   public GoodsVO goodsDetailData(int no)
   {
	   GoodsVO vo=new GoodsVO();
	   try
	   {
		   getConnection();
		   String sql="UPDATE goods_all SET "
				     +"hit=hit+1 "
				     +"WHERE no="+no;
		   ps=conn.prepareStatement(sql);
		   ps.executeUpdate();
		   
		   sql="SELECT no,goods_name,goods_poster,goods_price,"
			  +"goods_sub,goods_discount,goods_delivery,"
			  +"goods_first_price "
			  +"FROM goods_all "
			  +"WHERE no="+no;
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setNo(rs.getInt(1));
		   vo.setGoods_name(rs.getString(2));
		   vo.setGoods_poster(rs.getString(3));
		   vo.setGoods_price(rs.getString(4));
		   vo.setGoods_sub(rs.getString(5));
		   vo.setGoods_discount(rs.getInt(6));
		   vo.setGoods_delivery(rs.getString(7));
		   vo.setGoods_first_price(rs.getString(8));
		   
		   rs.close();
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
}
