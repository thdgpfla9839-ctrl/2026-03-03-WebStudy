package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MemberModel {
 
	// 기능1. 로그인(회원가입 / 회원수정 / 회원탈퇴 / 비번변경 )
	// 기타 : 우편번호 / 증복체크 이정도 추가할 수 잇음
	@RequestMapping("member/login.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response)
	{
		String msg ="로그인 처리";
		request.setAttribute("msg", msg);
		request.setAttribute("main_jsp", "../member/login.jsp");
		return "../main/main.jsp"; // 여기에 값을 보낸다
	}
}
