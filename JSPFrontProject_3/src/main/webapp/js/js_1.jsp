<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
   자바스크립트 공부해야 할 부분
   1. 자바스크립트 기본 문법 
   2. 태그 선택(브라우저 안에서 태그를 제어, 추가, 삭제, 데이터 수정 => '동적 페이지' 라고 함 => 페이지 변경 없이 그 자리에서 변경이 가능함)
   
   jsp ======= 서버
                |
              새로운 jsp
  => jsp에서 서버를 거치면 새로운 jsp가됨   
  
  ==============================================================================================================
  1. 태그를 가져오려면 선택자가 필요함 => CSS 선택자
       <button id="btn"> => #btn 
          ------------------------------- 단일  document.querySelector("#btn")
          <button class="btn"> => .btn
          <button>             => btn 
          ------------------------------- 다중  document.querySelectorAll(".btn")
     단일로 가져올때가 document. ~
     다중으로 가져올 때는 반드시 ALL을 붙이고
     
  2. 자손 / 후손
         <div>
            <p></p>  ==> div > p => <p>를 가져올 때 
            <span>
              <p></p> ==> div span p => <p>를 가져올 때 
            </span>
          </div>
       
    
  3. 기타
     속성 선택자 : <input type="text"> ==> input[type="text"]
     구조 선택자 : nth-child(1) ....  nth-child(2n) nth-child(2n+1)
                => 2n+1은 홀수 가져올 때
  ===============================================================================================================
  자바스크립트에서 지원하는 객체모델
  1. id 속성
     : document.getElementById("아이디명")
  2. class 속성
     : document.getElementsClassName("클래스명")
  3. name 속성
     : document.getElementsByName("name속 성명")
       <button name="btn">
  4. tag명 읽기
     : document.getElementByTagName("태그명")
  5. css 선택자 이용
     : document.querySelector(css 선택자)
     : document.querySelectorAll(css 선택자)
                                 ---------
                               => 아이디인 경우에는 #을 붙인다
                               => 클래스인 경우에는 .
                               => 태그>태그 
                               => 태그 태그 
                               => 태그[속성=값]
                               
 => 위 다섯가지를 모아서 '객체모델'이라고 부른다
 
 ===================================================================================================================
 1. 문자 조작
    : textContent : 순수하게 문자열만 출력 => HTML파싱이 안된다
      innerHTML : HTML을 파싱이 가능하다 
      
 2. 스타일 조작
    : 태그.style.속성=값
      예) 태그.style.color="red"
      예) 태그.style.display='' ==> 화면 출력할 때 사용 => show
      예) 태그.style.display='none' ==> 화면 감추기할 때 => hide
      
 3. 속성 조작   
   : img.src=""
     a.href=""
     
====================================================================================================================
언제 제어하는지에 관한 => 이벤트
1. 고전적 이벤트
   : btn.onclick(function(){})
   
2. 일반 이벤트
   : <button onclick="처리함수 호출"> ==> Vue / React에서도 이용
   
3. Listener 이용           
  : btn.addEventListener('click',function(){}) ==> Jqeury에서도 이용
     
--%>
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
.row {
  margin: 0px auto;
  width: 350px;
}
h1 {
	
	text-align: center
}
</style>
<script type="text/javascript">
function gesan()
{
   let kor=document.getElementById("kor")
   if(kor.value==="")
	  {
	   kor.focus()
	   return
	  }
   let eng=document.getElementById("eng")
   if(eng.value==="")
	  {
	   eng.focus()
	   return
	  }
   let math=document.getElementById("math")
   if(math.value==="")
	  {
	   math.focus()
	   return
	  }
   
   
   // 계산
   let total=document.getElementById("total")
   let hap=Number(kor.value)+parseInt(eng.value)+parseInt(math.value)
   // 웹에서의 데이터는 String이므로 숫자형 변환 Number(), parseInt()를 해준다
   
   total.value=hap
   
   let av=hap/3
   let avg=document.getElementById("avg")
   avg.value=Math.round(av)
   
   let score='A'
   if(av>=90)
	   score='A'
	   else if(av>=80)
		   score='B'
		   
	   else if(av>=70)
		   score='C'
		   
	   else if(av>=60)
		   score='D'
		   
	   else
		   score='F'
		document.getElementById('score').value=score   // 여기까지가 객체 모델 중 아이디 속성 가져올 때
		   
}
window.onload=()=>{
	
	// 태그 읽기
	let h2=document.querySelectorAll("h2") // 배열로 들어간다
	for(let i of h2) // 태그가 여러개 일 때는 for문을 돌려서 처리한다
		{
		  i.style.backgroundColor='green' // 스타일조작
		  i.textContent="Hello JavaScript" // 문자조작
		}
	
	// 
	let acc=document.querySelector("#account")
	acc.addEventListener('change',function(){
		let val=acc.value
		let price=document.querySelector("#price")
		let total=Number(val)*Number(price.textContent) // val = value
		document.querySelector('#sum').innerHTML='<font color=red>'+total+'원 </font>' // change 이벤트
	})
}
</script>
</head>
<body>
  <div class="container">
   <div class="row">
    <h1>성적 계산</h1>
     <table class="table">
      <tr>
       <th width="35%" class="text-center">국어</th>
        <td width="65%">
         <input type="text" class="input-sm" id="kor" size="15">
        </td>  
      </tr>    
      <tr>
       <th width="35%" class="text-center">영어</th>
        <td width="65%">
         <input type="text" class="input-sm" id="eng" size="15">
        </td>  
      </tr>    
      <tr>
       <th width="35%" class="text-center">수학</th>
        <td width="65%">
         <input type="text" class="input-sm" id="math" size="15">
        </td>  
      </tr>    
      <tr>
         <td colspan="2" class="text-center">
         <input type="button" class="btn" onclick="gesan()" 
         class="btn-sm btn-primary" value="계산">
        </td>  
      </tr>    
     </table>
      <h1>처리결과</h1>
      <table class="table">
       <tr>
        <th width="35%" class="text-center">총점</th>
        <td width="65%">
         <input type="text" class="input-sm" id="total"
         size="15" readonly>
        </td>
       </tr>
       <tr>
        <th width="35%" class="text-center">평균</th>
        <td width="65%">
         <input type="text" class="input-sm" id="avg"
         size="15" readonly>
        </td>
       </tr>
       <tr>
        <th width="35%" class="text-center">학점</th>
        <td width="65%">
         <input type="text" class="input-sm" id="score"
         size="15" readonly>
        </td>
       </tr>
      </table>
   </div>
   <div class="row" style="margin-top: 20px">
    <h2>Hello DOM-1</h2>
    <h2>Hello DOM-2</h2>
    <h2>Hello DOM-3</h2>
    <h2>Hello DOM-4</h2>
    <h2>Hello DOM-5</h2>
   </div>
   <div class="row" style="margin-top: 20px">
    <table class="table">
     <tr>
      <td>
      수량:<select id=""account>
      <%-- account => value
           input => value
           select => value
           textarea => value
       --%>
          <option value="1">1개</option>
          <option value="2">2개</option>
          <option value="3">3개</option>
          <option value="4">4개</option>
          <option value="5">5개</option>
      </select>
      </td>
     </tr>
     <tr>
     <%-- 가격은 textContent로 읽어와야 한다 --%>
      <td>가격:<span id="price">3000</span></td>
     </tr>
     <tr>
      <td>총금액:<span id="sum"></span></td>
     </tr>
    </table>
   </div>
  </div>
</body>
</html>