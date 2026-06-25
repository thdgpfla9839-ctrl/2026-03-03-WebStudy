package com.sist.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.io.PrintWriter;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BoardModel {

	// => Router : 화면만 이동 
    // => 실제 처리 => Front에서
	@RequestMapping("board/list.do")
	public String board_list(HttpServletRequest request,
			   HttpServletResponse response)
	   {
		   return "../board/list.jsp";
	   }
	
	@RequestMapping("board/list_vue.do") // 화면이동하는 부분이랑 뷰에서 처리하는 부분은 달라야 한다
	public void board_list_vue(HttpServletRequest request,
			   HttpServletResponse response)
	   {
		   String page=request.getParameter("page");
		   int curpage=Integer.parseInt(page);
		   int start=(curpage*10)-10;
		   List<BoardVO> list=BoardDAO.boardListData(start);
		   int count=BoardDAO.boardRowCount();
		   int totalpage=(int)(Math.ceil(count/10.0));
		   count=count-((curpage*10)-10);
		   
		   Map map=new HashMap();
		   map.put("curpage", curpage);
		   map.put("count", count);
		   map.put("totalpage", totalpage);
		   map.put("list", list);
		
		
		// 자바스크립트에서 json으로 넘기는 방식
		   try
		   {
			    ObjectMapper mapper=new ObjectMapper();
			    String json=mapper.writeValueAsString(map);
			    
			    response.setContentType("text/plain;charset=UTF-8");
			    PrintWriter out=response.getWriter();
			    out.write(json);
			    
			    // AJAX , VUE / REACT
		   }catch(Exception ex) {}
	   }
	
	@RequestMapping("board/insert.do")
	public String board_insert(HttpServletRequest request, HttpServletResponse response)
	{
		 // 화면 이동 예정
		return "../board/insert.jsp";
	}
	
	@RequestMapping("board/insert_ok.do")
	public void board_insert_ok(HttpServletRequest request, HttpServletResponse response)
	{
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content"); // 괄호 안에 값은 insert.jsp 파일 속 명칭과 일치 시켜야 한다
		String pwd = request.getParameter("pwd");
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setPwd(pwd);
		vo.setContent(content);
		
		BoardDAO.boardInsert(vo);
	}
	
	// 화면 변경
	@RequestMapping("board/detail.do")
	public String board_detail(HttpServletRequest request, HttpServletResponse response)
	{
		return "../board/detail.jsp";
	}
	
	// 화면이 변경되면 vue에서 자동으로 띄우는?
	@RequestMapping("board/detail_vue.do")
	public void board_detail_vue(HttpServletRequest request, HttpServletResponse response)
	{
		String no =request.getParameter("no");
		BoardVO vo =  BoardDAO.boardDetailData(Integer.parseInt(no));
		try
		{
			ObjectMapper mapper =new ObjectMapper();
			String json=mapper.writeValueAsString(vo);
			
			
			// json 보내는 형식
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json);
		}
		catch (Exception ex) {}
	}
}
