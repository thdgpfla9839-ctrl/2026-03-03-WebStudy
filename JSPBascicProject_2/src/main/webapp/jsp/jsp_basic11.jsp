<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- relative
     : 배치가 되면 원래 자리에서 위치로 약간 이동 (top / right / bottom / left를 이용한다)
     
     사용법)
     선택자 {
       position : relative;
       top: 10px;
       left: 10px;  
     }    
     
        top-left  / top-right 
        bottom-left / bottom-right
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.relative{
 position: relative;
 top: 20px;
 left: 20px;
 width: 100px;
 height: 100px;
 background-color: blue;
}
</style>
</head>
<body>
<%-- 
      body 원래 위치에서 top :20px , left : 20px => 오른쪽으로 이동
 --%>
 <div class="relative">Relative</div>
</body>
</html>