<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- 이벤트 적용 -> this --%>
    <%-- 코드 추가하기 --%>
    <%--
       <textarea>값</textarea> => text()로 받아오는 것이 아니라 val()로 받아온다
       ===============================================================
       이벤트 사용 빈도 높은 것)
       click  / hover / keyup / change
       
       ================================================================
        val() / text() / html() / prop() / append() / attr()
	   
	    여러개 중에 선택된 태그를 가지고 오는 경우 : $(this)
            
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
li:hover{
 cursor:pointer;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// 이벤트가 적용이 된 것이 this
	$('img').hover(function(){
		$(this).css({'opacity':0.3,
		              'cursor':'pointor',
		              'border':'2px solid green'
		            })
	
  },function(){
	  $(this).css({
		  'opacity':1.0,
          'cursor':'none',
          'border':'none'
	  })
  })
  
  $('li').on('click',function(){
	  let data=$(this).text()
	  alert("선택된 과정:"+data)
	  $(this).text(data+"과정")
	  // 선택된게 this
  })
  
  $('#readBtn').on('click',function(){
	let msg=$('#msg').val()
	let sel=$('#sel').val()
	let t=$('textarea').text() // 태그와 태그 사이에 값은 text()를 쓰기도 하지만 val()을 써주는게 좋다
	alert("text:"+msg+"\nselect:"+sel+"\ntextarea:"+t)
	  
  })
  
  $('#writBtn').on('click',function(){
	   $('#msg').val("hello spring")
       $('#sel').val("프론트엔드")
	   $('textarea').val("안녕")
	  
  })
  
  
  $('#htmlBtn').on('click',function(){
	   $('#html').html("<font color=red>hello HTML</font>")
	  
  })
})
</script>
</head>
<body>
 <h1>이미지 스타일 변경</h1>
  <img src="m1.jpg" style="width: 150px"height="200px">
  <img src="m2.jpg" style="width: 150px"height="200px">
  <h1>this 사용</h1>
   <ul>
    <li>자바: jsp / jdbc / spring / spring-Boot 이용하는 프로그램</li>
    <li>오라클: 데이터를 저장하는 공간 => CRUD / JOIN / SubQuery / Index 공부해야하는 부분</li>
    <li>HTML/CSS: 브라우저 화면 UI</li>
    <li>자바스크립트: 브라우저 동적 페이지를 만드는 것 => 클라이언트 용</li>
    <li>라이브러리: 가장 대표적인 것이 jquery3 / jquery4는 이제 막 나온 버전 => Ajax 서버연동이 핵심</li>
    <li>MVC를 이용한 프로젝트 만들기(세미 프로젝트)</li>
    <li>SpringFrameWork : MVC 라이브러리</li>
    <li>우분투를 이용해 Host 방법 배우기 => AWS를 어떻게 이용할지</li>
    <li>SpringBoot : JSP, ThymeLeaf</li>
    <li>CI/CD 시작 => Git Action, Docker / Docker-Compose</li>
    <li>CI/CD : Jenkin / 쿠바네티스</li>
    <li>최종 프로젝트 : SPringBoot, ThymLeaf, VueJs</li>
    <li>CI/CD : 무중단</li>
    <li>AWS에서 작업</li>
    <li>1. MySQL </li>
    <li>2. JPA </li>
    <li>3. NodeJS </li>
    <li>4. TypeScript </li>
    <li>5. React / Redux </li>
    <li>6. TanStackQuery = 개인프로젝트</li>
  </ul>
   <h1>val() / 입력창 : input, textarea, select</h1>
     <input type="button" value="읽기" id="readBtn">
   <input type="button" value="쓰기" id="writeBtn">
   <input type="button" value="HTML" id="htmlBtn">
   <br>
   <input type=text id="msg" size=20 value="Hello JavaScript"><br>
   <select id="sel">
    <option>Back-End</option>
    <option>Front-End</option>
    <option>DataBase</option>
   </select><br>
   <textarea rows="3" cols="20">Hello</textarea>
   <br>
   <span id="html"></span>
</body>
</html>