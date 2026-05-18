<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      <%! %> : 사용빈도가 거의 없다 => 메소드 (노출) 
      ------ 자바빈즈 / 자바클래스 
             ------ VO 
             JSP : EmpBean , MyBatis : EmpDTO , Spring : EmpVO
                   => 캡슐화 이용 (변수 읽기 getter/쓰기 setter)
       => 코딩되는 위치 
          class a_jsp extends HttpServlet
          {
              ----------------------
              
               <%! %> : 클래스 영역에 선언 
                        멤버변수 / 메소드 
              ----------------------
              public void _jspService()
              {
                 -------------------
                  <% %> , <%= %>
                 -------------------
              }
          }
                   
 --%>
<%!
    int p=1;
    public int add(int a,int b)
    {
    	return a+b;
    }
    // page => 내장 객체 => this
    // Object page=this
    // JSP에서는 this대신 page라고 사용 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%
      int a=10;
      int b=20;
      int c=add(a,b);
  %>
  <%= c %> <br>
  <%= p %>
</body>
</html>