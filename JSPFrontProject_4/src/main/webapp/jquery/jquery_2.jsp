<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 
        2. 문자 조작
           => textContent : 태그와 태그에 값을 주입(순수한 문자열) => HTML은 파싱이 안 된다
                            text()
           => innerHTML : 태그와 태그에 HTML 추가 => HTML 파싱이 가능
                          html() 
           => appendChild : 태그와 태그 사이에 태그 첨부
                            append()
                            
           => html() VS append()   
              html : 기존게 지워지고 바뀌는 것    
              append() : 기존은 유지되고 계속 붙여나가는 거     
====================================================================================                                    
    
    
     -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	
	// 바닐라 js로 값읽기
	let h1=document.querySelector("h1")
	let val=h1.textContent
	console.log("h1"+val)
	
	// jquery로 값읽기
	val=$('h1').text()
	console.log('h1:jquery=>'+val)
	
	
	// 값 첨부하기
	h1.textContent="바닐라 js 이용"
	/* $('h1').text("jquery js 이용") */ // 둘중 하나는 주석 걸고 실행해야 알 수 있음
	
	
	let h2=document.querySelector("h2")
	h2.innerHTML='<font color=red>순수 자바스크립트</font>'
	
	$('h2').html('<font color=green>jquery를 사용1</font>')
	$('h2').html('<font color=blue>jquery를 사용2</font>') // 단점을 실행하면 덮어써진다 왜냐 html()을 썼기 때문
	alert($('div').html()) // html() 말고 text() 주면 결과값이 달라짐
})
</script>
</head>
<body>
 <h1>안녕 제이쿼리</h1>
 <h2>안녕 제이쿼리</h2>
 <h3>안녕 제이쿼리</h3>
 <h4>안녕 제이쿼리</h4>
 <h5>안녕 제이쿼리</h5>
  <div>
   <h6>Div안에 h1</h6>
   <h6>Div안에 h2</h6>
   <h6>Div안에 h3</h6>
  </div>
</body>
</html>