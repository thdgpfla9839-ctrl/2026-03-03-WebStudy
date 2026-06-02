package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import com.sist.model.*; // 모델을 찾으려면 필요하다

/*
 *   _jspService (GET / POST)
 */
/*
 *    결과값 출력 (요청) === 요청 받기 / Model 찾기 === 결과값 전송 ==== 요청처리 
 *    --------------          Controller                       비지니스로직(데이터 처리)
 *    JSP => View                                                Model
 *                                                             1) VO 
 *                                                             2) DAO
 *                                                             3) 통합 => request값 담기 
 *                                                                Model 
 *                                                             4) Open API / WebSocket 
 *        | JSP + Jquery => Jquery 3 : Jquery 4
 *        | React 
 *        | Vue => 배포 (CI/CD) => SpringFramework (나눠서 작업)
 *          => SpringBoot CDN방식이용 
 */

@WebServlet("*.do")
// 컨트롤러가 고정이 안 되면 사이트가 동작할 수 X
public class Controller extends HttpServlet  // 컨트롤러 고정시키려는중
{
	// 이렇게 하나하나 클래스로 코딩하면 안 됨 관리가 어려워 => 메소드화 해서 만들어야 해
	private static final long serialVersionUID = 1L;
	private String[] cls = {
			"com.sist.model.ListModel", // 클래스 등록
			"com.sist.model.InsertModel",
			"com.sist.model.DetailModel",
			"com.sist.model.DeleteModel"
			
};
	private String[] keys= {
			
			"databoard/list.do", // url 주소, "com.sist.model.ListModel"랑 일치가 돼야 함
			"databoard/insert.do",
			"databoard/detail.do",
			"databoard/delete.do"
	};
	
	private Map<String, Model> clsMap = new HashMap<String, Model>(); // 맵에 저장하면 if문 없이 찾기가 가능
	// init()은 초기화를 담당
	// 메뉴를 저장한다
	// <bean id="databoard/list.do" class="com.sist.model.ListModel">
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try
		{
			// 클래스에 메모리에 저장
			for(int i =0; i<cls.length;i++)
			{
				Class clsName = Class.forName(cls[i]);
				Model model = (Model)clsName.getDeclaredConstructor().newInstance();
				clsMap.put(keys[i], model);
				// 이름만 갖고 만드는 리플랙션
				// 리플랙션 : 클래스명으로 메모리 할당
				//          멤버변수 설정
				//          생성자 호출
				//          메소드 호출
				
				
			}
		}
		catch (Exception ex) {}
	}

	// service() 요청시에 요청을 받고 -> 해당 모델 찾기 => 그 후 처리 '결과값'을 jsp에 전송한다 => 요청처리는 model이 해준다
	// doGet() : GET /  doPost(): POST  => 근데 두가지를 통합해서 처리하는 메소드가 service()
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			// 1. 요청 받기
			String uri = request.getRequestURI();
			String key = uri.substring(request.getContextPath().length()+1);
			System.out.println(uri);
			System.out.println(key);
			
			/*
			 *   databoard/list.do
			 */
			
			// 2. 해당 모델 찾기
			Model model = clsMap.get(key);
			
			// 3. 메소드 호출 => jsp로 받는다
			String jsp = model.requestHandler(request, response);
			
			// 이동 => request를 유지 하지 않고 이동 => sendRedirect()
			if(jsp.startsWith("redirect:"))
			{
			     
				 response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
			}
			else
			{
			// 4. jsp => request 전송
				// request 유지
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			rd.forward(request, response); // 리퀘스트를 보낼 떄는 포워드를 사용한다 / rd가 jsp정보를 갖고 있다 // 키하고 모델클래스를 어떻게 매칭할지에 초점을 둬라
			// 받아서 출력하러 가자 보드데이터에 리스트로 가자
			}
		}
		catch (Exception ex) {}
	}

}
