<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--  게시글 수정하기 파일
          전체적인 모양 틀은 insert.jsp에서 가져옴 
          수정하기 링크는 detail.jsp에서 걸어야 한다 => '../board/update.do?no='+no 이렇게
    
     -->
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
button {
  margin-left: 2px
}

h3 {
	text-align: center;
}
</style>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
 <div class="container">
  <div class="row">
   <h3>수정하기</h3>
    <table class="table">
     <tbody>
      <tr>
       <th class="text-center success" width="10%">이름</th>
        <td width="90%">
         <input type="text" size="20" class="input-sm"
          ref="nameRef"
          v-model="name"
         >
        </td>
      </tr>
      <tr>
       <th class="text-center success" width="10%">제목</th>
        <td width="90%">
         <input type="text" size="60" class="input-sm"
          ref="subRef"
          v-model="subject"
         >
        </td>
      </tr>
      <tr>
       <th class="text-center success" width="10%">내용</th>
        <td width="90%">
         <textarea rows="10" cols="63" ref="contRef"
         v-model="content"></textarea>
        </td>
      </tr>
      <tr>
        <th class="text-center success" width="10%">비밀번호</th>
        <td width="90%">
        <input type="password" size="10" class="input-sm"
          ref="pwdRef" v-model="pwd"
         >
        </td> 
      </tr>
      <tr>
       <td colspan="2" class="text-center">
        <button class="btn-sm btn-info" @click="write()">수정</button>
        <button class="btn-sm btn-info" @click="cancel()">취소</button>
       </td>
      </tr>
     </tbody>
    </table>
  </div>
 </div>
 <script>
 // 이 과정은 수정 게시글에서 수정 취소 누르면 전 화면으로 이동시키는 과정
  let update =Vue.createApp({
	  data(){
		  return {
			  no:${param.no},
			  name:'',
			  subject:'',
			  content:'',
			  pwd:''
		  }  
		},
		  // mounted는 화면이 생성되면 필요한 데이터를 서버로 부터 읽어줄 때 사용한다
		  // 다른 프레임워크와 연동할 때 사용 => 보통 jquery,AngularJS와 연동
		  mounted(){
			  // $(function(){}) 부분과 똑같다
			  axios.get('../board/update_vue.do',{
				  params:{
					  no:this.no  
				  }
				  
			  }).then(response=>{
				  console.log(response.data)
				  this.name=response.data.name
				  this.subject=response.data.subject
				  this.content=response.data.content
			  })
		  },
		  // 사용자 정의 부분 => 버튼 누르는 것과 같은 이벤트 처리를 이 부분에서 해준다
		  methods: {
			  cancel(){
				  window.location.href="../board/detail.do?no="+this.no
			  },
			  write(){
				  // 입력이 됐는지 확인하는 작업 => not null이라서 반드시 입력을 시켜줘야 함
				  if(this.name.trim()==="") 
				  {
				     this.$refs.nameRef.focus()
				     return
				  }
			  else if(this.subject.trim()==="") 
				  {
				     this.$refs.subRef.focus()
				     return
				  }
				  else if(this.content.trim()==="")
	    			{
	    				this.$refs.contRef.focus()
	    				return
	    			}
	    			else if(this.pwd.trim()==="")
	    			{
	    				this.$refs.pwdRef.focus()
	    				return
	    			}
				  
				  // 서버 연동 시키기 => post 방식의 형식 post(url,data,config)
				  axios.post('../board/update_ok.do',{},{
					  params:{
						  // 보내줘야 하는 데이터 내용
						  no:this.no,
						  name:this.name,
						  subject:this.subject,
						  content:this.content,
						  pwd:this.pwd
					  }
				  }).then(response=>{
					  // 처리 결과값 들어오는 곳
					  if(response.data==='yes')
						  {
						    // 비밀번호 일치하면 이동
						    window.location.href="../board/detail.do?no="+this.no
						  }
					  else
						  {
						    // 비밀번호 틀리면 재입력
						    alert("비밀번호가 일치하지 않습니다") // 메시지 창이 뜨게 만듦
						    this.pwd='' // 공백으로 바꿔주기
						    this.$refs.pwdRef.focus()
						    // 여기까지가 $(id명) => DOM 태그 자체를 가져온다
						  }
				  })
			  }
		  }
	  
  }).mount(".container") // 수정하기 <div> 전체를 제어할 때는 .mount를 꼭 적어준다
 </script>
 </body>
	</html>