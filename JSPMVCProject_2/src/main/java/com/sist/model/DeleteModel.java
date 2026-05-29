package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public class DeleteModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String msg ="게시판 삭제";
		request.setAttribute("msg", msg);
		return "/view/delete.jsp"; // 앞에 슬러쉬를 꼭 붙여야 경로를 찾을 수 있다. 맨앞에 /를 붙이면 경로를 읽어오는 기준이 달라짐
	}

}
