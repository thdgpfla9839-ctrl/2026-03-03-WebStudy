<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    기본문법 => react랑 vue에도 나와서 제대로 알고가자
    1) 변수선언 (let / const)
    2) 연산자 (산술 단항 비교 논리 대입) => 삼항연산자
    3) 제어문 (if / if ~ else / for / for-each / map)
    4) 배열 [] => 자바에서 ArrayList / JSON {} => 자바에서 VO
    5) 함수
    6) 이벤트 처리
    7) 태그 제어 => DOM
    8) 내장 함수 (Number, Math, String, Date)
    9) 브라우저 내장 객체(BOM) => window / location / history / document
    ========================================================================
    자바스크립트 처리
    1) 외부 스크립트 : .js 파일을 만들어서 사용
                    여러개의 파일에서 동시에 사용 (공통 사용)
                    소스가 긴경우
                    <script src="자바스크립트 파일명"> :import
    2) 내부 스크립트 : 파일 한군데서만 작업이 가능함 => 주석 추가
    3) 인라인 스크립트 : 태그 한개 제어
    ========================================================================
    교재는 p.325 
    함수 : function => 자바로 따지면 메소드에 해당
          명령문의 집합 => 명령문 여러개를 모아서 한개의 기능을 생산
          독립적으로 사용 (메소드는 클래스 종속 => 그래서 자바는 클래스를 벗어나면 안 됨)
    명령문의 집합 구성
    : 변수 / 연산 / 제어
    
    구조화된 프로그램 
    : 기능별로 분리
    
    기능처리
    : 로그인 / 목록 / 페이지 .... 등 브라우저 안에서 요청이 있는 경우 처리된다
    
    반복제거, 재사용 가능, 에러처리 쉬움(구분이 잘 돼있음)
    자바스크립트에서는 자바와 다르게 함수가 데이터형으로 인식됨
    => typeof로 처리하면 function으로 처리됨
    => 데이터형은 매개변수로 사용이 사능하다 => CallBack =>함수 안에 함수를 지칭, 자동으로 호출되는 함수이다
    => names.map(function(){})
                 ------------- 이 부분이 콜백함수
                 
    ======================================================================================             
    함수 제작 방법
    
    1. 선언적 함수 :리턴형은 있는데 리턴형을 기재하지 않음
                 매개변수는 변수명만 설정(let이나 const를 붙이지 않는다)
                 가장 많이 사용되는 형식
                 
                 형식)
                        function 함수명(매개변수 ... ) => 선언부
                        {
                           처리문장 => 구현부
                        }
   ========================================================================================                     
    2. 익명의 함수 : 함수 안에 함수를 선언할 수 있다 => 매개변수를 주로 사용
                  let 변수명=function(){}
    3. 화사표 함수 : function과 return 제거가 가능 : =>  이거 사용
                  let 변수명=()=>{}
    4. 콜백 함수  : function 함수명(function(){})
    
    =======================================================================================
    함수의 구성요소
    
    1. 선언부 : 함수명 / 매개변수 => 리턴형을 서술하지 않는다
    2. 구현부 : 변수 선언 / 연산처리 / 제어문
    function 함수명(매개변수...)
    ------------------------- => 선언부
    {
       --------------------------- => 구현부
    }
      
    
    --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

// 선언적 함수
function plus(a,b)
{
	return a+b
	} 
	
// 익명의 함수
let plus2=function(a,b)
{
	return a+b
	}

// 화살표 함수 => return이나 function을 제거할 수 있다	
let plus3=(a,b)=>{return a+b} 
let plus4=(a,b)=> a+b 
/* 
window.onload=function(){
	
	
} */
/* window.onload=()=>{
	
	// 화살표 함수를 이용하는 형식을 권장한다
	
	// ;은 생략이 가능한데 그걸 권장한다
	let a=10
	let b=20
	
	let c=plus1(a,b)
	let d=plus2(a,b)
	let e=plus3(a,b)
	let f=plus4(a,b)
	
	document.write("c="+c+",type="+typeof plus1"<br>")
	document.write("d="+d+",type="+typeof plus2"<br>")
	document.write("e="+e+",type="+typeof plus3"<br>")
	document.write("f="+f+",type="+typeof plus4"<br>")
	
	func(callback)
} */
let callback=function()
{
	document.write("함수 Call ... <br>")
}
function func(call) // 함수가 매개변수로 넘어옴
{
	for(let i=1;i<=10;i++)
		{
		   call()
		
		}
	
	/*
      콜백함수 
	  setTimer(callback) => 지정된 시간에 호출이 가능
	                     => 회원가입, 축하메시지, 자동으로 메인으로 이동
	  setInterval(callback) => 실시간 뉴스나 날짜 읽기
	  이벤트처리 => 주석투가
	
	*/
}
window.onload=function()
{
  let display=()=>{
	  
	  alert("display Call ...")
  }
  display()
}

</script>
</head>
<body>

</body>
</html>