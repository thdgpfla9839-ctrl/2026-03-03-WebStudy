create table reply(
 no NUMBER,
 fno NUMBER,
 id VARCHAR2(20),
 name VARCHAR2(51) CONSTRAINT re_name_nn not null,
 msg CLOB CONSTRAINT re_msg_nn not null,
 regdate DATE DEFAULT sysdate,
 CONSTRAINT re_no_pk PRIMARY key(no),
 CONSTRAINT re_fno_fk FOREIGN key(fno)
 REFERENCES food(no),
 CONSTRAINT re_id_fk FOREIGN key(id)
 REFERENCES member(id)

);

insert into reply VALUES(1,1,'user01','홍길동','댓글....',sysdate);

drop table reply;

SELECT * from member;
-- 멤버에 'hong'이 없어서 맞춰서 user01로 변경함

DELETE FROM  member
WHERE id='hong'; 
