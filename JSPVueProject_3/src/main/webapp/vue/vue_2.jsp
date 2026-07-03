<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div id="app">
   <h3>v-once:랜더링 한번만 할 때 사용, 데이터가 변경된 경우 화면은 갱신이 안 된다 => 로고, 검색기를 갖고 올 때 사용한다</h3>
   <h4 v-once>{{message}}</h4>
   <h4>{{message}}</h4>
   <button @click="change()">변경</button>
   <div>
    <ul>
     <li>회사명</li>
     <li>작성자</li>
     <li>게시물번호</li>
     <li>변경되지 않는 정보들은 v-once를 사용한다 => 개인정보 방침, 로그 => Footer에서 사용</li>
    </ul>
   </div>
  </div>
  <script>
   let app=Vue.createApp({
	   data(){
		   return {
			   message:'Hello Vue'
		   }
	   },
	   methods:{
		   change(){
			   this.message="Vue3 v-once";
		   }
	   }
   }).mount("#app")
  </script>
</body>
</html>