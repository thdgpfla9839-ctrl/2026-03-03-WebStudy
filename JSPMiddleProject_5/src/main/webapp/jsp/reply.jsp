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
    <div class="row">
      <h3>답변하기</h3>
      <form method=post action="reply_ok.jsp">
      <table class="table">
       <tr>
        <th width=10% class="text-center">이름</th>
        <td width=90%><input type=text name=name size=20 class="input-sm" required>
         <input type="hidden" name=pno value="${param.no }">
          <%--
            ${param.no} 는 request.getPatameter("no")와 똑같다
            reply.jsp?no=${vo.no}
            값하나 받을 때는 저렇게 처리해도 된다 => value="${param.no }"
            -----------------------------------------------------
            ${requestScope.키}
            ${sessionScope.키}
            ${param.no}
            ${pageContext.키}
            
            그외에는 비교연산자 / 삼항연산자 / += 정도 등장
           --%>
        </td>
       </tr>
       <tr>
        <th width=10% class="text-center">제목</th>
        <td width=90%><input type=text name=subject size=60 class="input-sm" required></td>
       </tr>
       <tr>
        <th width=10% class="text-center">내용</th>
        <td width=90%>
         <textarea rows="10" cols="61" name="content" required></textarea>
        </td>
       </tr>
       <tr>
        <th width=10% class="text-center">비밀번호</th>
        <td width=90%><input type=password name=pwd size=10 class="input-sm" required></td>
       </tr>
       <tr>
         <td colspan="2" class="text-center">
           <button class="btn-sm btn-primary">답변</button>
           <button class="btn-sm btn-primary" type=button
            onclick="javascript:history.back()"
           >취소</button>
         </td>
       </tr>
      </table>
      </form>
    </div>
   </div>
</body>
</html>