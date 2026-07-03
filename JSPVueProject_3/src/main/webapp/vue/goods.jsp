<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    주석 추가하기
    전체적인 틀
    Vue.createApp({
        data(){
          return{
          
          
          }
        
        },
        created(){
         채팅에서 많이 사용 -> 연결 시 websocket연결
        }
    
    }).mount("선택된 HTML (CSS선택자)")
    
    서버연결
    axios.get(url,data)
    axios.post(url,data,config) => data값은 null,{}
    => 요청 시
        .then(response=>{
         response는 결과값을 가지고 온다
         response는 여러개의 데이터를 가지고 온다, 실제 전송된 데이터는 data => response.data
         
    }) 
    
    ================================================================================
    let _this=this  ===> this를 사용하고 싶으면 이렇게 작성한다
    then(Function(response){
    
        => 여기에서 this는 Function이 가지고 있는 this는 Object로 사용한다
    })
    
    ==================================================================================
    then(response=>{
    
     => this는 존재하지 않는다 => Vue가 가지고 있는 this를 사용
        
    })
    
    ===================================================================================
    => 이렇게 axios를 사용하면 화면이 변경없이 그자리에서 처리가 된다
       => 이거와 유사하게 ajax,fetch도 동일함
    ===================================================================================
    Vue와 React는 태그를 만들어서 추가하는 방법이 아니라 html 자체를 제어한다 => 이건 Vue
                                              html을 만들어서 추가하는 방법을 쓴다 => React => 여기는 jsx를 이용해서 " "가 없다   
    
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
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
    <div class="container">
     <div class="row">
       <p>단가:{{price}}원</p>
        <button class="btn-m btn-danger" @click="account--" :disable="account <= 1">-</button>
         <span>{{account}}</span>    
        <button class="btn-m btn-primary" @click="account++">+</button>   
         <p>총금액:{{totalPrice}}</p> 
     </div>
    </div>
    <script>
     let goods=Vue.createApp({
    	 data(){
    		 return{
    		 price:10000,
    		 account:1
    		 }	// return{}안에 내용이 html에 적용하는 변수 설정하는 부분 => this.으로 받아야 한다	 
    	 },
    	 // 계산할 때 사용되는 계산식
    	 computed:{
    		 totalPrice(){ // totalPrice()는 함수
    			 return (this.price * this.account).toLocaleString()
    		 }
    	 },
    	 // 감시자 => 데이터 값 변경시 마다 처리
    	 watch:{
    		 account(newVal,oldVal){
    			 console.log("수량변경:"+oldVal+"->"+newVal) // watch:{} 클릭할 때마다 변경되는 값 출력됨
    		 }
    	 }
     }).mount(".container")
    </script>
</body>
</html>