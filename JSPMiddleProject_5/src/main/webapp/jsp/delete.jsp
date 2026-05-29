<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
</head>
<body>
 <div class="container">
  <div class="row" style="width: 300px"> 
   <h3>삭제하기</h3>
    <%--
          현재 파일에서 처리 : Ajax / Vue / React는 화면 변경없이 그자리에서 처리가 가능하다
                          => 예) 로그인 처리 / 로그아웃 / 검색 / 페이지 변경 / 추천 / 결제
     --%>
    <form mathod=post action="delete_ok.jsp">
    <table class="table">
     <tr>
      <td class="text-center">
       비밀번호:<input type="password" name=pwd size=15 class="inout">
        <input type="hidden" name=no value="${param.no }">
        <%--
           ${param.no }
                   --
           delete.jsp?no=${vo.no }
                      --
           request.getParameter("no") => key 구분, 키값은 동일해야 한다
                                 --
         --%>
      </td>
     </tr>
      <tr>
       <td class="text-center">
         <button class="btn-sm btn-warning">삭제</button>
         <button class="btn-sm btn-warning" 
           type="button" onclick="javascript:history.back()">취소</button>
       </td>
       <%--
           자바스크립트는 브라우저를 제어할 수 있다
             => 출력에 필요한 데이터가 필요함
             => Back-End를 자바스크립트로 제어
             
            javascript:histrory.go(-1) => forward(1)
            vue / react => nav(-1)
            
        --%>
      </tr>  
    </table>
    </form>
  </div>
 </div>
</body>
</html>