package com.sist.model;
/*
 *  ajax는 비동기로 사용 안 한다
 *  
 */
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
		   String name=request.getParameter("name");
		   String subject=request.getParameter("subject");
		   String content=request.getParameter("content");
		   String pwd=request.getParameter("pwd");
		  
		   
		   BoardVO vo=new BoardVO();
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
	
	@RequestMapping("board/delete_vue.do")
	public void board_delete_vue(HttpServletRequest request, HttpServletResponse response)
	{
		// detail.jsp에 데이터 전송 부분을 여기다 받는 과정
		String no =request.getParameter("no");
		String pwd = request.getParameter("pwd");
		
		// 데이터베이스에 연동한다 디비는 2개가 나온다
		boolean bCheck = BoardDAO.boardDelete(Integer.parseInt(no), pwd);
		String msg="";
		if(bCheck==true)
		{
			msg = "yes"; // 이렇게 되면 목록으로 이동
		}
		else
		{
			msg = "no"; // 이거면 비번이 틀렸다는 소리
		}
		
		// vue로 전송
		try
		{
			// 일반 문자열을 보낼 때는 text/html
			// JSON을 보낼 떄는 text/plain으로 전송한다
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(msg);
		}
		catch (Exception ex) {}
	}
	
	@RequestMapping("board/update.do")
	public String board_update(HttpServletRequest request, HttpServletResponse response)
	{
		return "../board/update.jsp"; // 수정하기 기능 처리까지 다 완성 이제 리스트에서 실행해봐
	}
	
	// 화면 이동도하고 이전에 데이터(수정 전 게시글 내용)까지 불러오게끔 하는 과정
	@RequestMapping("board/update_vue.do")
	public void board_update_vue(HttpServletRequest request, HttpServletResponse response)
	{
		String no = request.getParameter("no");
		BoardVO vo = BoardDAO.boardUpdateData(Integer.parseInt(no));
		
		try
		{
			ObjectMapper mapper =new ObjectMapper();
			String json=mapper.writeValueAsString(vo);
			
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json);
		}
		catch (Exception ex) {}
	}
	
	// 게시글 수정 후 수정 버튼 눌러서 실제 수정이 되게끔 하는 기능
	@RequestMapping("board/update_ok.do")
	public void board_update_ok(HttpServletRequest request, HttpServletResponse response)
	{
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content"); 
		String pwd = request.getParameter("pwd");
		String no = request.getParameter("no");
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setPwd(pwd);
		vo.setContent(content);
		vo.setNo(Integer.parseInt(no)); // 여기까지하면 mapper에 #부분에 값이 채워진다
		
		// db연동
		String msg="no";
		
		boolean bCheck=BoardDAO.boardUpdate(vo);
		if(bCheck==true)
		{
			msg="yes";
		}
		
		try
		{
			// 일반 문자열을 보낼 때는 text/html
			// JSON을 보낼 떄는 text/plain으로 전송한다
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(msg);
		}
		catch (Exception ex) {}
	}
}
