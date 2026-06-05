package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface RequestMapping {
   
	// 구분자를 만들어 준다
	public String value();
	
	/*
	 *  어노테이션 : 기능이 없어 단디 인덱스 역할임
	 *  -> 어노테이션은 if문 추가라고 생각하자
	 *  -> 리플랙션을 이용하기 떄문에 개발자가 메소드명이나 클래스명을 마음대로 지정할 수 있다
	 */
   
}
