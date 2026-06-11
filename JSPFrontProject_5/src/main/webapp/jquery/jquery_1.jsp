<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      주석 추가하기
    
       Jquery : DOM => 태그를 제어하는 라이브러리이다
       ======================================
       태그 제어 
       : 
         1) 태그 선택
            : $(CSS 선택자)
            
         2) 변경
            : 1. 속성 변경
                 => $(태그).attr("속성명","값")
                 => 예) $('img').attr("src","파일명")
                 => 예전버전) let img=document.querySeletor(선택자)
                            img.src=""
                 
              2. 값 변경
                 => input, select, textarea : '$(태그).val()' 이렇게 쓴다
                 => 예전버전) let h=document.querySeletor(선택자)
                            h.value=값
                 => $(태그).text() => 값 읽기
                 => $(태그).text(값) => 값 설정
                 => 예전버전)  let h=document.querySeletor(선택자)
                             h.textContent=값 => text()
                 
              3. HTML 추가
                 => $(태그).html() => html 읽기
                 => $(태그).html('<h1>hello</h1>') 
                 => 예전버전) let h=document.querySeletor(선택자)
                            h.innerHTML=값 => html()
                 
              4. 여러개 추가
                 => 위에 출력 : prepend()
                 => 아래에 출력 : append() => 예전에는 이 모양이었는데 appendChild(태그) 지금은 append()로 바뀜
                 
              5. 스타일 변경
                 => $(태그).css("속성","값")
                    => margin-top => marginTop      
                    => 예전버전) let h=document.querySeletor(선택자)
                               h.style.속성= 값
            
         3) 이벤트
            : 1. 고전적 이벤트 처리 방식
                 => $().click(function(){})
                 => click : keydown, keyup, mousedown, mouseup, mouseover, mouseout, change가 있다
                 => mouseover, mouseout은 hover로 묶어서 쓰기도 한다
                 
              2. jquery4 버전에서 등장하는 리스너 등록 방식
                 => $().on('click',function(){처리기능})
         4) 서버 연결
         5) 효과 / 애니메이션 => Jquery UI를 이용하면 됨    
      =============================================    
      CSS 선택자
      :
        1) id 속성 ==> #id명
        2) class  ==> .class명
        3) tag    ==> tag명
        4) 속성선택자 ==> 태그[속성=값] / 태그[속성*=값] => 포함된 값을 가져와라
        5) 자손 / 후손 ==> 자손 : 태그 > 태그 / 후손 : 태그 태그 => 후손은 공백을 주면됨
        6) 구조선택자  ==> 태그명:eq(0) / 태그명:nth-child(1)    
        
        => 바닐라 js에서 $는 querySelector
           document.querySelector(css 선택자)
            
    
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#btn1').on('click',function(){
		let len=$('input[type=checkbox]:checked').length
		alert("선택된 취미:"+len+"개 입니다")
	})
	$('#btn2').on('click',function(){
		$('input[type=checkbox]').prop('checked',true)
	})
	$('#btn3').on('click',function(){
		$('input[type=checkbox]').prop('checked',false)
	})
})
// prop => property의 약자 => checkbox나 radio에 자주 사용
</script>
</head>
<body>
<%-- 체크박스 만들기 --%>
 <button id="btn1">확인</button>
 <button id="btn2">전체</button>
 <button id="btn3">해제</button>
 <p>
  <input type="checkbox" checked>낚시
  <input type="checkbox" checked>운동
  <input type="checkbox" checked>쇼핑
  <input type="checkbox" checked>등산
  <input type="checkbox" checked>게임
  <%-- 이런 체크박스는 관리자모드에서 많이 사용됨 --%>
 </p>
</body>
</html>