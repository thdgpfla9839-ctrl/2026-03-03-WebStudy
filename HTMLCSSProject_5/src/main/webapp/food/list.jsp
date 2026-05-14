<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>

<%-- 쓰는 방식 이렇게도 가능 
          
           <%@  page import="java.util.*"  %>
           <%@ page import="com.sist.dao.*" %> 
           ----------------------------------------------  
             
     이 파일은 클래스가 아니고 메소드 영역이다 
         public class list_jsp extends HttpServlet
         {
            public void _jspInit(){} = 초기화
            public void _jspDestory(){} = 메모리 해제 
            
            public void _jspService() => 브라우저 전송 
            {
              out.println("<!DOCTYPE html>");
            }
         }
         
-----------------------------------------------
한가지 팁 : ctrl + shift+ / => 주석처리됨 \로 해제
--------------------------------------------------------------------------------
 코딩 방향) 1. 사용자가 요청한 데이터를 받는다 => request.getParameter()
          2. 자바에서 데이터베이스 연결 => 화면에 출력할 데이터를 읽어 온다 
-----------------------------------------------------------------------------------

     URL) 
         데이터를 받는 jsp
         데이터 전송 ?변수=값 
        		  ?변수=값&변수=값
        		  
         list.jsp?page=1 
                  ------
         --------  page값을 list.jsp로 보낸다
         detail.jsp?no=10
                    --
                    request.getParameter("no") => 모든 String으로 받는다
         GET 방식 



    --%>
<%
// 자바 소드 들어가는 부분 => 이 영역에서의 모든 문법은 자바와 동일하다
FoodDAO dao = new FoodDAO();

// 1. 사용자가 보낸 데이터 두가지 받기
String type = request.getParameter("type");
if (type == null) // 전송된 데이터가 없는 경우
{
	type = "한식"; // 디폴트를 한식으로 잡음
}
String strPage = request.getParameter("page");
if (strPage == null) {
	strPage = "1";

}

int curpage = Integer.parseInt(strPage);

// 2. 데이터 받기 시작
List<FoodVO> list = dao.foodListData(curpage, type);
int totalpage = dao.foodTotalPage(type);

// 3. 어떤 데이터가 들어왔는지 확인하는 과정 -> 콘솔창에서 결과값 봐야함
System.out.println("요청한 값: " + type);
System.out.println("페이지: " + curpage);
System.out.println("총 페이지: " + totalpage);
System.out.println("데이터: " + list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin-top: 50px;
}

.row {
  /* 화면에 출력 */
	margin: 0px auto;
	width: 960px;
}

p {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row tect-center">
			<a href="list.jsp?type=한식" class="btn btn-lg btn-danger">한식</a> <a
				href="list.jsp?type=양식" class="btn btn-lg btn-success">양식</a> <a
				href="list.jsp?type=중식" class="btn btn-lg btn-warning">중식</a> <a
				href="list.jsp?type=일식" class="btn btn-lg btn-info">일식</a> <a
				href="list.jsp?type=분식" class="btn btn-lg btn-primary">분식</a>
		</div>
		<div class="row" style="margin-top: 20px">
			<%
			for (FoodVO vo : list) {
			%>
			<div class="col-md-3">
				<div class="thumbnail">
					<a href=" #"> <img src="<%=vo.getPoster()%>" alt=""
						style="width: 240px; height: 150px">
						<div class="caption">
							<p><%=vo.getName()%></p>
						</div>
					</a>
				</div>
			</div>
			<%
			}
			%>
		</div>
		<div class="row text-center">
			<a
				href="list.jsp?page=<%=curpage > 1 ? curpage - 1 : curpage%>&type=<%=type%>"
				class="btn btn-sm btn-warning">이전버튼</a>
			<%=curpage%>
			page /
			<%=totalpage%>
			pages <a
				href="list.jsp?page=<%=curpage < totalpage ? curpage + 1 : curpage%>&type=<%=type%> class="
				btn btn-smbtn-warning">다음버튼</a>
		</div>
	</div>
</body>
</html>