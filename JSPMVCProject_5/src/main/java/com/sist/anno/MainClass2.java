package com.sist.anno;
import java.lang.reflect.Method;
import java.util.*;
public class MainClass2 {
// 클래스 하나를 주고 메소드를 제어하는 방식을 연습
	//  이 구조가 Spring에서 리퀘스트맵핑을 이용해 메소드 찾기
	// URI 주소는 리퀘스트 맵핑 주소와 일치돼야 한다
	// 필요하면 리퀘스트 맵핑에 추가만 하면 별다른 코드 추가 없이 간단히 호출이 가능하다
	// 이제 인터페이스가 없어짐
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		String[] cls = {"com.sist.anno.FoodModel",
				"com.sist.anno.GoodsModel",
				"com.sist.anno.MemberModel",
				"com.sist.anno.ReplyModel",
				"com.sist.anno.MainClass" // 메모리 할당이 안 된대
				
		}; 
		
		System.out.print("URI입력: 예) goods/list.do");
	    String uri = scan.next();
	    for(String clsName:cls)
	    {
	    	
	    	Class c = Class.forName(clsName);
	    	Object obj = c.getDeclaredConstructor().newInstance();
	    	
	    	// 클래스 메모리 할당
	    	Method[] methods = c.getDeclaredMethods();
	    	for(Method m:methods)
	    	{
	    		RequestMapping rm = m.getAnnotation(RequestMapping.class);
	    		if(rm.value().equals(uri))
	    		{
	    			m.invoke(obj, null);
	    		}
	    	}
	    }
 	}

}
