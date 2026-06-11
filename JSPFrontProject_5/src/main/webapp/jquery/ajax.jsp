<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	let httpRequest = null
	// $.ajax({}) 이거면 끝나는데 이해 안 될까바 자바로 먼저 연습하는 거야
	function getXMLHttpRequest() {
		// 브라우저 내장 => MLHttpRequest => 서버 => 처리 결과를 읽어오는 역할
		// 바닐라 js
		if (window.XMLHttpRequest) // null이 아니면 브라우저에 존재
		{
			alert("XMLHttpRequest 생성") // 이거 먼저 띄워야함
			return new XMLHttpRequest() // 크롬이나 FF에서 처리할 예정
		} else {
			return null;
		}
	}

	// 필요한 내용 서버에 전송 => 수신까지 받기 => $({method:...})
	// 이 함수가 만들어 지는게 ajax
	function sendRequest(url, params, callback, method) {
		// 여기서 send ~로 호출하니까 list.do라든지 ~ 'GET' 이런식으로 호출될거야
		// 1. 객체 생성
		httpRequest = getXMLHttpRequest()

		// 2. 메소드 방식을 지정
		let httpMethod = method ? method : 'GET'// 메소드의 값이 널이면 겟방식을 쓰겠다고 디폴트로 겟방식 잡아놓음
		if (httpMethod != 'GET' && httpMethod != 'POST') {
			httpMethod = 'GET'
		}

		// 3. params 처리 => ?id=admin이 params이다
		let httpParams = (params === null || params === "") ? null : params

		// 4. url 변경
		let httpUrl = url

		// 4-1. GET이면 => GET방식일 때만 ?를 붙인다
		if (httpMethod === 'GET' && httpParams != null) {
			httpUrl = httpUrl + "?" + httpParams
		}

		// 4-2. POST이면

		// 5. 서버와 연결
		httpRequest.open(httpMethod, httpUrl, true)
		// open()은 이미 제작됨 => 서버 연결 시 사용함
		// true는 Async을 의미 => 비동기적 연결

		// 6. 데이터 전송
		// 문제 발생 : 한글 처리를 해줘야함
		httpRequest.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded")

		// 7. 결과값을 읽어오는 함수 지정 => 자동호출이 되게끔 => callback 
		httpRequest.onreadystatechange = callback
		// 8. 데이터 전송
		httpRequest.send(httpMethod === 'POST' ? httpParams : null)

		// 9. 값 읽어오기 방법이 두가지가 있음 => responseText / responseXML(JSON) 

	}
	function send() {

		sendRequest('sub.jsp', null, ok, 'POST') // jsp 파일을 연결, 보낼값은 null , 가져올 건 ok함수?
	}
	//callback 함수 => 자동호출
	/*
	 * readystate => 0부터 시작 => 서버 연결 준비
	 1        => 서버 연결 => open() 
	 2        => 서버 연결 완료
	 3        => 데이터 전송 준비 => send()
	 4        => 데이터 전송 완료
	
	
	
	 200      => 서버에서 정상 수행 
	 */
	function ok() {

		if (httpRequest.readyState === 4) {
			if (httpRequest.status === 200) {
				//alert(httpRequest.responseText)
				let div = document.querySelector("#print")
				div.innerHTML = httpRequest.responseText
			}
		}

	}
</script>
</head>
<body>
	<button onclick="send()">전송</button>
	<div id="print"></div>
</body>
</html>