package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 인터페이스의 역할 : 서로 다른 클래스를 한개로 묶어서 사용 => 스프링에서 주로 사용
// 클래스가 데이터명이 달라서 if문을 전부 써야 하는데 그걸 방지하기 위해서
public interface Model {

	// 요청 후 요청 처리
	// 데이터 통합
	public String requestHandler(HttpServletRequest request,
		      HttpServletResponse response);
 // Model에 요청처리 => 요청 내용(request), 응답 (response)
}

