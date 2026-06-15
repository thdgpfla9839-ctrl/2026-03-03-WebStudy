<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
  width: 960px;
}
h1 {
   text-align: center;
}
p{
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>
</head>
<body>
 <div class="container">
  <div class="row text-center">
    <a href="list.do?tno=1" class="btn btn-sm btn-danger">명소</a>
    <a href="list.do?tno=2" class="btn btn-sm btn-success">자연</a>
    <a href="list.do?tno=3" class="btn btn-sm btn-info">쇼핑</a>
    <a href="list.do?tno=4" class="btn btn-sm btn-warning">호텔</a>
  </div>
   <div class="row" style="margin-top: 10px">
   <!-- title 출력될 위치 -->
    <h1 style="color: red">${title }</h1> 
   </div>
   <div class="row" style="margin-top: 20px">
    <!--  이미지가 출력될 위치 -->
     <c:forEach var="vo" items="${list }"> <!--  이 괄호 안에 내용은 모델 클래스에 request.setAttribute("list", list) 이 안에 들어 있는 걸 가져온거야 -->
      <div class="col-sm-3"> <!-- 화면을 분할하면 12개로 나눠지는데 3칸씩 합친거야 한줄에 4개씩 출력되게끔 -->
       <a href="detail.do?tno=${tno }&no=${vo.no }"> <!--  데이터가 두개 이상 보내지면 구분자로 &를 줘야함 이제 상세보기 하러 mapper로 가자 -->
        <div class="thumbnail">
         <img src=" ${vo.poster }" style="width: 240px; height: 130px;object-fit:cover"
         onerror="this.src='no.jpg'"> <!-- 이미지가 출력이 안 될때 onerror 주고 파일 바꿀 수 있음 -->
        </div>
         <p>${vo.title }</p>
       </a>
      </div>
     </c:forEach>
   </div>
   <div class="row text-center" style="margin-top: 20px">
    <a href="list.do?tno=${tno }&page=${curpage>1?curpage-1:curpage}" class="btn btn-sm btn-primary">이전</a>
    ${curpage } page / ${totalpage } pages
    <a href="list.do?tno=${tno }&page=${curpage<totalpage?curpage+1:curpage}" class="btn btn-sm btn-primary">다음</a>
   </div>
 </div>
</body>
</html>