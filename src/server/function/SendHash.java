package server.function;


import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import server.thread.ClientThread;


/*		
	// 로그인 1101
	// 채팅 1201
	// 업로드 1301
	// 목록 1302
	// 다운로드 1303
*/	


//완성된 HashMap을 클라언트에게 보내줄 함수 ( send 함수를 가진 클래스 ) 1명

public class SendHash {
	ClientThread clientT;
	HashMap response;
	
	public SendHash(ClientThread clientT ,HashMap response) {
		this.clientT = clientT;
		this.response = response;
	}
	
	public void send() {
		try {
			clientT.oout.writeObject(response);
		} catch (IOException e) {
			System.out.println(clientT.socket.getRemoteSocketAddress()+" 클라이언트로 데이터 전송 실패 !!!");
			try {
				System.out.println(clientT.socket.getRemoteSocketAddress()+" 클라이언트 접속 종료!!!");
				clientT.in.close();
				clientT.out.close();
				clientT.oout.close();
				clientT.oin.close();
				clientT.socket.close();
			} catch (Exception e1) {
			}
			synchronized(clientT.accept.clients) {
				clientT.accept.clients.remove(clientT);
			}
		}
	}
	
}	

//		// 반복자 생성
//		Iterator<String> it = keys.iterator();
//		
//			while (it.hasNext()) {
//				
//				String key = it.next();
//				// Set의 key 값을 하나씩 key에 대입
//				
//				Object value = sendM.get(key);
//				// 해당 key에 해당하는 value 대입 / 오토 언박싱
//				
//				System.out.println(key + " : " + value);
//				
//			}

		// 요놈 대신에 ... 각 if 문안에 ... val 값에 메인에서 오는 값을 입력한다.
/*
		for ( int i = 0; i < 3 ; i++ ) {
			 val = (int)(Math.random()*3);
		}
*/	
/*		
		System.out.println(val);
		
		
		if ( val == 0 ) {
			sendM.put("protocol", 1101);
		} else if ( val == 1) {
			sendM.put("protocol", 1201);
		} else {
			sendM.put("protocol", 1301);
		}
		
		System.out.println("값이 몇개여 ~" + sendM.size());
		System.out.println("뭔 값이여 !?" + sendM);
	}
*/	
//	public SendHash() { //자리에... 어떤 값으로 대체....하여 오버라이드...
		
		
//	}
			

	

	

