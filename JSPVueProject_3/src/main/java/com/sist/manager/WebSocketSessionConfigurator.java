package com.sist.manager;
//크롬하고 파이어폭스 두개를 이용해서 채팅확인한다
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import jakarta.websocket.server.ServerEndpointConfig.Configurator;
/*
 *  이 파일은 웹소켓 연결 시 HttpSession을 웹소캣으로 전닳하기 위한 클래스
 * 
 */
public class WebSocketSessionConfigurator extends Configurator{

	// WebSocket 연결 직전에 자동으로 호출되는 메소드 => Handshake(유선에서 무선으로 연결) => http를 ws로 변경해줌
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		// http 요청에서 HttpSession을 가지고 온다
		HttpSession session = (HttpSession)request.getHttpSession();
		// 로그인이 된 경우
		if(session!=null)
		{
			// 웹소켓에 UserProperties에 저장
			// onclose(), onopen(), onmessage() => HttpSession 사용이 가능함
			// properties에 세션을 저장할 때 
			sec.getUserProperties().put(HttpSession.class.getName(), session); // 여기에 등록돼 있는 아이디를 이용하기 위해 
			
		}
		// 접속자 마다 구분 => 0 ......
		/*
		 *  웹소켓에서는 HttpSession을 직접 사용 할 수 없다
		 *   => 따라서 UserProperties에 저장 후 접근 가능하게 설정한다
		 *   
		 *   http가 웹소켓으로 연결(Handshake)되는 순간에 실행되는 클래스 
		 *   =======================================================
		 *   
		 *   브라우저에서 http 요청 - httpSession 생성 - 웹소켓 연결 요청 - modifyHandshake 이게 호출되면 - request.getSession(), UserProperties에 저장
		 *   - @onOpen 호출 - UserProperties에 저장된 httpSession을 갖고 온다
		 */
	}

}
