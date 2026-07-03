package com.sist.manager;

import jakarta.websocket.server.ServerEndpoint;

/*   [실시간 뉴스 불러오는 과정]
 *  프로그램 시작(시작하자마자 접속자 저장 => set을 이용해 중복방지 / 저장할 때는 static을 이용해서 한개만 가지고 사용) - 스케쥴러 생성(쓰레드 만들기) 
 *  - 1분마다 실행 - 뉴스 검색(JSON) - 접속자에게 전송 - Ajax로 출력 
 */

/*
 *  Session : WebSocket
 *     세션ID => 고유번호(접속자 구분자) : session.getId()
 *     연결 상태 확인 => close(), open() : session.isOpen()
 *     메시지 전송 기능 => session.getBasicRemote().sendText()
 *     사용자 정보 : 브라우저, 누구인지는 IP => session.getUserPrincpal()을 이용해도 사용자 정보 데이터를 가져올 수 있다
       요청 URL => url을 확인하려면 ws://localhost/JSPProject/news 이런식이다
       접속시간
       사용자 속성 => UserProperties (HttpSession)
       WebSocket 관련 정보
 */

/*
 *   ConcurrentHashMap.newKeySet() : 여러개 쓰레드가 동시 접속 시 종료해도 안전하게 사용할 수 있다 => 안전하게 사용할 수 있는 Set 인터페이스 사용
 */

/*
 *  schedule : 예약된 작업을 수행하는 쓰레드
 *  ScheduledExecutorService : 예약 실행하는 쓰레드 => 1분마다 같은 작업을 수행
 *  Executors : 쓰레드를 쉽게 만들어주는 클래스
 *  newScheduledThreadPool(1) : 예약 작업을 수행하는 쓰레드 1개 생성 => 이 작업을 하게 되면 쓰레드 1개가 대기 - 1분이 지나면 바로 수행 => 이런 방식으로 수행하는 역할을 함 
 *  scheduleAtFixedRate : 이 메소드는 일정 시간마다 반복 실행을 한다 => 실행할 작업 / 처음 대기 시간 / 반복 시간 / 시간 단위 이렇게 4가지를 수행한다
 *  => 자바로 코딩하면 어렵지만 스프링으로 하면 간결해진다 참아라
 */

/*
 *   interface Runnable => scheduleAtFixedRate
 *   {
 *        주석 추가
 *   }
 */
import java.util.*;
import java.util.concurrent.*;
import jakarta.websocket.server.*;
import jakarta.websocket.*;
import java.io.*;
@ServerEndpoint("/news")
public class NewsServer {

	  // 설정파일을 따로 블러올 필요가 없는 파일이라 session을 불러올 필요가 없어
   
	  // 1. 접속자 저장을 한다 => 모든 접속자가 같은 메모리를 사용한다 => 이때 static을 이용
	  // 중복이 없어야 됨 =>  그래서 set 사용
	  // 모든 객체가 하나를 같이 사용 => static
	private static Set<Session> clients = ConcurrentHashMap.newKeySet();
	
	// 쓰레드 생성 => 원하는 시간대마다 보낼 수 있다
	private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	// 자동호출
	static 
	{
		// 람다식을 사용할 때는 메소드가 1개여야 한다
		//                           run() 이 괄호 안에 들어감  
		scheduler.scheduleAtFixedRate(()->{
			try
			{
				// 뉴스 읽어오자
				String json = NewsRealManager.newsFindData("맛집"); // 여기가 60초마다 한번씩 검색하고 들어온다
			
				// 데이터를 접속자 전체에게 전송
				 broadcast(json);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		},0,60,TimeUnit.SECONDS); // 60초면 1분에 한번 씩 새뉴스를 전송해라
	} // 여기까지 서버구동 - static{} 실행 - 스케줄러 생성 - 0초 후 실행 - 검색(NewsRealManager.newsFindData("맛집")) 
	 //         - json생성 후 읽어오기 - 모든 접속자한테 전송 - 전송이 되면 60초 대기 - 대기가 끝나면 처음부터(검색부터) 다시 실행 - 반복
	

	// 2. 접속 시 처리 => Set에 저장
	@OnOpen
	public void onOpen(Session session)
	{
		// session : 사용자 정보임
		clients.add(session);
		System.out.println("클라이언트 접속"+session.getId()); // 세션으로 접속 시는 접속번호만 알 수 있다
	}
	
	// 3. 접속 해제
	@OnClose
	public void onClose(Session session)
	{
		clients.remove(session);
		System.out.println("클라이언트 종료"+session.getId()); 

	}
	
	// 4. 메시지 전송
	@OnMessage
	public void onMessage(String msg, Session session)
	{
		System.out.println("클라이언트 메시지:"+msg);
	}
	
	// Http에서 웹소캣 ws로 변경되는 부분은 handshake를 사용한다
	// handshake가 HttpSession을 웹소캣으로 전송할 때 Configuator를 이용해서 둘을 연결해준다
	// Configuator : 어쩌구 클래스 주석추가하기
	
	// 5. 실제 데이터 출력 => 사용자 정의 함수 부분
	public static void broadcast(String message)
	{
		// 접속한 모든 사람에게
		for(Session session:clients)
		{
			// 접속인 상태냐
			if(session.isOpen())
			{
				try
				{
					session.getBasicRemote().sendText(message);
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
}