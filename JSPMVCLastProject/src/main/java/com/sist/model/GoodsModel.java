package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class GoodsModel {

	@RequestMapping("goods/list.do") // 괄호 값이 중복이면 에러나서 폴더명까지 적어줘라
	public String goods_list(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../goods/list.jsp");
		return "../main/main.jsp";
	}
}
