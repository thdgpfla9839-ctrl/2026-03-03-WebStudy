package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.*;


// MVC 구조를 만들고 있음 => 우리는 윈도우 / 우분투에서 실행 후 aws 호스팅이 가능하게 호환성 가능하게 만드는중 이후에 .jar(라이브러리 생성) 파일을 만들어 줄거야
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 클래스 모으기 => 모델 클래스를 저장할거야
	private List<String> clsList = new ArrayList<String>(); 
	// => 이 안에는 클래스가 저장될 거야
	/* com.sist.model.FoodModel
	 * com.sist.model.MemberModel
	 */
	

	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try
		{
			// 경로명으로 갖고 오면 인식을 못하는 경우가 발생
			// 그러므로 리얼 패스를 이용한다
			URL url = this.getClass().getClassLoader().getResource("."); // 지금처럼 읽어와야 한다
			
			// 파일로 변경
			File file = new File(url.toURI());
			// System.out.println(file.getPath()); // 겟패스는 경로명 + 파일명까지 읽어온다 최종적인 경로명 만든 후에 다른 작업할 때 오류날 수 잇어서 주석처리함 
			
			// path를 바꿀 거야
			String path = file.getPath();
			path = path.replace("\\", File.separator); 
			// -> File.separator를 사용하면 윈도우는 \\ , 우분투나 맥은  / 로 자동변환된다 
			
			// 실제 경로
			//C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCLastProject\WEB-INF\classes
			
			// 우리는 실제 경로 마지막에 classese가 필요없어서 잘라서 가져오려고
			path = path.substring(0,path.lastIndexOf(File.separator));
			// System.out.println(path); 최종적인 경로명 만든 후에 다른 작업할 때 오류날 수 잇어서 주석처리함 
			
			// 호환성을 위해서 최종적으로 우리가 원하는 경로를 만들었어 => 이러면 리눅스에서도 호환이 가능해짐
			path = path+File.separator+"application.xml";
			// System.out.println(path); 최종적인 경로명 만든 후에 다른 작업할 때 오류날 수 잇어서 주석처리함 
			// C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCLastProject\WEB-INF\application.xml 
			
			
			// xml 안에 있는 데이터(com.sist.model)를 추출 => '파싱'이라고 한다
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			// 파싱기 생성
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			// 파싱할 xml을 읽어온다
			Document doc = db.parse(new File(path));
			
			// root 태그 읽기 => xml (root태그는 table과 같은 역할)
			Element beans = doc.getDocumentElement(); 
			System.out.println(beans.getTagName());
			
			// 같은 이름의 태그를 모아서 데이터 추출
			NodeList list = doc.getElementsByTagName("context:component-scan");
			String pack ="";
			for(int i = 0; i<list.getLength();i++)
			{
				Element elem = (Element)list.item(i);
				pack = elem.getAttribute("basepackage");
			}
			System.out.println(pack);
			clsList = ComponentScan.componentScan(file.getPath(), pack); // 실행하면 모델클래스의 파일을 다 읽어온다
			
		}
		catch (Exception ex) {}
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 사용자 요청을 받기 시작 => URL 주소
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length()+1);

		 
		/* http://localhost:8080/JSPMVCLastProject/main/main.do
		 * -> main/main.do가 필요함
		 * -> uri눈 /JSPMVCLastProject/main/main.do까지 잘라서 가져온다
		 * -> JSPMVCLastProject  => getContextPath()
		 * 
		 * => 우리는 최종적으로 main/main.do 혹은 food/list.do food/detail.do로 변경해서 실행하는 걸 원함
		 */
		
		System.out.println(uri);
		
		try
		{
			// model 클래스 안에서 리퀘스트맵핑을 찾기 => 그 다음 메소드를 호출 시키기
			for(String cls:clsList)
			{
				// 메모리 할당
				Class clsName = Class.forName(cls);
				
				// 클래스 위에 @Controlller 존재 여부 확인
				if(clsName.isAnnotationPresent(Controller.class)==false)
						continue; // @Controller가 없는 경우는 제외
				
				// @Controller = > Model
				// 메모리 할당
				Object obj = clsName.getDeclaredConstructor().newInstance();
				
				// 요청한 기능 찾기 => 메소드
				Method[] methods = clsName.getDeclaredMethods();
				
				// 모든 메소드를 가지고 온다
				for(Method m:methods)
				{
					RequestMapping rm = m.getAnnotation(RequestMapping.class);
					if(rm.value().equals(uri)) // 사용자가 보내준 값과 리퀘스트맵핑 괄호의 값과 같냐
					{
						String jsp = (String)m.invoke(obj, request, response);
						
						if(jsp==null)
						{
							// 자바스크립트 -> Ajax
							return;
						}
						else if(jsp.startsWith("redirect:"))
						{
							// sendReddirect => 리퀘스트 초기화 후 화면 변경
							jsp = jsp.substring(jsp.indexOf(":")+1);
							response.sendRedirect(jsp);
							// return "redirect:list.do"
						}
						else
						{
							// forward => 리퀘스트를 유지하고 화면 변경
							RequestDispatcher rd = request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						
						return;
					}
				}
				
				
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}

}
