<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- main2는 웹 페이지 헤더 / 화면 / 푸터 나누는 연습하는 부분 --%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Company Name</title>
<link rel="stylesheet" type="text/css" href="../style/style_sheet.css"
	media="screen" />
</head>

<body>

	<div id="wrapper">
		<!--####  wrapper : 헤더, 본문, 푸터를 모두 감싸는 가장 큰틀  ###-->


		<%-- header --%>
		<jsp:include page="header.jsp"></jsp:include>

		<%-- 화면 --%>
		<jsp:include page="home.jsp"></jsp:include>
		
		<%-- footer --%>
		<jsp:include page="footer.jsp"></jsp:include>
		




		<div class="clr"></div>
	</div>
	<!--####  wrapper  ###-->

</body>
</html>
