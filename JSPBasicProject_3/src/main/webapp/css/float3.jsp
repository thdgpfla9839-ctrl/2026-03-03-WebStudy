<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/*
   이미지 카드 / 로그인 버튼을 옆으로 / 메뉴 li / layout
   float 
   => flex / grid (최근 형식)
      ----------- 레이아웃 (반응형)
*/
div.gallery{
  margin: 5px;
  border:1px solid #CCC;
  float: left;
  width: 180px;
}
div.gallery img{
  width: 100%;
  height: 200px;
}
div.desc{
 float: left;
 padding: 15px;
 text-align: center
}
</style>
</head>
<body>
  <div class="gallery">
    <img src="../images/m5.jpg">
    <div class="desc">
     서울 도심의 초고층 빌딩에서 정체불명의 집단 감염사태가 발생한다.
    </div>
  </div>
  <div class="gallery">
    <img src="../images/m1.jpg">
    <div class="desc">
     서울 도심의 초고층 빌딩에서 정체불명의 집단 감염사태가 발생한다.
    </div>
  </div>
  <div class="gallery">
    <img src="../images/m2.jpg">
    <div class="desc">
     서울 도심의 초고층 빌딩에서 정체불명의 집단 감염사태가 발생한다.
    </div>
  </div>
  <div class="gallery">
    <img src="../images/m3.jpg">
    <div class="desc">
     서울 도심의 초고층 빌딩에서 정체불명의 집단 감염사태가 발생한다.
    </div>
  </div>
  <div class="gallery">
    <img src="../images/m4.jpg">
    <div class="desc">
     서울 도심의 초고층 빌딩에서 정체불명의 집단 감염사태가 발생한다.
    </div>
  </div>
</body>
</html>