package server.thread;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import server.function.ChatClass;
import server.function.DownloadClass;
import server.function.ListClass;
import server.function.LoginClass;
import server.function.SendHash;
import server.function.UploadClass;
public class ClientThread extends Thread{
	public AcceptThread accept; // AcceptThread
	
	public Socket socket; // 소켓
	
	public String name; // 소켓 접속한 사람 이름
	
	// IO 스트림
	public InputStream in;
	public OutputStream out;
	public ObjectInputStream oin;
	public ObjectOutputStream oout;
	
	HashMap<String,Object> response;

	
	public ClientThread(AcceptThread accept, Socket socket) throws Exception{
		this.accept = accept;
		this.socket = socket;
		
		in = socket.getInputStream(); // inputStream 초기화
		out = socket.getOutputStream();// outputStream 초기화
		oin = new ObjectInputStream(in); // 초기화
		oout = new ObjectOutputStream(out); // 초기화
		System.out.println("스트림 생성 완료");
		
	}
	
	@Override
	public void run() {
		System.out.println(socket.getRemoteSocketAddress()+" 클라이언트 작업 시작!!!");
		try {
			while(true) {
				HashMap<String , Object> map = (HashMap<String , Object>)oin.readObject();
				if(map.get("protocol") == null) {
					break;
				}
				System.out.println("요청이 들어옴");
				int protocol = (int)map.get("protocol");
				switch(protocol) {
				
				case 1101: 
					System.out.println("로그인 요청");
					response = new LoginClass(this,map).loginProc();
					new SendHash(this,response).send();
					break;
				
				case 1201:
					response = new ChatClass(this,map).chatProc();
					synchronized(accept.clients){
						for(int i =0;i<accept.clients.size();i++) {
							new SendHash(accept.clients.get(i),response).send();
						}
					}
					break;
				case 1301:
					response = new UploadClass(this,map).uploadProc();
					new SendHash(this,response).send();
					break;
				
				case 1302:
					response = new ListClass(map,this).listProc();
					new SendHash(this,response).send();
					break;
			
				case 1303:
					response = new DownloadClass(this,map).downloadProc();
					new SendHash(this,response).send();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(socket.getRemoteSocketAddress()+" 클라이언트가 채팅방을 나갔습니다.");
			try {
				in.close();
				out.close();
				oin.close();
				oout.close();
				socket.close();
			} catch (Exception e1) {}
			synchronized(accept.clients) {
				accept.clients.remove(this);
			}
		}
	}


}


