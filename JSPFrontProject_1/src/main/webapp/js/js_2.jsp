<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
       자바 스크립트에서 지원하는 연산자 배우기
       
       1. 단항 연산자 : 증감연산자(++, --) / 부정연산자(!) 
                     형변환 연산자 (Number(), parseInt(), Boolean(), String())
                     => 주의할 점 자바처럼 형변환 할 떄 let a=(int)10.5 이렇게 사용하는 거 아니야
                     
       2. 이항 연산자 : 산술연산자(+, -, *, /, % ) / 비교연산자(==, !=, <, >, <=, >=)
                     => 추가로 ===, !== 은 데이터형이 같은 경우에 사용한다
                     논리연산자(&&, || )
                     대입연산자(=, +=, -=, *= ... )
                     
       3. 삼항 연산자 : 조건 ? 값1 :  값2
                     true = 값1
                     false = 값2 ==> if ~ else
    
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
 window.onload=function()
 {
	 // 1. 단항 연산자 
	 // 1-1. 증감 연산자 => 1개 증사, 1개 감소 되는 형식
	 let a=10 // 선언한 변수와 출력하려는 변수가 틀리니 오류가 났았어 주의하기
	 document.write("<h1> 증가전:"+a+"</h1>")
	 a++
	 document.write("<h1> 증가후:"+a+"</h1>")
	 document.write("<h1> 감소전:"+a+"</h1>")
	 a--
	 document.write("<h1> 감소후:"+a+"</h1>") // 태그 괄호 위치 잘 봐야해 위치 틀려서 오류 났었어
	 document.write("<hr>")
	 
	 a=10
	 b=a++
	 document.write("<h1> a="+a+"</h1>")
	 document.write("<h1> b="+b+"</h1>")
	 document.write("<hr>")
	 // document 브라우저에서 실행될 떄 출력하는 하얀 네모 영역이 document이다, 즉 화면을 출력하는 브라우저 영역이다
	 
	 let c=true
	 document.write("c="+c+"<br>")
	 c=!c
	 document.write("c="+c+"<br>")
	 
	 c=1 // 0이나 0.0이 아닌 수는 true
	 document.write("c="+c+"<br>")
	 c=!c // true / false를 출력하는 연산자는 자동으로 불린으로 변경된다
	 document.write("c="+c+"<br>")
	 
	 document.write("<hr>")
	 let a1=10
	 document.write("a1="+a1+",type="+typeof a1)
	 a1=String(a1) // String.valueOf()
	 document.write("a1="+a1+",type="+typeof a1)
	 
	 // a1=parseint(a1)
	 a1=Number(a1)
     document.write("a1="+a1+",type="+typeof a1)
	 document.write("<hr>")
	 document.write("Boolean(1)="+Boolean(1)+",type="+typeof Boolean(1)+"<p>")
	 document.write("Boolean(0)="+Boolean(0)+",type="+typeof Boolean(0)+"<p>")
	 document.write("Boolean(0.0)="+Boolean(0,0)+",type="+typeof Boolean(0.0)+"<p>")
	 document.write("Boolean(0.1)="+Boolean(0,1)+",type="+typeof Boolean(0.1)+"<p>")
	 document.write("Boolean('헬로 자바스크립트')="+Boolean('헬로 자바스크립트')+",type="+typeof Boolean('헬로 자바스크립트')+"<p>")
	 document.write("Boolean('')="+Boolean('')+",type="+typeof Boolean('')+"<p>")
	 document.write("Boolean(\'\')="+Boolean("")+",type="+typeof Boolean("")+"<p>")

	 // => 특별한 경우가 아니라면 일반 데이터형을 사용한다
	 
	 

     
	 
	 
	 // 1-2. 부정 연산자
	 // 1-3. 형변환 연산자
	 
 }
function calc() 
{
   let f=document.getElementById("first").value	
   let s=document.getElementById("second").value
   console.log("f="+f+",type="+typeof f)
   console.log("s="+s+",type="+typeof s)
   let span=document.getElementById("result")
   span.innerHTML="<h2>"+(Number(f)+Number(s))+"</h2>" // 형변환 시켜봄, 넘버 대신에 parseInt(f)+parseInt(s)로 형변환 시킬 수도 있음
   // innerHTML :  태그와 태그 사이에 값을 집어 넣을 때 사용, 서버에서 값을 받아서 원하는 위치에 값을 출력하는 것
}
</script>
</head>
<body>
  첫번째 값: <input type=text id="first" size=10><br>
  두번째 값: <input type=text id="second" size=10><br>
  <input type=button value="계산" onclick="calc()"><br>
  <span id="result"></span>
</body>
</html>