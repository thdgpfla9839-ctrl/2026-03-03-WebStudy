<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
       함수응용 예제 => 영화진흥원 소스를 이용할 예정
                     JSON을 이용해서 전송
    
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin-top: 50px;
}

.row {
	margin: 0px auto;
	width: 960px;
}
</style>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
// 이 영역에 변수를 선언하면 전역변수
let movie=[]
window.onload=function()
{
	movieList(1)
	/*
	   searchMainDailyBoxOffice.do
	   searchMainRealTicket.do
	   searchMainDailySeatTicket.do
	   https://www.kobis.or.kr/kobis/business/main/	   
	*/
}
function movieList(no)
{
	// 서버와 연결 후 데이터 넘기기
   axios.get('movie_list.do',{
	   params:{
		   no:no // 앞은 키, 뒤는 값
	   }
   }).then(response=>{
   
	   movie=response.data
	      console.log(movie)

	      let html=''
	    	  movie.forEach((m)=>{
	              html+='<tr onmouseover="movieDetail('+m.rank+')">'
	              +'<td class="text-center">'+m.rank+'</td>'
	              +'<td class="text-center"><img src="https://www.kobis.or.kr'+m.thumbUrl+'"></td>'
	              +'<td >'+m.movieNm+'</td>'
	              +'<td >'+m.director+'</td>'
	              +'<td >'+m.genre+'</td>'
	              +'</tr>'
	           })
	           
	           document.querySelector("#list tbody").innerHTML = html;
	        })
	     }

const movieDetail=(mno)=>{
	// 여기가 호출되면 display : none 했던게 노출될거야
	
	let table=document.querySelector("#detail")
	table.style.display=''
	let m=movie.find(m=>m.rank===mno)
	console.log(m)
	let img=document.querySelector("#poster")
	img.src="https://www.kobis.or.kr"+m.thumbUrl
	
	let name=document.querySelector("#name")
	name.textContent=m.movieNm
	let director=document.querySelector("#director")
	director.textContent=m.director
	let genre=document.querySelector("#genre")
	genre.textContent=m.genre
	let grade=document.querySelector("#grade")
	grade.textContent=m.watchGradeNm
	let story=document.querySelector("#story")
	story.textContent=m.synop


}
</script>
</head>
<body>
	<div class="container">
		<div class="row text-center">
			<button class="btn-lg btn-warning" onclick="movieList(1)">박스오피스</button>
			<button class="btn-lg btn-info" onclick="movieList(2)">실시간 예매율</button>
			<button class="btn-lg btn-success" onclick="movieList(3)">좌석 점유율</button>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="col-sm-8">
				<table class="table" id="list">
					<thead>
						<tr class="success">
							<th class="text-center">순위</th>
							<th class="text-center"></th>
							<th class="text-center">영화명</th>
							<th class="text-center">감독</th>
							<th class="text-center">장르</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
			<div class="col-sm-4">
				<table class="table" id="detail" style="display: none">
					<tbody>
						<tr>
							<td colspan="2" class="text-center"><img src=""
								style="width: 360px; height: 250px; object-fit: cover"
								id="poster"></td>
						</tr>
						<tr>
							<td width="25%">영화명</td>
							<td width="75%" id="name"></td>
						</tr>
						<tr>
							<td width="25%">감독</td>
							<td width="75%" id="director"></td>
						</tr>
						<tr>
							<td width="25%">장르</td>
							<td width="75%" id="genre"></td>
						</tr>
						<tr>
							<td width="25%">등급</td>
							<td width="75%" id="grade"></td>
						</tr>
						<tr>
							<td colspan="2" id="story"></td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>