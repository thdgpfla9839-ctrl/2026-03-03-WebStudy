<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
              반복문)
              do ~ while : 반드시 한번이상 수행할 때 사용하는 방식 => 조건을 나중에 검색한다
              while : 반복 횟수가 없는 경우에 사용한다
              for : 반복 횟수가 지정이 됐을 때 사용한다
              
         ===========================================================================
           1. do ~ while
              => 초기값 === 첫번쨰로 수행
                   do
                   {
                       반복문장 ==== 2
                       증가식  ==== 3
                   }
                   while(조건문) ==== 4     
       
       =============================================================================
         2. while
            => 초기값  ===== 1
               while(조건문) == 2
            {
               반복문장 ===== 3
               증가식  ====== 4   2번이 풜스면 종료
            }
            
    ================================================================================
      3. for
         => for(초기값;조건식;증가식)
           {     1    2     4
              반복 수행문장 === 3 순으로 실행
           }
         
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function()
{
	// do ~ while
   let i=1
   do
	   {
	     document.write("<h3>"+i+"</h3>"); // 조건이랑은 상관없이 무조건 한번은 여기가 수행이 된다
	     i++;
	   }
   while(i<=10)
	   document.write("<hr>")
	   
	   // while
	   i=1
	   while(i<=10)
		   {
		     document.write("<h3>"+i+"</h3>");
		     i++;
		   }
   
   document.write("<hr>")
   
   // for
   for(i=1;i<=10;i++)
	   {
	      document.write("<h3>"+i+"</h3>");
	   }
}
</script>
</head>
<body>

</body>
</html>