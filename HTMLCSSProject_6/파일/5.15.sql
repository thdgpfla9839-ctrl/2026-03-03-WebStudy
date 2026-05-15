-- 테이블 먼저 작성 - 널처리 - pk잡기 - 자동증가 추가해주기

create TABLE jspBoard(
  no NUMBER GENERATED ALWAYS AS IDENTITY, -- 고유번호라 자동증가 만들어줌 GENERATED ALWAYS AS IDENTITY
  name VARCHAR2(51) constraint jb_name_nn not NULL,
  subject VARCHAR2(200) constraint jb_sub_nn not NULL,
  content CLOB constraint jb_cont_nn not NULL,
  pwd VARCHAR2(10) constraint jb_pwd_nn not NULL,
  regdate date DEFAULT sysdate,
  hit NUMBER DEFAULT 0,
  CONSTRAINT jb_no_pk primary key(no)
);

insert INTO jspBoard(name,subject,content,pwd) -- 테이블에 값을 집어넣는 것이니 당연히 테이블명과 동일하게 적어야지 (insert into table_name)
VALUES('홍길동',
       '오라클-데이터베이스의 CRUD'
       ,'브라우저 = HTML이용 = jsp',
       '1234'
 );

SELECT * FROM jspboard;
desc jspBoard;
commit;