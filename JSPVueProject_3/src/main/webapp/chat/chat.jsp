<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--  websocket은 실시간 뉴스/ 알림에 많이 사용됨
          => 시간 조절은 timer를 이용한다
    
     -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../chat/chat.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script type="text/javascript">
/*
 *  1. 실행과정
       window.onload=function(){} => 페이지가 브라우저에서 html을 메모리에서 읽어서 출력된
       => 제이쿼리에서는 : $(function(){})
          vue에서 사용되는 onload : mounted()
          react : componentDidMount()
          useEffect()
          |
          connection() 연결
          |
          websocket 서버연결
           => onopen
              onclose
              onmessage
         -------------------- 등록 : 호출할 함수 지정 => callBack 자동호출 함수
         => 이벤트 등록
              
     ==========================================================================
    	 1) 서버연결
    	    엔드포인트와 일치가 되면 서버 연결됨
    	    
    	 2) 서버에서 호출
    	     제일먼저 호출되는 부분이 @onopen 밑에 있는 부분
    	     
    	 3) 메시지 전송
    	   => 순서: 사용자 입력 - <input type=text id=messageInput> - 서버전송 send() 
    	           - 입력값 읽기 - 공백검사 - 서버 전송 - 입력값 초기화
    	           
    	 4) 서버에서 메시지 수신  => 	 websocket.onmessage=onMessage
    	    서버의 종류 ) 입장 메시지인지 / 퇴장 메시지인지 구분
    	               상대방이 보냈는지 / 본인이 보냈는지 구분 => 누가 보낶는지에 따라 위치가 달라진다
    	               
    	               사용자 정의 부분은 우리가 만든 함수
          

 */
 let websocket
 window.onload=function(){
	 connection()
 }
 
 function connection(){
	 websocket = new WebSocket("ws://localhost/JSPVueProject_3/chat")
	 websocket.onopen=onOpen
	 websocket.onclose=onClose
	 websocket.onmessage=onMessage
 }
 
 function onOpen(event){
	 alert("채팅서버에 연결됨")
 }
 
 function onClose(event){
	 alert("채팅서버에 연결이 해제됨")
 }
 
 function onMessage(event){
	 let data=event.data // 전송된 데이터를 받아온다
	 // 채트메시지 파일에 [알림]부분 소스의 msg를 받아오거
	 if(data.substring(0,4)==='msg:'){
		 appendMessage(data.substring(4))
	 }
 }
 
 function appendMessage(msg){
	 $('#chatBox').append(msg+'<br>')
	 // 스크롤의 위치 지정 => 가장 밑으로 내려오게 지정
	 $('#chatBox').scrollTop($('#chatBox').scrollHeight())
 }
 
 function send(){
	 // 입력값 읽기
   let msg=$('#messageInput').val()
   // 공백검사
   if(msg.trim()==="")
	   {
	      $('#messageInput').focus()
	      return
	   }
	 // 서버로 전송
   websocket.send(msg)
   // 입력값 초기화
   $('#messageInput').val("")
   $('#messageInput').focus()
 }
 
 $(function(){
	 // 버튼을 클릭하든가
	 $('#sendBtn').on('click',function(){
		 
	 })
	 // 입력 후 엔터를 치든가
	 $('#messageInput').on('keydown',function(){
		 if(key.keyCode===13){
			 send()
		 }
	 })
 })
 /*
     <마지막 실행>
      채팅창 출력(chat.do) - connection() 호출 - websocket 연결 - 사용자 입력 (1. 버튼을 클릭 2. 엔터 치기) - send() 호출 - 샌드함수 안에서 메시지 입력 확인
      - websocket.send(msg) - 서버로 전송
      ======================================================================================================================
      서버에서는 접속자 전체 메시지 전송 - onMessage() 메시지 호출 - appendMessage() - 채팅창에 출력됨
 */
</script>
</head>
<body>
  <div class="chat-container">
   <div class="chat-header">실시간 채팅</div>
   <div id="chatBox" class="chat-box"></div> <!--  여기가 채팅 문자열이 들어가는 자리 -->
   <div class="chat-input">
    <input type="text" id="messageInput" placeholder="메시지 입력">
    <button id="sendBtn">전송</button> <!--  여기까지가 실시간 채팅창 틀 -->
   </div>
  </div>
</body>
</html>