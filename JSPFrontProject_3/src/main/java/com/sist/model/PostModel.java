package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;
import com.sist.dao.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller // 컨트롤러를 해줘야 모델 클래스인지 알고 찾을 수 있음
public class PostModel {

	@RequestMapping("post/postfind.do")
	public String postfind(HttpServletRequest request, HttpServletResponse response)
	{
		String dong=request.getParameter("dong");
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		int count = 0;
		if(dong==null)
			count=0;
		else 
		{
		  count = ZipcodeDAO.postFindCount(dong);
		  list = ZipcodeDAO.postFind(dong);
		}
		
		// 값 전송
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		return "postfind.jsp";
	}
}
