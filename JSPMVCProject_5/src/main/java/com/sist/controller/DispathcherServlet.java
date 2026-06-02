package com.sist.controller;
import jakarta.servlet.RequestDispatcher;
// 주석 추가하기
// 스프링의 컨트롤러를 이용해 클래스를 만들면 따로 등록하지 않아도 자동인식되게 만들어주는 코드를 연습해봄
// 스프링과 유사한 구조를 연습해봄
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

import com.sist.anno.Controller;
import com.sist.model.*;

// URI 주소 마지막에 .do가 있는 경우 servlet 호출하게끔 만들어둠
// MVC는 무조건 Controller를 거쳐야 수행(실행)이 된다
/*
 *   브러우저(주소창) 요청 ================== Controller ===================== Model 찾기
 *                                      => 여기서는                           |
 *                                         DispathcherServlet         Model 메소드 찾기 => DAO 연동
 *                                                                          |
 *                                                                        결과값 찾기
 *                                                                           |===> request / session
 *                                                                           Controller
 *                                                                           |======> request / session을 전송
 *                                                                           jsp 찾아서 결과값 전송
 * ========================================================================================================================                                                                          
 */



/* 
 *   
 *   1) 어노테이션 
 *     : 클래스 구분 / 메소드 찾기가 가능
 *       별다른 기능은 없다 검색(인덱스)만 가능
 *       쉽게 찾기가 가능해짐
 *      
 *   2) 팔요한 데이터를 등록 => XML
 *   3) 데이터베이스 연동 => MyBatis
 *   4) MVC 동작방법
 *   ------------------------------ 라이브러리 : SpringBoot/SpringFramework
 *   
 *   =======================================================================================================================
 *   
 *   어노테이션 : 알아보기 쉽게 만든 것
 *             주석이 아님
 *             복잡한 XML이나 if문을 단순화 해줌 => 소스 간결화
 *             생산성 향상
 *             
 *   제작 : 1. Retention : 메모리 할당 시 언제까지 사용되는지
 *            SOURCE / CLASS => 컴파일시 바로 사라진다 
 *            / RUNTIME => 프로그램 종료 시까지 유지된다
 *             
 *         2. Target : 어디에 붙일까?
 *          **Type   : 클래스 위에 위치함, 클래스 찾을 떄 사용
 *          **Method => 메소드 위에 (메소드 찾기)
 *            Constructor => 생성자 위에 (생성자 찾기)
 *            Parameter => 매개변수 위에 (매개변수 찾기)
 *         **Filed => 멤버변수 위에 (멤버변수 찾기)
 *            
 * ==========================================================================================================================
 * 항상 제어자는 위 혹은 옆에 위치한다
 * 어노테이션도 마찬가지
 * 
 *       @
 *       public void disp(@어노테이션 B b)
 *       {
 *       }
 * ===============================================================
 * 단 스프링은 @WebServlet이 없다 => web.xml에 등록한다 그래야 톰캣이 동작함           
 *            
 */
@WebServlet("*.do")
public class DispathcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String pack = "com.sist.model"; // 다음번에는 xml에 패키지 단윌호 등록하면 됨
	private List<String> clsList = new ArrayList<String>();
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try
		{
			// 나중에 여기는 realPath()를 붙여 출력해야함 => 리눅스 호환을 위해 
			String path="C:\\webDev\\webStudy\\JSPMVCProject_5\\src\\main\\java";
			String s = path+"\\"+pack.replace(".", "\\");
			System.out.println(s);
			File dir = new File(s);
			File[] files = dir.listFiles();
			for(File f:files)
			{
				if(f.isFile())
				{
					String name = f.getName();
					String ext = name.substring(name.lastIndexOf(".")+1);
					if(ext.equals("java"))
					{
						/* System.out.println(name); */
						String ss = pack+"."+name.substring(0,name.lastIndexOf("."));
						System.out.println(ss); // 메모리 할당까지 할 수 있게 만듦
						
						/*
						 * Class clsName = Class.forName(ss); Object obj =
						 * clsName.getDeclaredConstructor().newInstance(); System.out.println(obj); //
						 * 메모리 할당
						 */		
						clsList.add(ss);
					}
				} // 클래스만 만들어주면 등록하지 않아도 컨트롤러가 자동으로 인식해줌
			}
			
		}
		catch(Exception ex) {}
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 *  http://localhost:8080/JSPMVCProject_5/food/list.do
		 *  
		 *  uri 부분 : "/JSPMVCProject_5/food/list.do"
		 *  cmd     : "food/list.do"
		 */
		
		// 사용자 요청 정보 받기
		String uri = request.getRequestURI();
		String cmd = uri.substring(request.getContextPath().length()+1); // 찾을 준비는 끝
		System.out.println(cmd);
	    try
	    {
	    	// 메소드 찾기
	    	for(String cls:clsList)
	    	{
	    		System.out.println(cls);
	    		Class clsName = Class.forName(cls);
	    		/*if(clsName.isAnnotationPresent(Controller.class)==false)
	    		{
	    			// @Controller가 없는 클래스면 사용하지 않겠다.
	    			continue;
	    			
	    		}*/ // 이부분 원래 코드인데 나만 실행이 안 됐어서 주석처리함
	    		
	    		//@Controller가 있는 클래스는 메모리 할당을 한다
	    		Object obj = clsName.getDeclaredConstructor().newInstance();
	    		
	    		// 해당 클래스 안에서 메소드를 찾는다
	    		// 먼저 전체 메소드를 가지고 온다
	    		Method[] methods = clsName.getDeclaredMethods();
	    		for(Method m:methods)
	    		{
	    			// 메소드 위에 리퀘스트맵핑이 있는지 확인
	    			System.out.println(m.getName());
	    			RequestMapping rm = m.getAnnotation(RequestMapping.class);
	    			
	    			if(rm.value().equals(cmd))
	    			{
	    				String jsp = (String)m.invoke(obj, request,response);
	    				if(jsp==null)
	    				{
	    					// void => Jquery 혹은 Vue를 jsp에 전송할 때
	    					return; // 메소드 자체에서 처리 왜냐 void이기 때문이지
	    				}
	    				else if(jsp.startsWith("redirect:"))
	    				{
	    					// sendRedirect() : request 초기화 후에 화면 변경
	    					response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
	    				}
	    				else
	    				{
	    					// request 전송하려면 초기화가 되면 안 되지 => forward를 이용해 request 값 유지
	    					RequestDispatcher rd = request.getRequestDispatcher(jsp);
	    					rd.forward(request, response);
	    					
	    				}
	    				
	    				return; // 프로그램 종료
	    				// 모델패키지 - foodModel 가서 기능 작성한다
	    			}
	    		}
	    	}
	    }
	    catch (Exception ex) {ex.printStackTrace();}
	}

}
