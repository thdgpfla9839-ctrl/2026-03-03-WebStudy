package com.sist.vo;
/*
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
// 댓글 한개에 대한 정보를 갖고 있음 => 여러개를 갖고 오려면 List이용 / 한개는 ReplyVO 이용
public class ReplyVO {

	private int no,fno;
	private String id,name,msg,dbday;
	private Date regdate;
}
