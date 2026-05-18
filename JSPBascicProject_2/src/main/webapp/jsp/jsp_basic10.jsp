<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
    HTML 태그
    
    position : 위치 속성 => 원하는 위치에 출력이 가능하게 만드는 속성
               위치 종류 => static / relative / absolute / fixed / sticky
               
    ------------------------------------------------------------------------
    1. static
       : 모든 html 태그의 기본값
         소스 순서대로 배치
         top / left / right / bottom을 무시하므로 position을 해줘야함
         --------------------------------------------------------
         사용법 
        태그(id,class) {
           position:static => 생략이 가능
        }
         
                        
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.static{
 position: static;
 width: 100px;
 height: 100px;
 background-color: red;
 top: 20px;
 left: 20px; /* 좌표를 무시하기 떄문에 0.0으로 실행이 된다 */
}
</style>
</head>
<body>
 <%-- 1. 기본 흐름에 따라 배치
      2. top / left가 적용되지 않는다
   --%>
 <div class="static">static</div>
</body>
</html>