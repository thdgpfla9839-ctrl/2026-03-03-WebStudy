SELECT no,poster,name 
FROM food
     WHERE type LIKE '%한식%' 
     OFFSET 12 ROWS FETCH NEXT 12 ROWS ONLY;
          
-- OFFSET 뒤 숫자는 0 12 24 ... 이런식으로 시작돼야한다 왜냐 페이지 번호 자르는 거기 때문에
-- 12부터 시작하면 결과가 이상해서 order by를 붙여야 한다

desc food;

-- 인덱스 만들어 주기 전 제약조건 만듦 
-- pk를 만들떄 약식으로 만들지 말고 이름을 붙여서 쓰자 지금처럼
alter table food add CONSTRAINT food_no_pk PRIMARY key(no);

SELECT * FROM food
where 'address' like '%마포%'; -- 이건 자바 sql 코드에서 ?가 들어갈때의 sql 형태