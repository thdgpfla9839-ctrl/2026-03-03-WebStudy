package com.sist.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
/*
 * 1. 서블릿 : 순수하게 자바 중심의 웹 프로그램 => 단점) 수정 시 마다 컴파일을 해서 톰캣에 올려줘야 함
 *                                            수정 후 바로 확인이 불가
 *                                            소스가 길어짐
 *                                            css나 자바스크립트 처리가 어렵다
 *                                            
 *                                     => 장점) 소스가 노출되지 않는다(보안이 굿)
 *                                             자바 중심이라 호환성, 유지 보수가 좋다
 *                                             확장성이 좋다
 *                                             
 *  2. JSP : HTML 중심, 화면출력에 중점 => 장점) 서블릿의 단점을 보완       
 *                                         수정 후 바로 수행 가능
 *                                         톰캣에 의해 컴파일 시 바로 실행이 가능
 *                                         소스가 짧아진다(HTML과 java를 구분)
 *                                         HTML 중심 -> css나 자바스크립트 바로 사용이 가능
 *                                         
 *                                 => 단점) 소스가 노출된다
 *                                         확장성이 안 좋다
 * ---------------------------------------------------------------------------------------
 * 최근 경향) 보안이나 로직이 필요한 경우 => 서블릿
 *          화면출력 => jsp를 사용한다
 *          
 *-------------------------------------------------------------------------------------------
 * 1. 서블릿의 생명주기 :
 *                      init() : 변수의 초기화(생성자를 대체, db 연결 준비, 설정파일 읽기할 때)
 *                      |
 *                      service() : 클라이언트의 요청 시 마다 실행(화면 출력에 주력, GET / POST 동시에 처리, 요청시에는 한개만 수행) => doGet() : 요청시에 GET방식인 경우 화면출력 / doPost():요청시 POST방식인 경우 사용자 요청 처리   이렇게 두가지로 나뉨
 *                      |
 *                      destory() : 서버 종료
 *                                  새로고침
 *                                  화면이동
 *                                  => 자동으로 서블릿이 제거가 된다
 *                                  
 * 동작하는 과정)
 *         
 *    클라이언트 요청(브라우저에서 실행)
 *         |
 *         서블릿 객체 생성
 *         |
 *         init()호출 (한번만 수행) => _jspInit()
 *         |
 *         요청 시 마다 service()생성 => _jspService()
 *         |
 *         응답처리
 *         |
 *         서버종료 시 destory() 호출 => _jspDestory()   
 *         
 * --------------------------------------------------------------------------------
 * 
 * 1. 동적 컨텐츠(p.17)
 * 2. 웹 동작(p.18)
 * 3. servlet / jsp(p.22)
 * 4. jsp란
 * 5. jsp 특징
 * 6. 서블릿의 이해
 * => 교재 1장
 * => 2장 건너띄고 3장 순으로.....                                                                                   
 *                                                                                       
 */


@WebServlet("/EmpServlet") // 구분자 => 사용자에 따라 변경이 가능하다
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		 System.out.println("init()호출");
	}

	public void destroy() {
		// TODO Auto-generated method stub
		 System.out.println("메모리 해제");
	}
///////////////////////////////////////doGet / doPost영역에 소스 추가가 되는 것이 jsp
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// default => 바로 실행되며 get방식이 호출됨 => HTML 폼
		// 글쓰기 폼
		// 실행 후 변환
		
		/*
		 * 변환 과정
		 * 1. HTML변환 => text/html
		 * 2. XML변환  => text/xml
		 * 3. JSON 변환 => text/json
		 * 
		 */
		// 응답하는 타입 설정
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		// out영역에 html을 저장하면 해당 브라우저에서 읽어간다
		
		out.write("<html>");
		out.write("<body>");
		out.write("<h1>헬로 서블릿</h1>");
		out.write("</body>");
		out.write("</html>");
		// 단점 => jsp로 변경
		
		/*
		 * 1. 역할: 웹 서브스 기능을 처리하는 자바 클래스 => 서블릿
		 *    현재는 Controller 역할
		 *    => Controller :자바와 html을 묶어 주는 역할
		 *                   스프링은 이미 만들어져 있음 라이브러리에
		 *                   DispatcherServlet
		 *                   
		 * 2. 자바 안에 html 코드를 첨부해서 사용
		 *    => out.write("<html>");
		 *    => html 사용이 어렵다, css/ 자바스크립트 사용이 어렵다
		 *       html / css / 자바스크립트 에러처리가 어렵다
		 *       실행 시 마다 컴파일을 해서 사용 -> 바로 결과를 볼 수가 없다
		 *       소스를 볼 수 없다(.class) -> 보안은 뛰어남
		 *       
		 * 3. 서블릿의 단점 보완
		 *    jsp => html 즁삼
		 *    : 자바 사용시에는 스크립트릿을 이용
		 *      <% %> <%= %> 
		 *      
		 * 4. jsp
		 *    : 서블릿 보다 html 작성이 쉽다
		 *      복잡한 내용은 자바와 html을 구분해서 사용을 해야한다 => 보안한 게 jstl /el을 이용
		 *      
		 * 5. jsp 실행과정
		 * 
		 *   a.jsp : 브라우저에 요청
		 *    |
		 *    a._jsp.java 
		 *    |
		 *    a._jsp.class
		 *    |
		 *    실행 : out.write()
		 */
		
		/*
		 * <html>
           <head>
           <meta charset="UTF-8">
           <title>Insert title here</title>
           </head>
           <body>

           </body>
           </html>
		 */
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 처리 시에 사용
		// _ok.jsp
	}

}
