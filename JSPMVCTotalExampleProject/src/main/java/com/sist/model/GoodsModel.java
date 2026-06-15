package com.sist.model;

import java.util.*;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;

import com.sist.vo.*;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class GoodsModel {

	private String[] table= {
			"",
			"goods_all",
			"goods_best",
			"goods_new",
			"goods_special"
	}; 
	
	private String[] title= {
			"",
			"전체 상품",
			"인기 상품",
			"신상품",
			"특가 상품"
	};
	
	@RequestMapping("goods/list.do")
	public String goods_list(HttpServletRequest request, HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
		String tno = request.getParameter("tno");
		if(tno==null)
			tno="1";
		
		int curpage = Integer.parseInt(page);
		Map map = new HashMap();
		map.put("table", table[Integer.parseInt(tno)]);
		map.put("start", (curpage)*12);
		
		List<GoodsVO> list=GoodsDAO.goodsListData(map);
		int totalpage = GoodsDAO.goodsTotalPage(map);
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("tno", tno);
		request.setAttribute("title", title[Integer.parseInt(tno)]);
		
		
		return "../goods/list.jsp";
	}
	
	
}
