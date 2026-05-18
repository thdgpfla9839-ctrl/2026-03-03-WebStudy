<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    
<%--
     jsp에서의 자바코딩은 <% %>영역을 벗어나면 일반 텍스트로 인식된다
     ----------------------------------------------------
     
     위치속성)
     1. static : 태그의 코딩에 따라 순서대로 배치 = 디폴트
     2. relative : 상대 태그의 좌표를 이용(바로 옆에 있는 태그가 기준점)
     3. absolute : 절대 좌표
     4. fixed    : 고정 좌표
     5. sticky   : 스크롤 시 마다 고정 좌표가 됨
     
     위치 설정)
     top
     left
     right
     bottom
     
     유동속성)
     float => 메뉴 / 버튼을 만들 때 => left나 right 선택
     z-index => 레이어 개념 => 채팅이나 상담창 만들 때
     ----------------------------------------------------------
     
 --%>    
 <%
   List<String> images = new ArrayList();
   images.add("../images/m1.jpg");
   images.add("../images/m2.jpg");
   images.add("../images/m3.jpg");
   images.add("../images/m4.jpg");
   images.add("../images/m5.jpg");
   
   List<String> key = new ArrayList();
   key.add("a");
   key.add("b");
   key.add("c");
   key.add("d");
   key.add("e");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div.wrap {
	width: 900px;
	height: 900px;
	border: 1px solid red;
	position: absolute;
	
}
img {
	width: 200px;
	height: 200px;
	position: absolute;
}
.a {
 top: 0px;
 left: 0px;
}
.b {
 top: 0px;
 right: 0px;
}
.c {
 bottom: 0px;
 left: 0px;
}
.d {
 bottom: 0px;
 right: 0px;
}

/*
   전체를  900
   900 - 200 : 700/2 => 350
*/

.e {
  top: 350; 
  left: 350;
}
</style>
</head>
<body>
  <h1>Absolute</h1>
   <div class="wrap">
    <%
       for(int i=0;i<images.size();i++)
       {
    %>
    
       <img src="<%=images.get(i) %>" class="<%=key.get(i)%>">
    <% 	   
       }
    %>   
   </div>
</body>
</html>