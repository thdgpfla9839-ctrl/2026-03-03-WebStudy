<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
    
    <%-- p. 240 
    
    자바 / HTML 분리 
    <% %> <%= name%> => ;을 사용하면 안된다 
      |      | 출력 => System.out.println();
    자바 소스 
    => 자바 문법과 동일 => 문장 ; 
    
         자바소스에 들어가면 자바 문법과 동일하게 본다 
         HTML 괄호 안에 출력내용은  System.out.println(); 괄호 안에 있는 내용을 출력하는 자리라 ;을 사용하면 안 됨
         
          
   화면 간격 조절 
     밖 => margin 
          좌우 : margin-left margin-right
                -----------
          상하 : margin-top margin-bottom 
                ----------
          간단한 형식 
          ---------
           margin: () () () () 
                    |  | |   |
                            left
                         bottom
                   top right  ===> 시계방향 
     안 => padding 
     선 => border 
--%> 
    
 <%
 
     // 1. 자바에서 화면에 출력할 데이터를 가지고 온다
     // 2. 사용자가 전송한 값을 받는다 -> 페이지를 보내준다
     // 웹은 모든 데이터가 문자(String)로 들어온다 => 그래서 Wraper : 문자를 정수(숫자)로 변경 해줘야 함
     // list.jsp?page=1 => 이부분이  getparameter() 이다
     String strPage = request.getParameter("page");
     
     // 맨 처음에 사이트를 접속하면 1페이지는 자동으로 눌러지게 해야함
     if(strPage==null) // 페이지를 선택을 못하면 디폴트 페이지를 1페이지로 지정
         strPage="1";
     int curpage = Integer.parseInt(strPage);
     FoodDAO dao = new FoodDAO();
     List<FoodVO> list = dao.foodListData(curpage);
     int totalpage = dao.foodTotalPage();
     
     final int BLOCK=10; // 10개씩 출력하겠다
     int startPage = ((curpage-1)/BLOCK*BLOCK)+1; // 1 11 21... 이렇게 출력
     int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK; // 10 20 30 
     
     if(endPage>totalpage)
    	 endPage=totalpage;
     
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
 .container {
   margin-top: 50px;
 }
 .row {
   /* 화면에 출력 / 한줄로 출력하고 싶으면 .row{} 사용 */
   margin: 0px auto;
   width:960px;
 }
 p {
 /* p는 태그이니까 .을 쓰면 안 된다 */
     overflow: hidden; 
     white-space: nowrap; /* 다음줄로 내리지 말라 */
     text-overflow: ellipsis; /* 문자 수가 넘어가면 ... 찍어라 그래야 글자가 안 깨진다 */
 }
</style>
</head>
<body>
  <div class="container"><!-- 전체 영역 -->
    <div class="row"><!-- 출력하는 영역 -->
    <%
       for(FoodVO vo:list)
       {
    %>
      <div class="col-md-3"> <!-- 숫자합 12가 되면 자동으로 다음줄로 내려간다 -->
	    <div class="thumbnail">
	      <a href="detail.jsp?no=<%=vo.getNo()%>">
	        <img src="<%=vo.getPoster() %>" alt="" style="width:240px;height: 150px">
	        <div class="caption">
	          <p><%=vo.getName() %></p>
	        </div>
	      </a>
	    </div>
	  </div>
	<%
       }
	%>
    </div>
    <div class="row text-center">  <!-- 페이지 나눠서 모양 잡는 부분  -->
     <ul class="pagination">
       <%
          if(startPage>1) // 1 11 21 31
          {
              %>
                   <li><a href="list.jsp?page=<%=startPage-1%>">&laquo;</a></li>
              <%
                 }
              %>
              <%
                 for(int i=startPage;i<=endPage;i++)
                 {
              %>
              <li <%=i==curpage?"class=active":"" %>><a href="list.jsp?page=<%=i %>"><%=i %></a></li>
              <%
                 }
              %>
              
              <%
                 if(endPage<totalpage)
                 {
              %>
              <li><a href="list.jsp?page=<%=endPage+1%>">&raquo;</a></li>
              <%
                 }
              %>
            </ul>
           </div>
         </div>
       </body>
       </html>