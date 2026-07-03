<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    
          Vue
            1. 장점 : 리액트와 연동, 제이쿼리와 연동, 앙글라js와도 연동이 된다
                     리액트는 가상DOM을 이용해서 속도가 빠름
                     제이쿼리는 값 읽기가 편함
                     앙글라는 양방향이다
                     
                 ==================================================
                  => 즉, 다른 프레임 워크와 연동이 가능하다
            
            2. 화면 출력 방식 => View에서 사용한다
               v-on: 이벤트 처리 시 사용, @ v-on:click="함수"에서 @click="함수" 이렇게 함수호출 방식이 변경됨
               v-bind: 속성에 값을 주입, :src, :href 같은 형식으로 사용한다
               v-show: toggle에서 사용됨, true/false
               v-if: 조건문 사용시
               v-for: 반복문 사용시 사용
               v-memo: 캐시메모리, callback
               v-slot/v-once
               
           3. ViewModel
              => MVVM
              => M : model => 데이터 관리 data()
              => V : html 영역
              => V : html 영역
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
p {
   overflow: hidden;
   white-space: nowrap;
   text-overflow: ellipsis;
}
.a-link:hover{
  cursor: pointer;
}
.btn-xs {
  margin-left: 2px
}
h3{
  text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
   <div class="container">
    <div class="row">
     <button class="btn btn-sm btn-danger">실행</button>
      <p id="print" style="display:none">
       <input type="text" id="name" class="input-sm" size="20" v-model="msg">
      </p>
    </div>
   </div>
   <script>
    let reply=Vue.createApp({
    	data(){
    		return{
    			msg:'홍길동',
    			no:0
    		}
    	},
    	// 제이쿼리와 뷰를 연동을 할 때는 여기 mounted에서 처리해줘야 한다
    	mounted(){
    		$('.btn-danger').on('click',function(){
    			
    			if(this.no===0)
    				{
    				    $('#print').show()
    				    $('.btn-danger').text("취소")
    				    this.no=1
    				}
    			else
    				{
    				   $('#print').hide()
    				   $('.btn-danger').text("실행")
    				   this.no=0
    				}
    		})
    	}
    }).mount(".container")
   </script>
</body>
</html>