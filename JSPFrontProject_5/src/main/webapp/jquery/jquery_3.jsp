<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#btn').on('click',function(){
		$.ajax({
			// 이 안에 5번 부터 9번 내용이 들어가 있다 => ajax.jsp파일 내용 중
			type:'POST',
			url:'sub.jsp',
			success:function(response) // 여기가 연결되면 실행된 결과값을 가지고 온다
			{                          // html/json 두가지가 들어갈 수 있다
				alert(response)
				$('#print').html(response)
			}
		})
	})
})
</script>
</head>
<body>
 <button id="btn">클릭</button>
 <div id="print"></div>
</body>
</html>