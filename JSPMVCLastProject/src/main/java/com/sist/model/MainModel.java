package com.sist.model;
// 여기 코드 다시 수정하기
import com.sist.controller.Controller;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainModel {
	@RequestMapping("main/main.do")

	public String main_main(HttpServletRequest request, HttpServletResponse response) 
	{
		// 화면 목록 
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		
		// 현재 페이지 지정
		int curpage = Integer.parseInt(page);
		
		// 데이터베이스에서 출력할 데이터를 가지고 온다
		int start =(curpage*12)-12;
		List<FoodVO> list= FoodDAO.foodListData(start);
		int totalpage = FoodDAO.foodTotalPage();
		
		// 블록별 페이지 처리
        final int BLOCK = 10;
        int starPage = ((curpage-1)/BLOCK*BLOCK)+1; // 스타트페이지는 시작하는 페이지를 의미하는 게 아냐 페이지번호 맨앞을 의미
        int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
        if(endPage>totalpage)
        	endPage=totalpage;
        
        // 데이터를 브라우저로 전송        
        request.setAttribute("list", list);
        request.setAttribute("curpage", curpage );
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("startPage", starPage); // 앞은 모델명이고 뒤에는 jsp명이다
        
        
		request.setAttribute("main_jsp", "../main/home.jsp");
		// include
		return "../main/main.jsp";
	}
}
