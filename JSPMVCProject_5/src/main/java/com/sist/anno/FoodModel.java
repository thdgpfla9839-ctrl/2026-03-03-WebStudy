package com.sist.anno;
// 어노테이션 : '구분자 혹은 인덱스' 라고 보면 된다 => if문 추가
// 모든 메소드는 invoke를 이용하면 메소드 이름 몰라도 호출이 가능하다
 /*
  *  어노테이션 구분자 종류)
*  => 
  *  @ => 클래스 구분 (Type)
 *   class A
 *   {
 *      @ => 멤버변수 구분  (Field)
 *      B b;
 *      
 *      @ => 생성자 구분 (Constructor)
 *      public A()
 *      {
 *      }
 *      @ => 메소드 (Method)
 *      public void disp()
 *      {
 *      }
  *  
  */
@Controller // 클래스 찾는 어노테이션
// 이 패키지에서는 메소드 찾는 연습중
public class FoodModel {

	@RequestMapping("food/list.do") // 메소드 찾는 어노테이션
	public void food_list() 
	  {
		  System.out.println("food_list Call");
	  }
	@RequestMapping("food/detail.do") // if문과 같은 역할을 추가한 거
	public void food_detail() 
	{
		System.out.println("food_detail Call");
	}
}
