create table mvcDataBoard(
  no NUMBER,
  name VARCHAR2(51) CONSTRAINT mdb_name_nn not null,
  subject VARCHAR2(2000) CONSTRAINT mdb_sub_nn not null,
  content CLOB CONSTRAINT mdb_cont_nn not null,
  pwd VARCHAR2(10) CONSTRAINT mdb_pwd_nn not null,
  regdate DATE DEFAULT sysdate,
  hit NUMBER DEFAULT 0,
  filename VARCHAR2(260),
  filesize NUMBER,
  CONSTRAINT mdb_no_pk PRIMARY key(no)
  
);

create SEQUENCE mdb_no_seq
  START with 1
  INCREMENT by 1
  NOCACHE
  NOCYCLE;
  
INSERT into mvcDataBoard
VALUES(mdb_no_seq.nextval,'홍길동',
'MyBatis 기초: 실무에서 사용하는 기술',
'MyBatis 기초: 실무에서 사용하는 기술',
'1234',sysdate,0,'',0);
  
COMMIT;  