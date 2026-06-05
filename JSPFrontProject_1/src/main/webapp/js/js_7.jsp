-<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    
        일반 for문 => 페이징 처리할 떄 사용

         => for(초기값;조건식;증가식)
           {     1    2     4
              반복 수행문장 === 3 순으로 실행
              => 이 안에서 변수는 const는 불가능 왜냐면 증가를 못해 그래서 let변수만 사용
           }
            2번이 풜스면 종료된다
        
        ===============================================================
        데이터가 집합일 경우 : '배열 / 객체'
        => for-each를 이용한다
        for(BoardVO vo:list)
        => 목록 출력할 때 사용됨
        =================================================================
        1. for in
           => for(변수 in 배열)
           {
               배열의 인데스 번호를 가지고 온다
           }    
          => 배열 여러개를 동시에 출력할 때 사용한다 
          
       ===================================================================
       2. for of
          => for(변수 of 배열)
            {
               배열 안에 있는 실제 데이터를 가지고 온다
            }
            
      =====================================================================
      3. forEach
         => 배열.forEach(function(변수){}) => 배열과 변수에 값을 넣어주는 것을 반복 / 변수가 데이터값을 받아오는 위치 = 자동 호출 되는 함수     
         
     ===============================================================================================================
     4. map 
        => 배열.map(function(변수){}) => forEach구문과 형식이 동일함    
             
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=()=> // function은 생략하고 =>를 써줄 수 있다
{
   let names=["홍","김","이","박"]
   let sexs=["남","여","여","남","여"]
   
   document.write("<h1>회원목록</h1>")
   document.write("<ul>")
   for(let index in names) // 인덱스 번호를 가져오는 것이니 0부터 ~ 3까지 가져오겠지
	   {
	      document.write("<li>"+names[index]+"("+sexs[index]+")</li>")
	   }
   document.write("</ul>")
   document.write("<hr>")
   
   document.write("<h1>회원 이름</h1>")
   for(let name of names) // names라는 배열 안에서 name을 하나씩 읽어온다
	   {
	     document.write("<h3>"+name+"</h3>")
	   }
   
   document.write("<hr>")
   
   document.write("<h1>회원 이름</h1>")
   names.forEach(function(name){
	  document.write("<h3>"+name+"</h3>")  
   })
   document.write("<hr>")
   
   document.write("<h1>회원 이름</h1>")
   names.map(function(name,index){
	  document.write("<h3>"+(index+1)+"."+name+"</h3>")  
   })
   document.write("<hr>")
   
   document.write("<h1>회원 이름(forEach)</h1>")
   names.forEach((name)=>{
	  document.write("<h3>"+name+"</h3>")  
   })
   document.write("<hr>")
   
   document.write("<h1>회원 이름</h1>")
   names.map((name,index)=>{
	  document.write("<h3>"+(index+1)+"."+name+"</h3>")  
   })
}
</script>
</head>
<body>

</body>
</html>