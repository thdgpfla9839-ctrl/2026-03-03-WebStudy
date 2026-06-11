/**
 * 코드가 너무 길어서 뺴주려고
 * js = > ts
 * list_ajax.do?page
 * 
 * 보안이 필요한 소스는 자바스크립트로 따로 뺴서 작성하면 실행 시 페이지 소스가 안 보인다
 */
window.onload = () => {
	// 중복되는 코드는 따로 만들어 주고 반복코드 제거해줬다
	dataRecv(1)


}
function foodPrint(json)
{
   let html=''
   json.forEach((food)=>{
	     html+='<a href="#">'
	         +'<div class="col-sm-3">'
	         +'<div class="thumbnail">'
	         +'<img src="'+food.poster+'" style="width: 204px;height: 130px;object-fit:cover">'
	         +'<p>'+food.name+'</p>'
          	 +'</div>'
	         +'</div>'
	         +'</a>'
   })
   $('#print').html(html)
   
   let curpage=json[0].curpage
   let totalpage=json[0].totalpage
   let startPage=json[0].startPage
   let endPage=json[0].endPage
   
   let pagePrint='<ul class="pagination">'
	// 여기 list.jsp 파일에 <c:forEach> 이 부분들이야
	if(startPage>1)
	   {
		   pagePrint+='<li><a class="link" onclick="prev('+(startPage-1)+')">&laquo;</a></li>'	
	   }
	   
	   for(let i=startPage;i<=endPage;i++)
	   {
		   pagePrint+='<li><a class="link" onclick="change('+i+')">'+i+'</a></li>'	
	   }
	   
	   if(endPage<totalpage)
	   {
	   	  pagePrint+='<li><a class="link" onclick="next('+(endPage+1)+')">&raquo;</a></li>'	
	   }
	   pagePrint+='</ul>'
	   $('#pagination').html(pagePrint)
	}
function change(page)
{
	// 중복되는 코드는 따로 만들어 주고 반복코드 제거해줬다
	dataRecv(page)


	    
}
function prev(page)
{
	// 중복되는 코드는 따로 만들어 주고 반복코드 제거해줬다
	dataRecv(page)

}
function next(page)
{
	// 중복되는 코드는 따로 만들어 주고 반복코드 제거해줬다
    dataRecv(page)
	    
}
function dataRecv(page){
	$.ajax({
		        type: 'post',
		        url: 'list_ajax.do',
		        data: { "page": page },
		        success: function(json) // 자동 호출 함수 callback => 결과값을 매개변수로 가지고 온다
		        {
		            console.log(json) // 실행은 브라우저의 콘솔에서 보기, 이거까지 실행한거랑
		            json = JSON.parse(json)
		            console.log(json) // 여기 추가로 작성해서 실행하면 결과값이 다르다 => 배열로 들어옴
		            foodPrint(json)
		        }
})
}