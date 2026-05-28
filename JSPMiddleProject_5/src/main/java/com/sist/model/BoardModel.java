package com.sist.model;
import java.text.SimpleDateFormat;
/*
 * Controller : 처리된 데이터를 jsp로 전송 => servlet => 스프링 안에서 이미 생성됨
 * Model : 최근에는 여기에서 데이터 처리 => 일반 java로 만듦 => 백엔드 담당
 * View :jsp => JSTL / EL ==> 화면 출력(데이터 처리 + 화면 ui)
 *       => 프론트(자바스크립트) 담당
 *       
 * => 이걸 모아서 MVC
 * => 뷰와 모델을 나눠서 작업을 하니 분업이 편하고 확장성 굿 보안 굿
 */
import java.util.*;
import com.sist.dao.*;

import jakarta.servlet.http.HttpServletRequest;
public class BoardModel {

	 // 요청 시 jsp로 출력에 필요한 데이터만 전송을 한다
	// jsp는 출력만 하면 됨
	public void boardList(HttpServletRequest request)
	{
		// 자바 => jsp로 데이터 전송
		// 1. request  2. session(공통으로 사용되는 데이터 전송) => 데이터 유지할 때(개인정보 저장, 장바구니.....)
		
		// 1. 사용자의 요청정보를 받는다
		String page = request.getParameter("page");
		if(page==null)
	     	page ="1"; // 디폴트 페이지 => jsp에서 소스코딩 => jsp에서 자바 소스를 최소화
		int curpage = Integer.parseInt(page);
		
		// 2. 요청받은 값을 이용해서 데이터베이스 연동
		BoardDAO dao = BoardDAO.newInstance();
		List<BoardVO> list = dao.boardListData(curpage);
		int count = dao.boardRowCount(); // count는 총개수를 의미하는데 순차적으로 출력하려면 no가 아닌 count로 잡아야 한다
		int totalpage = (int)(Math.ceil(count/10.0));
		count = count-((curpage*10)-10); // 순차적 숫자로 출력하기 위함 
		// 뭘 순차적으로 출력하냐면 게시판 새글 번호들을
		
		/*
		 * 예) count == >12
		 *      
		 *       12   다음 페이지로 넘기면 10씩 감소    2
		 *       11                              1
		 *       10                    
		 *       9
		 *       ...
		 *       ...
		 *       ...
		 *       ...
		 *       ...
		 *       ..
		 *       3
		 */
		
		
		// 3. 연동이 되면 데이터베이스에서 가지고 온 데이터를 jsp로 전송한다 => controller가 해주는 역할
		// 값을 전송할 때는 request.setAttribute("키",값) / session.setAttribute("키",값)를 이용하면 된다
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("count", count); // jsp로 전송하는 내용/ 괄호안 앞이 키, 뒤가 값
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date())); // 작성일에 오늘 날짜
		
		// 가공이 없다, 그럼 jsp는 받은 데이터만 출력하면 됨(가공은 자바에서 처리했음)
		// 이제 list.jsp로 가서 출력하자
		
		
		
		
		
		
	}
}
