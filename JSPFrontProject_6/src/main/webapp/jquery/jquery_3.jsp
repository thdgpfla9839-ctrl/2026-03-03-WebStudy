<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    
           removeClass : <a class=""> => 추가하거나 삭제할 때 => 주로 메뉴에서 사용
           addClass
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-4.0.0-rc.1.min.js"></script>
<script type="text/javascript"></script>
<style type="text/css">
*{
 margin: 0px;
 padding: 0px;
 box-sizing: border-box;
}
.menu{
 display: flex; /* 이미지 카드 : grid*/
 list-style: none;
 background-color: #333
}
.menu li {
	padding: 15px 25px; 
	color: white;
	cursor: pointer;
}
.menu li.active {
	background: #2196f3;
}
.content{
  display: none;
  padding: 20px;
  border: 1px solid #ddd;
}
.comtent.active {
	display: block;
}
</style>
</head>
<body>
<%-- 태그는 사용자 정의가 없다
     속성은 사용자 정의가 가능하다
     예약이나 결제를 할 때는 사용자 정의 속성을 이용한다
     사용자 정의 => 'data-'라고 임의로 만든 거
 --%>
 <ul class="menu">
  <li class="active" data-tab="1">홈</li>
  <li data-tab="2">회원</li>
  <li data-tab="3">묻고 답하기</li>
 </ul>
 <div id="tab1" class="content active">
 홈입니다
 </div>
 <div id="tab2" class="content">
  회원 관련 메뉴입니다
 </div>
 <div id="tab3" class="content">
  묻고 답하기 메뉴입니다
 </div>
</body>
</html>