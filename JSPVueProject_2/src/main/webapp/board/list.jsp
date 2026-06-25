<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- 리액트는 cdn이 아니라 module화를 이용해서 개인프로젝트를 만들 예정 
    
         JSP => 깜박 거림 효과 / 입력된 데이터를 손실 
             1. Ajax => 중간에 HTML을 만들어서 첨부 
             2. Vue => HTML자체 제어 
                JSP => HTML을 이용 => 태그에서 제어 : ThymeLeaf
    
    
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
button {
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
      <h3>Vue를 이용한 게시판</h3>
      <table class="table">
        <tbody>
          <tr>
            <td>
             <a href="../board/insert.do" class="btn btn-sm btn-warning">새글</a>
            </td>
          </tr>
        </tbody>
      </table>
  <%-- table-hover 말고 table-striped 쓰면 또 게시글이 효과가 달라진다 --%>
  <table class="table table-hover">
   <thead>
          <tr class="danger">
           <th width=10% class="text-center">번호</th>
           <th width=45% class="text-center">제목</th>
           <th width=15% class="text-center">이름</th>
           <th width=20% class="text-center">작성일</th>
           <th width=10% class="text-center">조회수</th>
          </tr>
        </thead>
        <tbody>
   <%-- 반복문  --%>
    <tr v-for="(vo,index) in list" :key="index">
     <td width="10%" class="text-center">{{count-index}}</td> <!-- count-index 게시물 번호가 순차적으로 정렬된다 -->
     <td width="45%"><a :href="'../board/detail.do?no='+vo.no">{{vo.subject}}</a></td>
     <td width="15%" class="text-center">{{vo.name}}</td>
     <td width="20%" class="text-center">{{vo.dbday}}</td>
     <td width="10%" class="text-center">{{vo.hit}}</td>
    </tr>
    <tr>
            <td colspan="5" class="text-center">
              <button class="btn-sm btn-primary" type="button" @click="prev()">이전</button>
              {{curpage}} page / {{totalpage}} pages
              <button class="btn-sm btn-primary" type="button" @click="next()">다음</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
<script type="importmap">
<%--  지금하는 과정은 module화 방식 --%>
   {
      "imports":{
         "vue":"https://unpkg.com/vue@3/dist/vue.esm-browser.js"
      }
   }
  </script>
  <%--
        import java.util.Scanner
   --%>
<script type="module">
 import {createApp} from "vue"
    const app=createApp({
       // 변수 설정 => HTML에 적용됨 => 호출할 때는 반드시 this.을 써야한다 
      data(){
           return {
              curpage:1,
              totalpage:0,
              count:0,
              list:[]
           }
        },
       // 시작과 동시에 서버에서 데이터 값 읽기
       mounted(){
         // 제이쿼리에서는 $(function(){}) , 자바스크립트에서는  window.onload=function(){} 부분에 해당된다
                this.dataRecv()
        },
        // 사용자 정의 함수 
        methods:{
            async dataRecv(){
              await axios.get('../board/list_vue.do',{
                  params:{
                    page:this.curpage
                  }
              }).then(response=>{
                 console.log(response.data)
                 this.list=response.data.list
                 this.curpage=response.data.curpage
                 this.totalpage=response.data.totalpage
                 this.count=response.data.count
              })
            },
            prev(){
               this.curpage=this.curpage>1?this.curpage-1:this.curpage
               this.dataRecv()
            },
            next(){
               this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage
               this.dataRecv()
            }
        }
    })
    app.mount(".container")
  </script>
</body>
</html>