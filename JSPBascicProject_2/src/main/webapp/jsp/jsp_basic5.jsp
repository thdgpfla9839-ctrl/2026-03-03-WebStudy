<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>제어문 (for)=최근 (현재 사용) => JSTL</h1>
  <table border=1 bordercolor=black width=560>
    <tr>
      <c:forEach var="i" begin="2" end="9">
        <th>${i }단</th>
      </c:forEach>
    </tr>
    <c:forEach var="i" begin="1" end="9">
      <tr>
        <c:forEach var="j" begin="2" end="9">
          <td align="center">${j }*${i }=${j*i }</td>
        </c:forEach>
      </tr>
    </c:forEach>
  </table>
</body>
</html>