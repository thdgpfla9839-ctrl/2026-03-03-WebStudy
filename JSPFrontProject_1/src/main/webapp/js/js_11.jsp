<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
      배열 함수
      => 추가 : push함수 => 데이터를 마지막에 추가할 때 사용 ***
         삭제 : pop => 마지막을 삭제할 때
         개수 : length
         자르기: 자바스크립트에서 페이징할 때 사용 => slice(start,end) ***
         찾기 : find()
         배열 복사 : 스코프 연산자 ... => react
    
     --%>
     
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	
	let names=["쿠로미","망곰이","알렉스","심슨","티니핑"]
	document.write("<h3>원본 배열</h3>")
	document.write("<ul>")
	names.map((name)=>{
		document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names.length+"</li>")
	document.write("</ul>")
	document.write("<hr>")
	
	// 데이터 추가하기
	names.push("을지문덕")
	document.write("<h3>배열 추가 : push </h3>")
	document.write("<ul>")
	names.map((name)=>{
		
		document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names.length+"</li>")
	document.write("</ul>")
	document.write("<hr>")
	
	
	// 배열 데이터 삭제
	// names.shift() => 가장 처음에 있는 데이터 삭제
	// names.pop() => 가장 마지막에 있는 데이터 삭제
	document.write("<h3>배열 삭제 : pop(마지막에 삭제)/shift(처음에 삭제)</h3>")
	names.pop("을지문덕")
	document.write("<ul>")
	names.map((name)=>{
		
		document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names.length+"</li>")
	document.write("</ul>")
	document.write("<hr>")
	
	// 배열 자르기 1
	document.write("<h3>배열 자르기 : slice(start,end) => 새로운 배열 생성</h3>")
	let names2=names.slice(2) // 괄호 안 숫자는 인덱스 번호, 즉 인덱스 2번부터 잘라라
	document.write("<ul>")
	names.map((name)=>{
		
		document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names2.length+"</li>")
	document.write("</ul>")
	document.write("<hr>")

	
	// 배열 자르기 2
	document.write("<h3>배열 자르기 : slice(start,end) => 새로운 배열 생성</h3>")
	let names3=names.slice(1,3) // 1 ~ 3까지 잘라라 , 맨 마지막은 제외가 된다 => 즉, 1 ~ 3-1 = > 1번 부터 2번까지
	document.write("<ul>")
	names.map((name)=>{
		
		document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names3.length+"</li>")
	document.write("</ul>")
	document.write("<hr>")
	
	
	// 배열 복사 ... => 스코프 연산자
	document.write("<h3>복사 ... 스코프 연산자 </h3>")
	let names4=[...names,"스폰지밥"]
	document.write("<ul>")
	names.map((name)=>{
		
		document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names4.length+"</li>")
	document.write("</ul>")
	document.write("<hr>")
}
</script>
</head>
<body>

</body>
</html>