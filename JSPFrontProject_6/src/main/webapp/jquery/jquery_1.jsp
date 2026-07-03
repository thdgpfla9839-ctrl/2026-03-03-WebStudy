<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
      Jquery의 효과 => 댓글이나 삭제할 때 사용
      :  
         1) hide : 감추는 기능 => 더보기 display:none
         2) show : 보여주는 기능 =>     display:''  
         3) toggle : 버튼에서 2번이 가능 => 라디오 / 체크박스 처리할 때 사용
    
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- import --%>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// jquery4에서 사용하는 방식
	$('#hideBtn').on('click',function(){
		// css 선택자 => 태그를 선택한다 => 태그 제어 프로그램
		$('#id').hide()
	})
	$('#showBtn').on('click',function(){
		$('#id').toggle()
	})
})
</script>
</head>
<body>
 <button id="hideBtn">숨기기</button>
 <button id="showBtn">보이기</button>
 <button id="toggleBtn">토글</button>
 <div>
  <input type="text" id="id" size="20">
 </div>
</body>
</html>