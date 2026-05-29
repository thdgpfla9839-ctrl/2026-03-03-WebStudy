package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public class ListModel {
	public void execute(HttpServletRequest request) 
	{
        String msg="게시판 목록";
        request.setAttribute("msg", msg); // 이제 디테일모델로 넘어가
	}
}
