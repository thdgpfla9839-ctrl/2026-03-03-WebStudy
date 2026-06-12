<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>"%>
    <%--
       내장 객체 ) 
       
       1. Number
            parseInt() : 숫자 변환
            toLoacleString() :1,000
          
       2. String
            문자열 인덱스 번호 : 0번 부터 시작 => 자바와 동일
            length() : 문자 개수 => 비밀번호 유효성 검사, 입력이 되었는지에 쓰임
            indexOf / lastIndexOf : 문자 위치 찾기
            replace() : 문자 변경
            replaceAll() : 정규식 변경
            split() : []
            substring(startIndex, endIndex) : 문자를 자르는 경우
             => endIndex는 endIndex-1을 해서 거기까지 구해진다 
             => substr(start,count) : 오라클
                             -----
                                  -> 개수가 들어간다                     
            trim() : 좌우 공백 제거
            startsWith / endsWith / contain  => include
            
        3. Array
             push()  : 데이터 첨부 (마지막에)
             pop()   : 데이터 삭제 (마지막에)
             slice() : 원하는 위치에서 데이터를 잘라서 새로운 배열을 생성      
             length(): 배열에 저장된 데이터 개수
             find()  : 배열 찾기
             
       4. Date : 날짜, 시간 관리
            let today=new Date()
            year : today.getFullYear()
            month: today.getMonth() => 0부터
            date : today.getDate()
            day  : today.getDay() => 요일을 구할 때
            
            => fullcalender를 이용하면 편하다 => 라이브러리
            
       5. Math
            round()
            ceil ()
            
      6. BOM : 브라우저 내장 객체
           window : open / close라는 함수 중요함
           location : href ==> 화면 이동을 도와줌 => sendRedirect()
           history  : back(), go(-1)
           document : 객체모델 선택자 => querySelector(), 화면 출력할 때 사용 => write
           
           => alert
    ========================================================================================== => 전부를 라이브러리화 시킨게 Jquery       
                                
              
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 예약이나 결제일 등록할 때 많이 사용됨
// 댓글 만들 때도 필요함

 window.onload=function(){ 
	
	/* let today=new Date()
	let year=today.getFullYear() // 대소문자 구분 잘 하기
	let month=today.getMonth()+1
	let day=today.getDate()
	let week=today.getDay()
	let strWeek=["일","월","화","수","목","금","토"]
	
	document.write("<h1>오늘은 "+year+"년 "+month+"월 "
			+day+"일 "+strWeek[week]+"요일</h1>") // 일을 구하는 거니까 date가 아니라 day를 더해줘야지 */
			

    // 1. split
    let str='red,black,green,blue,pink,orange'
    let colors=str.split(",")
    let html='<ul>'
    colors.forEach((color)=>{
    	html+='<li>'+color+'</li>'
    	
    })
    
    html+='</ul>'
    document.body.innerHTML=html
			
}
</script>
</head>
<body>

</body>
</html>