package com.sist.anno;
// 브라우저 같은 역할
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		System.out.println("메뉴:맛집(1),상품(2),댓글(3),회원(4)");
		
		// 기능을 추가할 때마다 if문을 계속 추가하는 짓은 노노
		// 안 좋다는 예시를 보여주는 거야
		int menu = scan.nextInt();
		if(menu==1) // if문 하나씩 추가하는게 어노테이션
		{
			// 기능 호출
			System.out.println("기능 선택: 목록출력(1), 상세보기출력(2): ");
			int m = scan.nextInt();
			FoodModel model = new FoodModel();
			if(m==1)
			{
				model.food_list();
			}
			else if(m==2)
			{
				model.food_detail();
			}
		}
		else if(menu==2)
		{
			
		}
		else if(menu==3)
		{
			
		}
		else if(menu==4)
		{
			
		}
	}

}
