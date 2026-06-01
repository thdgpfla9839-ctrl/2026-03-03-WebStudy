<%@page import="com.sist.vo.DataBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
    <%
       String strPage = request.getParameter("page");
       if(strPage==null)
    	   strPage="1";
       int curpage = Integer.parseInt(strPage);
       int start = (curpage*10)-10;
       List<DataBoardVO> list = DataBoardDAO.databoardListData(start);
       int totalpage = DataBoardDAO.databoardTotalPage();
       // 여기까지가 모델에 들어가는 소스 => 출력할 데이터만 전공 : request / session
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
 margin-top: 50px;
}

.row{
 margin: 0px auto;
 width: 900px;
}

h3 {
	text-align: center;
}
</style>
</head>
<body>
 <div class="container">
  <div class="row">
   <h3>자료실</h3>
    <table class="table">
     <tr class="success">
      <th width=10% class="text-center">번호</th>
      <th width=45% class="text-center">제목</th>
      <th width=15% class="text-center">이름</th>
      <th width=20% class="text-center">작성일</th>
      <th width=10% class="text-center">조회수</th>
     </tr>
     <%
         for(DataBoardVO vo:list)
         {
      %>
         <tr>
           <td width=10% class="text-center"><%=vo.getNo() %></td>
           <td width=45%><%= vo.getSubject()%></td>           
           <td width=15% class="text-center"><%=vo.getName() %></td>
           <td width=20% class="text-center"><%=vo.getDbday() %></td>
           <td width=10% class="text-center"><%=vo.getHit() %></td>
         </tr>
      
      <%
         }      
      %>
    </table>
      <table class="table">
       <tr>
        <td class="text-center">
          <a href="list.jsp?page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-danger">이전</a>
          <%=curpage %> page / <%=totalpage %> pages
          <a href="list.jsp?page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-danger">다음</a>
        </td>
       </tr>
      </table>
  </div>
 </div>
</body>
</html>