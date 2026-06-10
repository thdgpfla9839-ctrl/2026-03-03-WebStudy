<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 
         3. 속성 조작
            : class / src
            
            
        * 자바스크립트는 세미콜론을 안 붙여도 실행은 되지만 세미콜론을 붙이면
          문장이 끝나는지 표시가 되니까 오류가 더 줄어들 거야 그러니까 연습하는 동안 붙이자    
    
    
     -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// 태그를 제어하는 프로그램 'DOM script'
/* 	document.querySelector('img').src="https://img.megabox.co.kr/SharedImg/2026/05/13/zlk6dycnwuPdVuSH40RVickv2CYYCrcU_420.jpg"
 */	$('img').attr('src','https://img.megabox.co.kr/SharedImg/2026/05/13/zlk6dycnwuPdVuSH40RVickv2CYYCrcU_420.jpg'); // 위 아래 두 버전이 있다 $ ~ 이 버전이 최근에 많이 사용하는 형태라 권장함
 
 // input에 값 첨부 => val()
    /* document.querySelector("input[type='text']").value="hong" */ // 예전 버전
    $("input[type='text']").val("hong"); // 신 버전    
    $("input[type='password']").val("1234");    
    alert($("input[type='text']").val());   
    alert($("input[type='password']").val()); 
    
    // 괄호랑 .위치 때문에 에러가 발생했어 주의해서 작성하자
    // Jquery 메소드를 호출할 때는 객체 뒤에 .을 찍는다

    
})
</script>
</head>
<body>
 <img src=""><br>
 ID:<input type="text" id="id" size="15"><br>
 PW:<input type="password" id="pwd" size="15"><br>
</body>
</html>