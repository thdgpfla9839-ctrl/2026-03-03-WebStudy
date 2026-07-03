package com.sist.model;

import java.util.*;
import com.sist.vo.*;
import com.sist.manager.*;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class NewsModel {

	@RequestMapping("news/find.do") // 요청할 때만 뉴스 뿌려주는
	public String news_find(HttpServletRequest request, HttpServletResponse response)
	{
		String fd = request.getParameter("fd");
		if(fd==null)
		   fd="축구";
		List<NewsVO> list = NewsManager.newsFindData(fd);
		request.setAttribute("list", list);
		
		
		request.setAttribute("main_jsp", "../news/find.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("news/news.do") // 사이트와 관련된 뉴스만 보내주는
	public String news_news(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		request.setAttribute("main_jsp", "../news/news.jsp");
		return "../main/main.jsp";
	}
}
