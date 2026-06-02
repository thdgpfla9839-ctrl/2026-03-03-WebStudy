package com.sist.model;

import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 메소드를 만들면 어떻게 찾을 까(호출을 해야 수행이 되는데)
// 클래스 찾는 것은 if문을 이용했지만
// if문을 대처할 수 잇는 방법?
/*
 *  클래스 검색 : Map을 이용해서 if문을 제거
 *  
 *  메소드 : 메모리할당 할 수 있는 상태가 아님
 *         1) if문제거 -> 구분자를 줘서 찾는다 => 어노테이션 => if문 없이 메소드를 찾을 수 있게 됨
 *         2) 리플랙션하기 -> 메소드명과 관련없이 자동 검색이 가능하다 => Spring
 *         
 *--------------------------------------------------------------------------------------
 * 찾는 애들을 'controller'라고 함 => 스프링에서 제공하는 거 사용중
 * -------------------------------------------------------------------------------------
 * 이번 프로젝트에서는 MVC 구조 파악하는 것에 신경쓰자
 *                   |
 *                   Model(DAO,VO)
 *                   |
 *                   View(JSP)        
 */
@Controller
public class FoodModel {

	// 기능1. 목록출력
	@RequestMapping("main/main.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response)
	{
		// 사용자가 보낸 데이터 받기 => Page
		String page = request.getParameter("page");
		if(page==null)
			page ="1";
		
		// 현재 페이지 지정 (몇 페이지 읽고 있는지)
		int curpage = Integer.parseInt(page);
		
		// 해당 페이지의 목록을 가져오기 (0부터 12개 가져오기)
		int start =(curpage*12)-12;
		List<FoodVO> list = FoodDAO.foodListData(curpage);
		
		// 토탈페이지 가져오기
		int totalpage = FoodDAO.foodTotalPage();
		
		// 브라우저(jsp)에서 출력할 데이터 전송
		request.setAttribute("list", list); // 리퀘스트로 값을 보내는데 필요한 데이터를 추가해서 보내는중
		request.setAttribute("totalpage",totalpage ); 
		request.setAttribute("curpage",curpage ); 
		
		
		// include하는 jsp 확인
		request.setAttribute("main_jsp", "../food/list.jsp"); // main_jsp를 뒤에로 바꿔라
		
		// 실제 화면 출력하는 부분
		return "../main/main.jsp"; // 여기에 값을 보낸다
	}
	
	// 기능2. 상세보기
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response)
	{
		String msg ="맛집 상세보기";
		
		// 데이터베이스 연동이 되는 부분
		request.setAttribute("msg", msg); // 출력할 데이터 전송하는 부분
		
		// include하는 jsp 확인
		request.setAttribute("main_jsp", "../food/detail.jsp");
		
		// 실제 화면 출력하는 부분
		return "../main/main.jsp"; // 여기에 값을 보낸다
	}
	// 더 만든다면 기타 : 좋아요 / 찜하기 / 예약하기 / 추천 / 근처 맛집 / 길찾기 / 검색 ......... 이정도
}
