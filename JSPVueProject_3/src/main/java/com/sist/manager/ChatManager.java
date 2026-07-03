package com.sist.manager;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.ChatVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.*;
@ServerEndpoint(value="/chat",configurator = WebSocketSessionConfigurator.class) // 리퀘스트맵핑과 똑같음, 환경설정한 것임
public class ChatManager {

	// 접속자 저장을 해야함 => 이 함수는 set()과 똑같아 => 중복이 없는 접속자 등록이 된다
	private static Map<Session,ChatVO> users=
			// 동기화 => 여러 사용자가 동시에 접속할 시 안전하게 저장할 수 있게끔 => synchronizedMap
			Collections.synchronizedMap(new HashMap<Session,ChatVO>()); // 동기화 방식으로 저장하는 방법 => 끝난다음 다음사람이 저장됨
	
	// 접속시 처리하는 부분
	// onOpen => 클라이언트가 웹소켓서버에 연결됐을 때 자동 호출되는 함수
	@OnOpen // 접속시 처리되는 어노테이션 => 어떤 메소드가 호출이 되는지 구분하는 '구분자'
	public void onOpen(Session session, EndpointConfig config) // 여기 세션은 본인
	throws Exception
	{
		// httpSession 연결하는 부분
		ChatVO vo = new ChatVO();
		HttpSession  hs=(HttpSession)config.getUserProperties().
				get(HttpSession.class.getName());// 여기는 웹소켓 세션이라 우리가 저장한 내용을 바로 못가져옴
		vo.setId((String)hs.getAttribute("id"));
		vo.setName((String)hs.getAttribute("name"));
		vo.setSession(session);
		
		// 접속한 클라이언트가 저장되는 위치
		users.put(session, vo);
		
		// 입장 메시지 
		Iterator<Session> it=users.keySet().iterator();
		while(it.hasNext())
		{
			Session ss = it.next();
			if(ss.getId()!=session.getId()) // ss 즉 본인이 아니면
			{
				
				ss.getBasicRemote().sendText("msg:[🔔알림]"+vo.getName()+"님 입장하셨습니다");
			}
		}
		// 누가 접속했는지 확인
		System.out.println("클라이언트 접속:"+vo.getId()); // 실제 아이디 출력
		System.out.println("클라이언트 접속(Session):"+session.getId()); // 세션 출력
	}
	
	// 입장 메시지 날리기 => 접속된 모든 사람한테 날리는 메시지
	@OnMessage
	public void onMessage(String message, Session session) throws IOException
	{
		System.out.println("수신된 메시지:"+message+","+users.get(session).getName());
		Iterator<Session> it=users.keySet().iterator();
        
		while(it.hasNext())
		{
			Session ss = it.next();
			ChatVO vo =users.get(session);
			
			// 입장 메시지 전송하는 부분
			ss.getBasicRemote().sendText("msg:[🔔알림]"+vo.getName()+"님 입장하셨습니다");
			
		}
	}
	// 접속 해제
	@OnClose
	public void onClose(Session session) throws Exception // 세션은 접속한 사람의 모든 정보를 갖고 있다, 세션이 실제 연결되는 대상
	{
		Iterator<Session> it=users.keySet().iterator();
		
		// 데이터를 모아서 한번에 처리
		while(it.hasNext())
		{
			// session : 접속자 마다 부여된 번호를 갖고 있다
			Session ss = it.next(); // 전체 접속자
			ChatVO vo = users.get(session); // 나가는 사람
			
			if(ss.getId()!=session.getId())
			{
				ss.getBasicRemote().sendText("msg:[🔔알림]"+vo.getName()+"님 퇴장하셨습니다");

			}
			
		}
		System.out.println("클라이언트 해제(ID):"+users.get(session).getName());
		System.out.println("클라이언트 해제(Session):"+session.getId());
		
		// 접속자 명단에서 삭제하는 부분
		users.remove(session); 

	}
	// 에러 처리
	@OnError
	public void onError(Session session, Throwable ex)
	{
		ex.printStackTrace();
	}
}
