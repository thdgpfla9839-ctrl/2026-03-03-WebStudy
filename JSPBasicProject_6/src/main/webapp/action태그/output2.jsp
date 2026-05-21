<%@page import="com.sist.bean.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 전송된 데이터를 받는 곳 (사용자가 데이터를 전송하면 톰캣에 의해 request에 의해 값이 읽어진다)
    String name = request.getParameter("name");
    String sex = request.getParameter("sex");
    String address = request.getParameter("address");
    String phone = request.getParameter("phone");
    String content = request.getParameter("content");
    // 데이터가 넘어오면 오라클로 보낸다 혹은 화면에 그냥 출력한다
    
    // 화면에 그냥 출력하는 방법을 쓸거야   
    // 인풋의 네임속성과 아웃풋의 네임속성 일치 시켜야한다
    
    //setter로 값을 받아오고 getter로 값을 출력할 거야
    MemberBean bean=new MemberBean();
    bean.setName(name);
    bean.setSex(sex);
    bean.setAddress(address);
    bean.setPhone(phone);
    bean.setContent(content);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>받은 데이터</h1>
  이름:<%=bean.getName() %><br>
  성별:<%=bean.getSex() %><br>
  주소:<%=bean.getAddress() %><br>
  전화:<%=bean.getPhone() %><br>
  소개:<%=bean.getContent() %>
</body>
</html>