<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
     유동 속성 
     float => right / left 
     사용법 
       float:right
       float:left
       ----------------- 레이아웃 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container{
  width: 800px;
  height: 200px;
  border: 1px dotted red;
}
.box {
  width: 100px;
  height: 100px;
  background: blue;
}
.a {
  float: left;
}
.b {
 float: right;
}
</style>
</head>
<body>
  <div class="container">
    <div class="box a">Left</div>
    <div class="box b">Right</div>
  </div>
</body>
</html>