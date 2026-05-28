-- 답변형 게시판(묻고 답하기)
create table jspReplyBoard(
   no NUMBER,
   name VARCHAR2(51) CONSTRAINT jrb_name_nn not null,
   subject VARCHAR2(4000) CONSTRAINT jrb_sub_nn not null,
   content CLOB constraint jrb_cont_nn not null,
   pwd VARCHAR2(10) constraint jrb_pwd_nn not null,
   regdate DATE default sysdate,
   hit NUMBER default 0,
   group_id NUMBER,
   group_step NUMBER default 0,
   group_tab NUMBER default 0,
   root NUMBER default 0,
   depth NUMBER default 0,
   constraint jrb_no_pk PRIMARY key(no)
);

/*
   group_id NUMBER, => 답변끼리 모아서 관리할 목적
   group_step NUMBER default 0, => 답변을 하게되면 출력순서가 있는데 출력 순서를 지정
   group_tab NUMBER default 0, => depth는 답변 공백 1 답변 답변(칸간격)
   root NUMBER default 0, => 어떤 게시물의 답변인지 삭제용
   depth NUMBER default 0, => 답변 개수
   
   => 목적 : 트랜젝션 처리를 하기 위해
   
                             no      group_id   g_s  g_t   root    depth 
 aaaa 새글                    1         1        0    0      0        2
  => bbbbb 답변               2         1        1     1     1        1
    => ccccc 답변의 답변       3         1        2     2     2        0
   =>kkkkkkk                 6         1        3      1     1         0
eeeee                        4         2        0     0     0         1
 =>fffffff                   5         2        1     1     4         0
 
 => depth가 0일 때 삭제가능
 
 
 기본 사이트 : 회원가입, 로그인처리, 회원수정, 회원탈퇴는 기본으로 만듦
             게시판 => 댓글형 게시판
             공지사항, 묻고 답하기 (관리자모드에서 만듦)
             목록출력 / 상세보기
             예약으로 갈지, 추천으로 갈지, 결제 .....
             추가한다면 채팅, 실시간 상담, 이메일 전송 .......
*/
-- 자동증가
create SEQUENCE jrb_no_seq
   start with 1
   INCREMENT by 1
   NOCACHE
   NOCYCLE;
   
   
   -- 새글
   insert into jspreplyBoard(no,name,subject,content,pwd,group_id)
   VALUES(jrb_no_seq.nextval,'홍길동','MV=JSTL,EL학습중',
   '중심적인 내용: EL/JSTL/DBCP/트랜잭션','1234',1);
    insert into jspreplyBoard(no,name,subject,content,pwd,group_id)
   VALUES(jrb_no_seq.nextval,'홍길동','MV=JSTL,EL학습중',
   '중심적인 내용: EL/JSTL/DBCP/트랜잭션','1234',2);
    insert into jspreplyBoard(no,name,subject,content,pwd,group_id)
   VALUES(jrb_no_seq.nextval,'홍길동','MV=JSTL,EL학습중',
   '중심적인 내용: EL/JSTL/DBCP/트랜잭션','1234',3);
    insert into jspreplyBoard(no,name,subject,content,pwd,group_id)
   VALUES(jrb_no_seq.nextval,'홍길동','MV=JSTL,EL학습중',
   '중심적인 내용: EL/JSTL/DBCP/트랜잭션','1234',4);
    insert into jspreplyBoard(no,name,subject,content,pwd,group_id)
   VALUES(jrb_no_seq.nextval,'홍길동','MV=JSTL,EL학습중',
   '중심적인 내용: EL/JSTL/DBCP/트랜잭션','1234',5);
   
   -- 답변(댓글)
    insert into jspreplyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab)
   VALUES(jrb_no_seq.nextval,'홍길동','MV=JSTL,EL학습중',
   '중심적인 내용: EL/JSTL/DBCP/트랜잭션','1234',5,1,1);
    insert into jspreplyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab)
   VALUES(jrb_no_seq.nextval,'홍길동','MV=JSTL,EL학습중',
   '중심적인 내용: EL/JSTL/DBCP/트랜잭션','1234',5,2,2);
   
   COMMIT;