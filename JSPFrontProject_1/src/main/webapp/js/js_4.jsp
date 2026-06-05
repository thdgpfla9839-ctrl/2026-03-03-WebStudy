<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- 논리 연산자 : &&, ||
    
    1) && : 직렬 연산자(한줄로 됨) => 두개의 조건이 true 일 때만 true
    2) || : 병렬 연산자 => 두개의 조건 중 한개 이상이 true 일 때 true
    
    =======================================================
    
   1. 연산처리가 불가능한 경우 : null, 값이 없는 경우
                        => 서버에서 값을 보내거나 / 입력값이 없는 경우
                        => NaN => 산술연산에 많이 등장
    
    
   2. Infinity : 0으로 나눈 경우 나타남
   3. 변수의 초기값이 없는 경우 : undefined => 변수의 값이 없다는 말
   4. 정수 / 정수 = 실수
   5. HTML에 입력값을 가지고 오는 경우 : String => Number(), parseInt()
   6. 단점 : 서버를 연결할 수 없다 => 서버를 연결할 수 있는 라이브러리를 이용하면 된다
                                                -------- => ajax / axios
   7. 같다 : ===
   8. 같지 않다 : !==
   
   --------------------------------------------------------------------
   1 ~ 8을 제외하곤 자바스크립트와 자바는 동일하다                                                
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <script type="text/javascript">
 window.onload=function()
 {
	 // 1. 논리 연산자
	 let i=(6<7) && (6==7)
	 console.log("i="+i) // 결과값 : false
	 
	 let j=(6<7) || (6==7)
	 console.log("j="+j) // 결과값 : true
	 
	 
	 // 2. 대입 연산자
	 //   :  =, +=, -=
		 let k=10
		 k+=10
		 console.log("k="+k) // k = 20
		 k-=10
		 console.log("k="+k) // k = 10
		 
		 
		 // 3. 삼항 연산자
		 let g=(6%2==0)?"짝수":"홀수"
		 console.log("g="+g) // g = 짝수
		 
		 let h=10/0
		 console.log("h="+h) // h = Infinity 0으로 나눠졌다는 뜻
 }
 </script>
</body>
</html>