package com.sist.dao;
/*
 * NO      NOT NULL NUMBER         
   NAME    NOT NULL VARCHAR2(51)   
   SUBJECT NOT NULL VARCHAR2(2000)   
   CONTENT NOT NULL CLOB           
   PWD     NOT NULL VARCHAR2(10)   
   REGDATE          DATE           
   HIT              NUMBER         
----------------------------------------
 * 
 * 
 * 매칭되는 상태
 * 한개의 게시물 저장할 목적
 * 사용자의 요청이 오면 한번에 전송을 해야한다
 * 
 * 
 * -------------------------------------
 * 
 * 오라클과 자바의 매칭)
 *     
 *     오라클                    자바
 *     CHAR
 *     VARCHAR2
 *     CLOB                   String
 *     
 *     NUMBER                  int
 *     NUMBER(2,1)             double
 *     
 *     DATE                    java.util.Date
 */
import java.util.*;

import lombok.Data;
@Data // 롬복이 적용 안 되면 마우스 우클릭 - 소스 - 게터세터 선택 후 직접 넣어버리자....그렇지만 롬복이용하는게 코드가 깔끔
public class BoardVO {
  private int no,hit;
  private String name,subject,content,pwd,dbday;
  private Date regdate;
}