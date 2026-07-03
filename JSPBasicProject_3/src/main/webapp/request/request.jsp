<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"
    buffer="16kb" info="상품 목록" errorPage="error.jsp"
%>
<%--
      contentType="text/html; charset=UTF-8"
      import="java.util.*,com.sist.dao.*"
      errorPage="error.jsp"
 --%>
<%
    // 1. 사용자가 요청한 값 => request.getParameter() => page 
    String strPage=request.getParameter("page");
    // 처음 실행시에는 page전송이 불가능 => default 
    /*
        http://localhost/JSPBasicProject_3/request/request.jsp
        => ?가 없는 상태 => null
        ?page=   ""
    */
    if(strPage==null)
    {
    	strPage="1";
    }
    // 현재 페이지 
    int curpage=Integer.parseInt(strPage);
    // 데이터베이스 연동 
    GoodsDAO dao=GoodsDAO.newInstance();
    // 데이터 읽기 
    List<GoodsVO> list=dao.goodsListData(curpage);
    // 총페이지 
    int totalpage=dao.goodsTotalPage();
    
    // 블록별 
    final int BLOCK=10;
    // 1 11 21 
    /*
         1  2  3  4  5  6  7  8  9  10
         11
         21
    */
    int startPage=((curpage-1)/BLOCK*BLOCK)+1;
    // 10 20 30 
    int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
    
    if(endPage>totalpage)
    	endPage=totalpage;
    
    // a href="detail.jsp?no=1 
    //         ----------
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
  font-family: sans-serif , Arial;
  padding: 40px;
  background: #f5f5f5;
}
.card-wrap{
   display: flex;
   flex-wrap: wrap;/*  이미지 => 범위를 벗어나면 아래로 내려라 */
   gap:20px;
}
.card {
  width: calc(25% - 15px); /* 한줄에 4개 */
  background: white;
  border-radius: 12px; /* top-left top-right bottom-right bottom-left */
  overflow: hidden;
  text-transform: 0.3s /* 수행시간 3초 */
}
.card:hover{
  transform:translateY(-5px)
}
.card img{
  width: 100%;
  height: 220px;
  object-fit:cover;/* 이미지를 화면 크기에 맞게 꽉채운다 */
  display: block;
}
.card-body {
  padding: 15px;
}
.card-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.card-text {
  color: #666;
  font-size: 14px;
}
.pagination {
  display: flex;
  gap:10px;
  align-items: center;
  margin-top: 20px;
  justify-content: center;
}
.pagination a{
 width:40px;
 height: 40px;
 border-radius: 50%;
 border:1px solid #ccc;
 display: flex;
 align-items: center;
 justify-content: center;
 text-decoration: none;
 color:#333;
 transition:0.2s;
}
.pagination a:hover{
  background: #f0f0f0;
}
.pagination .arrow{
  font-size: 18px;
}
.pagination a.active{
  background: #0000FF;
  color: white;
  border-color:#333
}
.page-card{
  width: 100%;
}
.detail {
  text-decoration: none;
  color:black;
}
.detail:hover{
  text-decoration: underline;
  color:green;
}
</style>
</head>
<body>
  <div class="card-wrap">
   <%
      for(GoodsVO vo:list)
      {
   %>
      
	    <div class="card">
	      <img src="<%=vo.getGoods_poster() %>">
	      <div class="card-body">
	        <div class="card-title">
	        <a href="detail.jsp?no=<%=vo.getNo()%>" class="detail">
	         <%=vo.getGoods_name() %>
	        </a>
	        </div>
	        <div class="card-text"><%=vo.getGoods_price() %></div>
	      </div>
	    </div>
	 
   <%
      }
   %>
  </div>
  <div class="page-card">
   <div class="pagination">
    <%
      if(startPage>1)
      {
    %>
    <a href="request.jsp?page=<%=startPage-1 %>" class="arrow">&laquo;</a>
    <%
      }
    %>
    <%
      for(int i=startPage;i<=endPage;i++)
      {
    %>
    <a href="request.jsp?page=<%=i %>" <%=curpage==i?"class=active":"" %>><%=i %></a>
    <%
      }
    %>
    <%
      if(endPage<totalpage)
      {
    %>
    <a href="request.jsp?page=<%=endPage+1 %>" class="arrow">&raquo;</a>
    <%
      }
    %>
  </div>
  </div>
  
</body>
</html>