<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.bean.*"%>
    
<jsp:useBean id="bean" class="com.sist.bean.MemberBean">
<%--
 이 부분은 예전 코드 이런게 있었다 정도로 한번 가볍게 읽기
       MemberBean bean = new MemberBean(); => 클래스 메모리 할당 부분
                                              이거 대신 class="com.sist.bean.MemberBean" 작성가능하다
       id =>객체명
       
 ---------------------------------------------------------------------------------------------------------------
 setProperty를 사용할 수 있는 조건)
    name이라는 속성이 jsp:useBean id="bean"명과 동일해야함
    property="name" => setName()
    property="*" => 모든 setter에 값을 채운다
                 => 정수는 integer.parseInt()    
                 
------------------------------------------------------------------------------------------------------------------
jsp 액션 태그)

 : 자바코드를 직접 쓰지 않고 기능을 수행하게 만드는 표준 태그
 
jsp 액션 태그 사용법)
          <jsp: ~ >
          => xml 형식으로 돼 있다
             그래서 태그나 속성명을 대소문자 구분해줘야 함
             태그나 열리면 반드시 닫아줘야 함
             속성값을 입력할 떄 반드시 ""를 사용한다     
          => 자바 코드가 감소
          => 재사용성이 증가
          => 다른 jsp / Bean 연동이 가능 
          
-----------------------------------------------------------------------------
<jsp:useBean id="member" class="com.sist.bean.Member"> ~ 이 코딩이
   => Member member=new Member()로 대체해서 쓸 수 있다
   그러나 jsp에서만 사용이 가능함
   실무에서는 mvc 구조를 사용해서 이건 사용도 안 함
   
----------------------------------------------------------------------------
 <jsp:setProperty name="member" property="name" value="홍길동">
=> name이 객체가 됨
   member.setName("홍길동")
    => 멤버에 셋 네임에 홍길동을 집어넣어라는 의미
   
<jsp:setProperty name="member" property="*">
=> 멤버 객체에 모든 setter를 호출한 후 값을 채워라는 의미  
=> 리플렉션
----------------------------------------------------------------------------
<jsp:getProperty name="member" property="name">
 => 멤버에 겟네임을 호출해라      
----------------------------------------------------------------------------
자바소스가 갈수록 길어지니까 그걸 방지하기 위해 태그를 만들어 사용한다
=> 그래서 나온게 표준으로 만든 태그인 jsp 액션 태그이다

-------------------------------------------------------------------------------
이 챕터에서 볼 거는 
<jsp:include>만 보면 된다
=> jsp 안에 다른 jsp를 첨부할 떄 사용 
=> 메인 페이지 구조 제작이 가능하다
=> MVC구조 적합 
=> 동적으로 변경 

-------------------------------------------------------------------------------
자바빈의 형태(p.213)
: 단순한 클래스의 형태
  규칙이 필요하다 
  jsp - servlet - DB 전송
  캡슐화 : private / setter / getter =>lombok
  mvc에서도 데이터를 모아서 한번에 전송
  데이터베이스 컬럼과 일치시켜야 한다(desc table명)
  
  ------------------------------------------------------------------------------------
               자바빈                           DTO                   VO
  목적)      jsp 표준 객체                    데이터 전달 객체          불변 => 고정
  규칙)         엄격                            자유로움               자유로움
  사용)      jsp 중심으로                            ----------------------- => 두개는  spring이나 mybatis에서 사용
  
  => 최근에는 대부분 DTO를 사용한다(데이터를 모아서 브라우저로 보내는 목적을 갖고 있음)
  ------------------------------------------------------------------------------------------------------------
  
  <form>
    |
   jsp : bean에 저장
    |
  데이터베이스
    |
  브라우저 

=> bean은 데이터 저장이 핵심이다
   데이터 저장용 클래스이다 (getter / setter)
                 
--%>

  <%-- MemberBean bean=new MemberBean();
       클래스 메모리 할당 
       id=>객체명
   --%>
  <jsp:setProperty name="bean" property="*"/> 
  
  <%-- setProperty 태그를 주면 전체 값을 받을 수 있다 
       name => jsp:useBean id="bean"명 동일 
       property="name" => setName()
       property="*" => 모든 setter에 값을 채운다 
       => 정수 : Integer.parseInt() / 
  
  --%>
</jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>받은 데이터 1</h1>
  이름:<%=bean.getName() %><br>
  성별:<%=bean.getSex() %><br>
  주소:<%=bean.getAddress() %><br>
  전화:<%=bean.getPhone() %><br>
  소개:<%=bean.getContent() %>
  <br>
  나이:<%=bean.getAge() %>
  <hr>
 
 <%--
 <%=bean.getName() %> => bean 객체의 getName()
 받은 데이터와 받은데이터2는 같은 내용
  --%>
  
 <h1>받은 데이터 2</h1>
  이름:<jsp:getProperty name="bean" property="name"><br>
성별:<jsp:getProperty name="bean" property="sex"><br>
  주소:<jsp:getProperty name="bean" property="address"><br>
  전화:<jsp:getProperty name="bean" property="phone"><br>
  소개:<jsp:getProperty name="bean" property="content">
  <br>
  나이:<jsp:getProperty name="bean" property="age">
</body>
</html>