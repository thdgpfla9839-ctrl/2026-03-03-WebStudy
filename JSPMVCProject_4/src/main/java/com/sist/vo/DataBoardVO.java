package com.sist.vo;
import java.util.*;

import lombok.Data;
/*
 *  마이바티스는 컬럼명과 반드시 일치 시켜야 한다
 *  
 *  컬럼명인 네임을 setname을 갖고 데이터를 찾는다
 *  컬럼명과 일치하지 않으면 세터가 없어서 읽지를 못해 그래서 별칭을 주게 되면 코드 길이가 길어지니까 컬럼명과 일치하는게 가장 편하다
 *  마이바티스에선 while문 rs.next 이런게 없어질거야
 */
@Data
public class DataBoardVO {

	
	private int no,hit,filesize;
	private String name,subject,content,pwd,filename,dbday;
	private Date regdate;
}
