<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
             제어문)
             1. 조건문 => 유효성 검사 / 정규식에 많이 등장
                : 단일조건문 ** 
                  => if(조건문)
                  {
                    조건문은 부정 연산자 / 비교 연산자 / 논리 연산자 + 알파로 더 쓸 수 있다 
                    조건이 트루일 때 실행
                    펄스일 경우 건너뛴다
                  }
                  
                  선택조건문 **
                  =>  if(조건문)
                  {
                      조건이 트루일 때 수행하는 문장
                  }
                  else
                  {
                     조건이 풜스일 때 수행하는 문장
                  }
                  => 삼항연산자와 동일 
                  
                  다중조건문
                  => if(조건문)
                     {
                       조건이 트루이면 수행문장 종료
                            풜스면 다른 조건문으로 내려간다
                     }
                     else if(조건문)
                     {
                       조건이 트루이면 수행문장 종료
                            풜스면 다른 조건문으로 내려간다
                     }
                     else if(조건문)
                     {
                       조건이 트루이면 수행문장 종료
                            풜스면 다른 조건문으로 내려간다
                     }
                     ....
                     else
                     {
                       해당 조건이 없는 경우
                     }
                     
                  선택문
                  => switch(number 혹은 문자)
                  {
                     case 값:
                        처리문장
                        break;  => 여기서 종료하나 만약 브레이크가 없다면 밑에 문장 수행
                     case 값:
                        처리문장
                        break; => 브레이크가 있을 때까지 밑으로 내려가서 수행함
                      ..
                      ..
                      default:
                       처리문장
                     
                  }
                  
                  
             2. 반복문
                : do ~ while문
                  while문
                ** 일반 for문 => for in
                               for of **
                               forEach **
                               map **
             3. 반복 제어문
                : break **
                  continue
    
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/axios/dist/axios.min.js" ></script>
<script type="text/javascript">
window.onload=function()
   
{
	/* movieList(1) */
	
	let i=3;
	
	switch(i)
	{
	case 1:
		document.write("<h1>문장1</h1>")
	case 2:
		document.write("<h1>문장2</h1>")
	case 3:
		document.write("<h1>문장3</h1>")	
	case 4:
		document.write("<h1>문장4</h1>")
	case 5:
		document.write("<h1>문장5</h1>")
		break
	}
	
	/* // 1. 단일 조건문
	  let a=10
	  let b=5
     	if(a+b) 
		{
		   document.write("정상 수행")
		} */
}

// 리턴형은 없고 매개변수는 변수명만 사용이 가능함

/*
 * searchMainDailyBoxOffice.do
   searchMainRealTicket.do
   searchMainDailySeatTicket.do
 */
function movieList(no) 
{
  	let site="https://www.kobis.or.kr/kobis/business/main/";
  	if(no===1)
  		site+="searchMainDailyBoxOffice.do"
  		
   if(no===2)
	   site+="searchMainRealTicket.do"
	   
   if(no===3)
	   site+="searchMainDailySeatTicket.do"
	   
	   
  axios.get(site).then(response=>{
	  // 사이트를 연결해서 실행한 화면 가져오기
	  
	  console.log(response.data)
	  // 이렇게만 하면 포트번호가 안 맞아서 안됨 그래서 우리 자바 서버를 연결해줘야 함
  })
  
}
</script>
</head>
<body>

</body>
</html>