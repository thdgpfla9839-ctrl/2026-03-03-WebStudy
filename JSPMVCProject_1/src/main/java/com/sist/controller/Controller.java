package com.sist.controller;
// 실행은 컨트롤러 파일에서 실행시켜야 한다
// 동작하는 방법을 봄 실행브라우저에 ?cmd=list 여기 부분이 바뀐거
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;

import com.sist.model.DeleteModel;
import com.sist.model.DetailModel;
import com.sist.model.InsertModel;
import com.sist.model.ListModel;
import com.sist.model.UpdateModel;

/*
 * 서블릿 동작과정 => 3개의 메소드가 실행됨
 * 1. init() : 환경설정 => web.xml
 * 2. service() :1) doGet(): get방식 => sendRediret(), <a>, location.href  
 *               2) doPost(): post 방식 => <form>, ajax, vue, react
 * 3. destory() : 메모리가 해제
 * 
 * => doGet + doPost = service
 *    여기서 서비스는 두가지가 동시에 적용된 상태
 *    
 * -------------------------------------------------------------------------
 * 컨트롤러 클래스는 값을 받아오는? 읽어오는 곳   
 * 
 * -------------------------------------------------------------------------
 * 브라우저가 요청 ==================== controller ===============해당 model을 찾는다
 *                                                                  |
 *                                                              처리된 결과를 가지고 온다
 *                                                                  |
 *                                                                  jsp에 전송 : 두가지 처리 방법이 있음 => 1. 공통으로 처리 : session에 담는다
 *       [mvc 구조]                                                                                   2. jsp마다 처리 : reqest에 담아서
 *  브라우저 : jsp 사용자(손님) => 주문받기(요청)
 *  Controller : servlet 서빙 => 오더 / 응답
 *  Model : 자바로 만듦 => 주방      
 *                                                                                                 
 */

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void init(ServletConfig config) throws ServletException {
		
		// model클래스 메모리 할당
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 주문을 받는다 : 요청
		// http://localhost:8080/JSPMVCProject_1/Controller?cmd=list 이렇게 주소를 보낼거래 => cmd=list 부분이 사용자가 요청하는 값이 들어가는 부분
		String cmd = request.getParameter("cmd");
		if(cmd==null)
		   cmd ="list";
		// 2. 주문에 해당되는 model 클래스 = 메소드 찾기
		String jsp = "";
		if(cmd.equals("list"))
		{
			ListModel model = new ListModel();
			model.execute(request);
			jsp = "view/list.jsp";
		}
		else if(cmd.equals("detail"))
		{
			DetailModel model = new DetailModel();
			model.execute(request);
			jsp = "view/detail.jsp";

		}
		else if(cmd.equals("insert"))
		{
			InsertModel model = new InsertModel();
			model.execute(request);
			jsp = "view/insert.jsp";

		}
		else if(cmd.equals("update"))
		{
			UpdateModel model = new UpdateModel();
			model.execute(request);
			jsp = "view/update.jsp";

		}
		else if(cmd.equals("delete"))
		{
			DeleteModel model = new DeleteModel();
			model.execute(request);
			jsp = "view/delete.jsp";

		}
		// 3. 요청 처리 => model이 가지고 있는 메소드 호출
		// 4. jsp를 찾는다
		// 5. reqest나 session에 결과값을 담아서 보낸다
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
		
	}

}
