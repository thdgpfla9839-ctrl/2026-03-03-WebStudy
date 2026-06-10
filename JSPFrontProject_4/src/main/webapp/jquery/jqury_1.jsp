<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      
     Jquery 3에서 4로 넘어감 : 변화가 많지는 않음(제거랑 추가 정도)
            1. 라이브러리가 작아짐 => 대신 실행의 속도가 최적화됨
            2. 자바스크립트 라이브러리가 => 프레임워크 형식으로 변경(소스가 통일화됨) 
                                                        ----------- => 이게 되면 교육을 최소화할 수 있음(인수인계같은)
                                                        
           과거에는 스프링의 마이바티스 기반의 jqeury를 사용
           현재는 스프링부트의 jpa에서 react 사용
           
   ============================================================================================================                                                     
     jquery
           => 태그, 속성을 제어하는 프로그램 : DOM(문서 객체 모델)
           => 속성 조작 / 문자 조작 / 스타일 조작 / 이벤트 가능을 하나로 통일 시킨게 jquery
           => document.querySelector("#btn")
           => document.querySelectorAll("#btn")
           => document.getElementById()
           => document.getElementsByClassName()
           => document.getElementByTagName(
           ---------------------------------------- '$'로 통일
           $('CSS 선택자') : 모든 태그 읽기
           
           조작)
           1. 문자 조작 :<b>변경대상</b>
               textContent  / innerHTML
                  |              |
               text()           html()
               
           2. 스타일 조작
               태그.style.속성명 => css()  
               btn.style.color="red"
               btn.style.backgroundColor="yellow"
               
               $("#btn").css("color","red")
                        .css("backgroundColor","yellow")
               => 3버전 => 비효율적
               
               ================================
               4버전을 권장
               => css({
                          "color":"red,
                          "backgroundColor":"yellow"
                         })
               
              =================================
              
           3. 속성 조작
              <img src="">
              let img=document.querySelector("img")
              img.src=""
           
              = > $('img').attr("src","")
                  위에 코드가 이렇게 $() ~ 로 바뀜   
                  
             ========================================     
             값읽기
              <input type=text value="aaa">
               let input=document.querySelector("input")
               input.value 
                   
                $('input').val()
                   
             
             => 얘네는 getter / setter로 읽는다
             
             [getter / setter]
                 text() : getter로 읽는다 => text("aaa") : setter로 읽는다 
                 attr() : attr("src")  =>  attr("src","")
                 val()  : val(), val("")
                 html() : html(), html("")
              => 매개변수가 있으면 setter / 없으면 getter로 읽는다 
              
            ===============================================================
            이벤트 처리방식
                 <img>
                      => let img=querySelector("img")
                         img.addEventListener('click',function(){})
                       1)  $('img').on('click',function(){})
                       2)  $('img').click(function(){})
           =================================================================
           기타
               서버와 연결     
                   ajax => $.ajax({})
                   => Vue / React : fetch / axios
                   
          ==================================================================
          Jquery는 버전이 충돌되면 작동이 안 된다
          프로그램을 만들면 include를 이용하는데 main.jsp에서 자바스크립트 라이브러리 추가                    
               
               
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script> <!--   Jquery 3.0 버전 -->
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-4.0.0.min.js"></script>  4.0 버전 -->
<script type="text/javascript">
// 3버전 4버전이 동일함 => $(function({}))
$(function(){
	
	// 1. 스타일 조작
	$('#h1').css("color","yellow") // $는 querySelector에 해당된다
	                              //  style -> css()로 바뀐거    
    $('.h1').css("backgroundColor","cyan") // 클래스  	                              
    $('#h2').css("backgroundColor","red")  	                              
    $('#h3').css("backgroundColor","blue")  	
    
    
    // 태그($)를 선택하면서 스타일 조작하는 중(css())
    $('h2:eq(0)').css("color","magenta") // 첫번째부터 값을 가져올 때는 eq(0)를 사용한다
                 .css("backgrwoundColor","black") // css를 여러개 줄 땐 지금처럼 나열하면 됨
    $('h2:eq(1)').css({
    	"color":"pink",
    	"backgroundColor":"blue" // 이렇게도 css 여러개 줄 때 블록을 줘서 처리할 수도 있음 => 4버전에서는 이 형태를 권장함
    })
    // 4버전은 $('h2').eq(1)
    $('h2:eq(2)').css("color","green")
    $('h2:eq(3)').css("color","gray")
    $('h2:eq(4)').css("color","cyan")
    
    // 스타일 조작 구버전
   /*  let h3s=document.querySelectorAll("h3")
    for(let h3 of h3s)
    	{
    	    h3.style.backgrwoundColor='blue'
    	    h3.style.color="white"
    	} */
    	
    // 스타일 조작 신버전
    $('h3').css({
    	"backgroundColor":"blue",
    	"color":"white"
    })
	                              
	$('img').css({
		"width":"160px",
		"height":"230px"
	})
	
	$('img').hover(function(){
		$(this).css({ // 여러개 중에 선택된 걸 찾을 때 'this'라고 한다, 이벤트가 되는 대상을 찾을 때 'this'
			"cursor":"pointer",
			"border":"3px solid green"
		})
	},function(){
		$(this).css({
			"cursor":"none",
			"border":"none"
		})	
	})
	   // this : 이벤트에 해당 되는 자신 , 여러개가 있을 때만 사용이 가능
	  
})  // 여기까지 스타일 조작에 대한 정리
</script>
</head>
<body>
  <h1 id="h1">헬로우 제이쿼리</h1>
  <h1 class="h1">헬로우 제이쿼리</h1>
  <h1 class="h1">헬로우 제이쿼리</h1>
  <h1 id="h2">헬로우 제이쿼리</h1>
  <h1 id="h3">헬로우 제이쿼리</h1>
  <h2>헬로우 제이쿼리</h2>
  <h2>헬로우 제이쿼리</h2>
  <h2>헬로우 제이쿼리</h2>
  <h2>헬로우 제이쿼리</h2>
  <h2>헬로우 제이쿼리</h2>
  <h3>하이 제이쿼리</h3>
  <h3>하이 제이쿼리</h3>
  <h3>하이 제이쿼리</h3>
  <h3>하이 제이쿼리</h3>
  <img src="https://img.megabox.co.kr/SharedImg/2026/05/13/zlk6dycnwuPdVuSH40RVickv2CYYCrcU_420.jpg">
  <img src="https://img.megabox.co.kr/SharedImg/2026/05/19/Xya2c40b4Yck7jNWa2l0NWUpIuoZkPAV_420.jpg">
  <img src="https://img.megabox.co.kr/SharedImg/2026/05/21/ysq28y1C9Y3yJdnIIwb3kacb7Sx6omLV_420.jpg">
  <img src="https://img.megabox.co.kr/SharedImg/2026/06/09/KohW1eBmK8OyASrtmtTWAK51aqk448re_420.jpg">
  <img src="https://img.megabox.co.kr/SharedImg/2026/05/28/EWuCHtLbifaow6OzleUI5qz6VczYpjxS_420.jpg">
</body>
</html>