package com.sist.anno;
// 이 패키지는 메소드 찾는 연습용
@Controller
public class GoodsModel {
	
	@RequestMapping("goods/list.do") //어노테이션 만들어줄 때 메소드로 선언했기 떄문에 항상 여기에 위치해야한다 찾는 영역 위에 위치한다
  public void good_list() 
  {
	  System.out.println("good_list Call");
  }
	@RequestMapping("goods/detail.do")
  public void good_detail() 
  {
	  System.out.println("good_detail Call");
  }
}
