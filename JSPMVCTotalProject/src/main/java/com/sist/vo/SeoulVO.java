package com.sist.vo;

import lombok.Data;

/*
 * seoul_Location(명소)

   NO      NOT NULL NUMBER         
   TITLE   NOT NULL VARCHAR2(200)  
   POSTER  NOT NULL VARCHAR2(500)  
   MSG     NOT NULL VARCHAR2(4000) 
   ADDRESS NOT NULL VARCHAR2(300)  
   HIT              NUMBER 

 */
// 패키지 세가지를 넘겨주는게 Controller의 역할 => 우리는 spring.jar 라이브러리로 만들어 뒀기 때문에 그 작업이 간단해진 상황
@Data
public class SeoulVO {

	private int no, hit;
	private String title, poster, msg, address;
}
