package com.sist.model;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 브라우저로 전송하는 클래스 => 모델
// 여기만 유일하게 컨트롤러와 연결이 됨

// Model의 역할
// => 요청받기 - 요청처리(DAO연결,Open API)- 결과값을 session 혹은 request에 담아서 JSP로 전송한다
// Model을 찾는 애는 Controller가 해준다

/*
 *  데이터 전송 시 => request.setAttribute() / session.setAttribute()
 *                -> 그외에는 이걸로              -> 이건 사용자 정보, 장바구니, 예약 정보를 보낼 때 사용
 *                -> 한개의 JSP에서만 사용이 가능   -> 프로그램 종료 시까지 기억됨
 *                                            -> 모든 JSP에서 사용이 가능
 */
@Controller
public class SeoulModel {
	
	private String[] table= {
			"",
			"seoul_location",
			"seoul_nature",
			"seoul_shop",
			"seoul_hotel"
	};
	
	private String[] title= {
			"",
			"서울 명소",
			"서울 자연",
			"서울 쇼핑",
			"서울 호텔"
	};
	
	@RequestMapping("seoul/list.do")
	public String seoul_list(HttpServletRequest request, HttpServletResponse response)
	{
		
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
			String tno=request.getParameter("tno"); // tno는 테이블 번호
			if(tno==null)
				tno="1";
			
			int curpage = Integer.parseInt(page); // 현재 페이지 지정됨
			Map map = new HashMap(); // 오라클에서 값을 가지고 온다
			map.put("table", table[Integer.parseInt(tno)]); // mapper.xml 파일에 ${table}에 값을 넣는 작업
			map.put("start", (curpage*12)-12); // mapper.xml 파일에 #{start}에 값을 넣어주는 작업 
			List<SeoulVO> list=SeoulDAO.seoulListData(map);
			int totalpage=SeoulDAO.seoulTotalPage(map);
			
			request.setAttribute("list", list);// 출력 대상인 데이터를 전송
			request.setAttribute("curpage", curpage);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("tno", tno);
			request.setAttribute("title", title[Integer.parseInt(tno)]); // 이값을 가지고 화면 출력하러 서울 폴더 list.jsp로 가자
			
			return "../seoul/list.jsp"; // request를 받는 jsp를 지정
		
	}
@RequestMapping("seoul/detail.do") // detail.do라는 명령이 들어오면 이 밑에 메소드를 처리해라 
// 리퀘스트맵핑은 조건문 대신 사용하거나 메소드 구분자로 사용된다
// 어노테이션 안에 리퀘스트 맵핑이야
// 어노테이션은 구분자로서 인덱스의 역할을 해줘 빠르게 찾기가 가능해진다
public String seoul_detail(HttpServletRequest request, HttpServletResponse response)
{
	String no=request.getParameter("no");
	String tno=request.getParameter("tno"); // 어떤 값을 받았는지 확인 차 변수명도 일치시킨 것 뿐이야
	
	Map map=new HashMap();
	map.put("no", Integer.parseInt(no));
	map.put("table", table[Integer.parseInt(tno)]);
	
	SeoulVO vo=SeoulDAO.seoulDetailData(map);
	
	// 값을 jsp로 전송
	request.setAttribute("vo", vo);
	request.setAttribute("tno", tno); // 디테일.jsp 파일 만들러 가자
	
	return "../seoul/detail.jsp";
}
}
