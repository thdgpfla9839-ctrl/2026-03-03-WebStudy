<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
        [] : 서버에서는 "ArrayList"라고함 => 이걸 변경하면 JSONArray
        {} : 서버에서는 "VO" => 이걸 변경하면 JSONObject
        
        =========================================================
        Objrct[] = arry ={"" ..... }; 
        오브젝트 단위로 배열을 잡으면 자바에서도 실행이 가능하다
          => 다만 자바에서는 데이터형이 다 다르다 보니 for문을 못돌려
          
          하지만 자바스크립트는 let 변수 하나로 받으니 for문을 돌리면됨 => 그래서 일반 for문 돌리면 된다
        =============================================================================
        객체
           {키:값} => 숫자는 그냥 사용 / 문자열은 "", ''
          => 키는 멤버변수로 사용
          => 키의 개수가 동일하지 않을 수 있다
          => 자바와 자바스크립트를 연동할 때 많이 사용
          => 추가로 JSON은 데이터를 전송시에 주로 사용
          => 서버는 브라우저, 모바일 둘다 전송이 가능 !! 이부분 추가 정보 찾아서  주석달기 @
          
       사용법
          let sawon = {"sabun":1, "name":"홍길동"};
          sawon.sabun
          sawon.name
         => 꼭 .을 찍어줘야 한다     
    
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=()=>{
	/*
	   자바에서는 
	   class Sawon
       {
	      int sabun;
	      String name,job;
       }
      # Sawon sawon=new Sawon()
       sawon.sabun=1
       sawon.name="홍길동"
       sawon.job="사원" #까지는 const ~ 이 부분을 표현한 것
	   	*/
	const sawon={"sabun":1,"name":"홍길동","job":"사원"}; // 객체 표현법
	
	//1ROW에 해당하는 부분
	document.write("<h1>자바스크립트의 객체 표현법 => 이게 JSON </h1>")
	document.write("사번:"+sawon.sabun+"<p>")
	document.write("이름:"+sawon.name+"<p>")
	document.write("직위:"+sawon.job+"<p>")
	document.write("<hr>")
	
	
	// 이렇게도 출력해도 값이 동일함 (객체 뒤 배열형식에 키를 넣어줌)
	document.write("<h1>자바스크립트의 객체 표현법 => 이게 JSON </h1>")
	document.write("사번:"+sawon['sabun']+"<p>")
	document.write("이름:"+sawon['name']+"<p>")
	document.write("직위:"+sawon['job']+"<p>")
	document.write("<hr>")
	// 객체 배열 => 배열의 함수
	
	
}
</script>
</head>
<body>

</body>
</html>