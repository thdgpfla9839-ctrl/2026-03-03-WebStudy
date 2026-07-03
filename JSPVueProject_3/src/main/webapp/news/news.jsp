<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--  실시간 뉴스 파일 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.news-box{
width:700px;
margin:30px auto;
background:#fff;
border-radius:15px;
box-shadow:0 5px 20px rgba(0,0,0,.15);
overflow:hidden;
}

.news-box h2{
margin:0;
padding:18px;
background:#0d6efd;
color:white;
text-align:center;
font-size:24px;
}

#newsList{
list-style:none;
padding:20px;
margin:0;
}

#newsList li{
margin-bottom:12px;
border:1px solid #ddd;
border-radius:10px;
transition:.3s;
background:white;
}

#newsList li:hover{
background:#f8f9fa;
transform:translateX(5px);
box-shadow:0 3px 10px rgba(0,0,0,.15);
}

#newsList a{
display:block;
padding:15px;
text-decoration:none;
color:#333;
font-size:17px;
font-weight:bold;
}

#newsList a:hover{
color:#0d6efd;
}

}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	const ws = new WebSocket("ws://localhost:8080/JSPVueProject_3/news")
	const newsList=$('#newsList')
	ws.onopen=()=>console.log("서버 연결 완료")
	ws.onclose=()=>console.log("서버 연결 종료")
	ws.onerror=(e)=>console.log("에러:",e)
	ws.onmessage=(event)=>{
		try
		{
			const data = JSON.parse(event.data)
			console.log(data)
			let html=""
			data.map(news=>{
				html+='<li><a href="'+news.link+'" target="_blank">'+news.title+'</li>'
			})
			$('#newsList').html(html)
			
			const now = new Data()
			$('#updateTime').text("마지막 업데이트 :"+now.toLocaleTimeString())
		}
		catch (err)
		{
			// event.data => 이 값은 뉴스서버 파일에 session.getBasicRemote().sendText(message); 이 데이터다
			console.err("JSON 파싱 실패:",err,event.data)
		}
	}
	
})
</script>
</head>
<body>
 <div class="news-box">
  <div class="news-header">
    <h2>실시간 뉴스</h2>
  </div>
   <ul id="newsList">
    
   </ul>
    <div class="update" id="updateTime">
     마지막 업데이트 : 
    </div>
 </div>
</body>
</html>