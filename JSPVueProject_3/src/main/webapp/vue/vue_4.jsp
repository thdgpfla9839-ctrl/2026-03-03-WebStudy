<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--  가장 많이 사용된다 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
 <div class="app">
  <button @click="count++"> <!-- 함수 곧장 처리되게 해버림 -->
   count 증가
  </button>


  <button @click="name='Hong'">
   이름 변경
  </button>
  <!--  v-memo => 렌더링 최적화 기능을 갖고 있다
                  지정된 값이 변경되지 않으면 다시 렌더링을 하지 않는다
                                                            -->
  
  <div v-memo="[name]"> <!-- 이름이 변경이 될 때 카운트를 증가시켜라 -->
   <h3>{{name}}</h3>
   <h3>{{count}}</h3>
  
  </div>
   <h3>{{name}}</h3>
   <h3>{{count}}</h3>
  
 </div>
 <script>
  let app=Vue.createApp({
	  data(){
		  return {
			  name:'Shin',
			  count:0
		  }
	  }
  }).mount('.app')
 </script>
</body>
</html>