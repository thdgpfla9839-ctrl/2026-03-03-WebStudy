<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- 
            브라우저 : url을 이용해서 요청 
                    브라우저 자체에서 처리 => 자바스크립트 이용
                    
                    
                    |
                  서버로 이동
                  Controller : delivery의 역할(요청을 받아서 전송하는 역할을 함)
                    |
                  Model 중 @리퀘스트맵핑을 찾아서 메소드 호출 
                    |
                  리퀘스트를 받음
                    |
                  jsp로 전송 
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
  width: 800px;
}

</style>
</head>
<body>
 <div class="container">
  <div class="row">
   <table class="table">
    <tr>
     <td colspan="2" class="text-center">
      <img src="${vo.poster }" style="width: 750px;height: 300px;
      onerror="this.src='no.jpg'">
      
     </td>
    </tr>
    <tr>
     <td colspan="2">
      <h3>${vo.title }</h3>
     </td>
    </tr>
    <tr>
     <td width="10%">주소</td>
     <td width="90%">${vo.address }</td>
    </tr>
    <tr>
     <td colspan="2">${vo.msg }</td>
    </tr>
    <tr>
     <td colspan="2" class="text-right">
      <a href="list.do?tno=${tno }" class="btn btn-sm btn-primary">목록</a>
     </td>
    </tr>
   </table>
  </div>
 </div>
</body>
</html>