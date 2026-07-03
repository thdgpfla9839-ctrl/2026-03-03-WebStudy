package com.sist.vo;
import java.util.*;
/* 
 * 이름      널?       유형           
------- -------- ------------ 
NO      NOT NULL NUMBER       
CNO              NUMBER       
RNO              NUMBER       
ID               VARCHAR2(20) 
NAME    NOT NULL VARCHAR2(51) 
MSG     NOT NULL CLOB         
REGDATE          DATE 
 */
import lombok.Data;
@Data
public class ReplyVO {

	private int no,cno,rno;
	private String id,name,msg,dbday,uMsg;
	private Date regdate;
}
