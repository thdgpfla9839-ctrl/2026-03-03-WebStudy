package com.sist.vo;
// 일반 댓글 관련
/*
 *  ----------------------------------------- -------- ----------------------------
 NO                                        NOT NULL NUMBER
 FNO                                                NUMBER
 ID                                                 VARCHAR2(20)
 NAME                                      NOT NULL VARCHAR2(51)
 MSG                                       NOT NULL CLOB
 REGDATE                                            DATE
 */
import java.util.*;

import lombok.Data;
@Data
public class ReplyVO {

	// 이제 마이바디스가 자동으로 처리해준대
	private int no,fno;
	private String id,name,msg,dbday;
	private Date regdate;
}
