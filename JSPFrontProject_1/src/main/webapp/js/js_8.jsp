<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    지난 수업 복습 주석)
     변수 : let(변수) / const(상수) => 자동으로 데이터형 설정
     연산자 : ===. !=
          => 데이터형이 다른 경우에는 비교가 불가능
          비교연산자 : 숫자 / 문자 / 날씨
              +   : 문자열 가능  / 덧셈
              /   : 정수 / 정수 = 실수
           제어문   : if / if ~ else
                    반복문   
                    ----
                      일반 for
                      for in : 인덱스 번호
                      for of : 실제 데이터값
                     => 함수화 => Jquery / Vue / React => 배열 / JSon
                        map() : 새로운 배열을 생성
                        forEach() : 일반 반복문
      ===================================================================
      오늘 배울 부분
      
      배열
      객체(JSON)
      DOM = > HTML 태그 제어(CSS 선택자 사용)
      ===================================================================
      배열 : 관련된 데이터를 모아서 관리하는 영역 =>Object형
            형식) 
                [값,값,값.......] => 이 값들은 서로 데이터형이 다를 수 있다
                        
     --%>
     <%--
       1) 배열
       2) 객체 : JSON {}
       3) 함수
       4) DOM => HTML 문서를 자바스크립트가 다룰 수 있도록 객체(Object) 형태로 만든 것
                 원하는 태그를 가지고 온다
                 querySelector()
                 DOMScript ====> Jquery 3 / Jquery 4 
     
     
      --%>
     <%-- 6.8 수업은 여기서 실행 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
 margin-top: 50px;
}
.row{
margin: 0px auto;
width: 800px;
}
</style>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
window.onload=function()
{
    // $(functiuon(){}) : Jquery
    // mounted() : Vue
    // componoentDidMount():  react => hooks => useEffect()
    // 자바 main하고 동일한 함수 => 자동으로 호출되는 함수
    // axios : 서버와 데이터를 주고받기 위한 라이브러리 => response.data로 서버가 보낸 데이터 읽기
    // console.log(response)
    // => F12 → Console에서 데이터 확인

   // axios.get()
  // => F12 → Network → Response에서 서버가 보낸 JSON 데이터 확인
    
    let arr=[];
    axios.get('js_8.do')
         .then(response=>{
        	 console.log(response) // response 안에는 데이터 종류가 많아서 특정 데이터 갖고 오고 싶으면 리스폰스. 어쩌구 적으면 됨
        	 let html=''
        	 let data=response.data
        	 data.map((emp)=>{
        		 html+='<tr>'
        		     +'<td>'+emp.empno+'</td>'
        		     +'<td>'+emp.ename+'</td>'
        		     +'<td>'+emp.job+'</td>'        		 
        		     +'<td>'+emp.dbday+'</td>'
        		     +'<td>'+emp.dname+'</td>'
        		     +'<td>'+emp.loc+'</td>'
        		     +'</tr>'
        	 })
        	 let tbody=document.querySelector("tbody")
        	 
        	 // DOM => HTML 문서를 자바스크립트가 다룰 수 있도록 객체(Object) 형태로 만든 것
        	          // 원하는 태그를 가지고 온다 : querySelector()
        	 tbody.innerHTML=html
        	 
        	 
        	 /*
    	     1. document.write() 
    	     2. alert() 
    	     3. innerHTML => 지정된 태그에 값을 첨부 
    	        Jquery => html() 
    	     4. VueJS는 태그에서 처리 => 값을 서버에서 받으면 자동으로 HTML 적용(양방향)
    	        React는 HTML을 만들어서 추가 : 단방향 
    	        
    	     1) 배열 [] 
    	     2) 객체 : JSON {}
             3) 함수 
             4) DOM => 원하는 태그를 가지고 온다 
                querySelector("tbody") 
             5) 이벤트 처리 방식
             ------------------------------
             기타 
               = 내장 함수 / 브라우저 내장 함수 
             -----------------------------라이브러리화 : Jquery 
            	   
    	 */
         })
}

</script>
</head>
<body>
<div class="container">
 <div class="row">
  <h3 class="text-center">사원목록</h3>
   <table class="table">
    <thead>
     <tr class="danger">
      <th>사번</th>
      <th>이름</th>
      <th>직위</th>
      <th>입사일</th>
      <th>부서명</th>
      <th>근무지</th>
     </tr>
    </thead>
     <tbody>
     
     </tbody> 
   </table>
 </div>
</div>
</body>
</html>