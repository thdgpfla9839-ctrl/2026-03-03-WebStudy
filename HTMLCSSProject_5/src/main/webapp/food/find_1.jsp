<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- p.95 참고 
   
   [웹 개발과정]
   1. VO / DTO => 오라클에서 보낸 값을 모아서 전송을 하려는 목적이다
                  오라클의 컬럼과 일치를 시켜야함
   2. DAO => VO에 값을 채운다
   3. JSP => 화면에 UI 만들기(HTML / CSS를 이용)
   4. DAO에서 얻어온 데이터를 화면에 출력               
--%>
<!-- 
   HTML 태그 
   화면 UI => <div> <span> <ul> ...
   데이터 입력 => 전송 
      데이터를 모아서 한번에 전송 : <form>
                              | 보안 => method=post
                              | 일반 검색 => method=get 
                              | 데이터를 전송하는 태그는 반드시 name속성을 가지고 있어야 된다
   HTML안에서 전송할때 사용 
   => JavaScript : Ajax , Vue , React  
 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">

.container {
 /* container는 바싹 붙어서 마진으로 내려주자 */
 margin-top: 50px;
}

.row {
margin: 0px auto; /* 가운데 정렬 */
width: 960px;
}

p {
/* 글자수가 범위에서 넘어가면 css가 깨지니 알아서 잘라줘라 하는 명령어 overflow */
 overflow: hidden; /* 벗어난 글자는 hidden을 이용해 숨겨줘라 */
 white-space: nowrap; /* 한줄로 만든다 */
 text-overflow: ellipsis; /* 생략된 부분 ... 처리해줌 */
}
</style>
</head>
<body>
 <div class="container">
  <div class="row text-center">
   <form action="find.jsp" method=""> 
   
   <%-- <form> : 전송 데이터를 한번에 묶어서 보낼때 사용한다 => input / textarea / select 가 있는 것만 보낼 수 있고 저 세가지 안에 입력된 값만이 넘어가는 것이다  
                method="post" : 보안이 필요한 데이터를 보낼 때는 이렇게 post방식으로 작성해준다 / 만약 ""안을 생략하면 GET방식으로 전송된다
                 
               <form>의 속성 : 1) method : 공개 / 비공개 결정
                                         공개 -> GET 방식
                                         비공개 -> POST 방식
                             2) action : 어떤 파일로 전송할 것인지 설정한다
                                         예) ?page=1&type=한식
                             3)  id , class : CSS / JavaScript에서 태그제어 
                             
                             4)  name : JSP/Servlet으로 값을 전송   
       -------------------------------------------------------------------------------------------
          selected / checked / readonly / required / placeholder
                                         text,textarea       
                                
           <select>   radio/checkbox
           
           => GET : SELECT
           => POST : INSERT
           ----------------------
           => PUT : UPDATE 
           => DELETE : DELETE
           ---------------------- Rest API                
                          
           --%>
   <select name="column" class="inpur-sm"> <%-- name 값이 변수명이 된다 --%>
    <option value="address">주소</option> <%-- value를 안 써주면 주소라는 값이 넘어가지는데 value해서 넘어가는 값을 지정해줌  --%>
    <option value="type" selected>음식 종류</option> <%-- selected를 넣으면 그걸 추가한 부분이 가장 먼저 위로 올라온다 --%>
    <option value="name">업체명</option>
   </select>
   <input type="text" name="fd" size=20 class="input-sm" required >
     <button class="btn-sm btn-info">검색</button>
     </form>
  </div>
 </div>
</body>
</html>