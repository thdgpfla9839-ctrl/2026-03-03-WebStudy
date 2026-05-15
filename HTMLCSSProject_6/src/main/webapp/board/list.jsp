<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<% 
   // 출력할 데이터 => 1. 사용자가 요청한 데이터 받기 (오늘은 페이지에 해당됨 => 한 파일 안에서 데이터를 변경 => 동적 페이지)
   // HTML은 정적 페이지라 자체 내에서 데이터 변경이 안 됨
   // 동적페이지 : jsp / javascript but, 자바스크립트는 다른 서버에 연결이 안된다
   
   String strPage = request.getParameter("page");
   // request : 내장객체 => 이미 객체 생성이 돼있다.
		   // 리퀘스트가 해주는 역할....
		   //
		   //
		   // 첫번 째 수행에는 페이지를 보낼 수 없다 -> 그래서 조건-디폴트 페이지를 만들고 시작해야해 안 그러면 오류발생
		   
		   if(strPage==null)
			   strPage="1"; // 여기까지가 디폴트 페이지 잡기(자동으로 1페이지 띄워주는 코드)
           
		   // 현재 페이지로 설정
		   int curpage = Integer.parseInt(strPage);
			  /*  System.out.println(curpage); */
			  
		
		   // 오라클에서 데이터 읽기	  
		   BoardDAO dao = new BoardDAO();
		   List<BoardVO> list = dao.boardListData(curpage);
		   
		   // 총페이지
		   int count = dao.boardRowCount();
		   int totalpage = (int)(Math.ceil(count/10.0));
		   count = count-((curpage*10)-10); // 페이지가 넘어갈 때마다 10씩 뺴줘야한다 시작하는 위치가 20부터 시작하면
		   
			   
			   
    
%>
    
    <%-- 
        btn => link, 입체효과
        버튼 크기)
        btn-xs / btn-md / btn-sm / btn-lg
        
        버튼 색상)
        btn-danger(레드) / btn-success(그린) / btn-warning(주황) / btn-info(CYAN) / btn-primary(블루) / btn-default(그레이) 
   
    --%>
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
  margin: 0px auto; /* 가운데 정렬 */
  width: 800px;
}
h3 {
  text-align: center;
}
.board-wrap{
  background: #fff;
  padding: 30px;
  border-radius: 18px;
  box-shadow: 0 8px 25px rgba(0,0,0,0.08);
}
.btn-success{
  border: none;
  border-radius: 30px;
  padding: 8px 18px;
  font-weight: bold;
  transition:all 0.3s ease;
}
.btn-success{
  transform:tranlateY(-2px);
  box-shadow: 0 5px 15px rgba(0,206,201,0.4);
}
.table {
  background: white;
  border-radius: 12px;
  overflow: hidden;
}
.table tbody tr.a:hover{
  background: #f1f5ff;
  transform:scale(1.01);
}
</style>
</head>
<body>
 <div class="container">
  <div class="board-wrap">
   <h3>자유 게시판 (jdbc 中 CRUD)</h3>
    <div class="row">
	     <table class="table">
	       <tbody>
	         <tr>
	           <td>
	            <a href="insert.jsp" class="btn btn-sm btn-success">새글</a>
	            <%--
	                 btn => link , 입체효과 
	                 btn-xs / btn-md / btn-sm / btn-lg 
	                 btn-danger(red) / btn-success(green) / 
	                 btn-warning(주황색) 
	                 btn-info(cyan) / btn-primary(blue) / 
	                 btn-default(gray) 
	             --%>
	           </td>
	         </tr>
	       </tbody>
	     </table>
     </div>
     <div class="row" style="margin-top: 10px">
     <table class="table">
       <thead>
         <tr class="danger">
           <th width=10% class="text-center">번호</th>
           <th width=45% class="text-center">제목</th>
           <th width=15% class="text-center">이름</th>
           <th width=20% class="text-center">작성일</th>
           <th width=10% class="text-center">조회수</th>
         </tr>
       </thead>
       <tbody>
<%
    // 10개씩 출력 => for문 이용
    for(BoardVO vo:list)
    {
%>

                     <tr class="a">
			           <td width=10% class="text-center"><%=count-- %></td>
			           <td width=45%><%= vo.getSubject() %></td>
			           <td width=15% class="text-center"><%= vo.getName()%></td>
			           <td width=20% class="text-center"><%=vo.getDbday() %></td>
			           <td width=10% class="text-center"><%=vo.getHit() %></td>
			         </tr>  
 
<% // html 안에 자바 for문 작성할 때는 for문의 괄호를 각자 기호 안에 넣어줘야 한다는 점 확인 잘 해주자 한번에 다 집어넣을 수 없음!!!!
    }
%>
     </tbody>
   </table>  
     </div>
     <div class="row text-center" style="margin-top: 20px">
       <a href="list.jsp?page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-warning">이전</a>
       <%=curpage %> page / <%=totalpage %> pages
       <a href="list.jsp?page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-warning">다음</a>
     </div>
    </div>
   </div>
</body>
</html>