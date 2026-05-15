<%@page import="com.sist.dao.BoardDAO"%>
<%@page import="com.sist.dao.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
  // 화면 출력하는 곳이 아니라 값만 받는 곳 => 사용자의 요청값을 받아서 처리
  // 데이터 4개를 먼저 받는다
  String name = request.getParameter("name");
  String subject = request.getParameter("subject");
  String content = request.getParameter("content"); // html 부분에 name으로 변수 선언이 무조건 돼야 여기서 값을 받아올 수 있따 
  String pwd = request.getParameter("pwd");
  
  
  BoardVO vo = new BoardVO();
  vo.setName(name);
  vo.setSubject(subject);
  vo.setContent(content);
  vo.setPwd(pwd);
  
  
  
  // 오라클에 전송
  BoardDAO dao = new BoardDAO();
  dao.boardInsert(vo);
  
  
  // 화면으로 이동하게끔 따로 해줘야해
  response.sendRedirect("list.jsp");
%>
