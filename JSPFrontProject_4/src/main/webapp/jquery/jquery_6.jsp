<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
       이벤트 처리방식
           1) 고전적 방식
              : $(). 
              
              마우스오버랑 마우스아웃은 최근에 호버로 바뀜
              
              
           2) 리스너 이용 방식 => 이게 jquery4버전에서 사용하는 방식이고, 최근 권장사항 방식이다
              : $().on('click',function(){})
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
  margin: 0px auto;
  width: 500px;
}
h1 {
	
	text-align: center
}
#chatArea{
 width: 300px;
 height: 300px;
 overflow-y:auto;
 border: 1px solid black; 
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-4.0.0.min.js"></script>
<script type="text/javascript">

// 이벤트가 발생하면 어떻게 처리할지
// 사용자가 이벤트 처리를 요청하면 DOMscript를 이용한다 => 라이브러리
// 태그를 조작, 제어하는 프로그램 : DOM 프로그램
// 속성이나 스타일 조작, 데이터 변경이 가능하게끔 해준다 => DOM
// DOM의 대표적인게 jquery

$(function(){
	$('#sendMsg').on('keydown',function(key){
		if(key.keyCode===13) // 13번이 enter
		{
			 // 4버전은 key.keyCode==='Enter'
			 let msg=$(this).val() // this => '#sendMsg'
			 if(msg.trim()==="")
				 {
				     // trim() : 좌우의 공백을 제거할 때 사용
				     $(this).focus()
				     return
				 }
			 $('#recvMsg').append(msg+"<br>")
			 $(this).val("")
			 $(this).focus()
			 
			 let ch=$('#chatArea').height()
			 let m=$('#recvMsg').height()-ch
			 $('#chatArea').scrollTop(m) // 스크롤바가 항상 아래에 위치하게끔
		}
	})
})
</script>
</head>
<body>
 <div class="container">
  <div class="row">
   <h1>실시간 채팅 (엔터 - 문자열 전송)</h1>
    <table class="table">
     <tr>
      <td>
       <div id="chatArea">
        <div id="recvMsg"></div>
       </div>
      </td>
     </tr>
      <tr>
       <td>
        <input type="text" size="38" id="sendMsg">
       </td>
      </tr>
    </table>
  </div>
 </div>
</body>
</html>