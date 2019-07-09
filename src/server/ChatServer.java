package server;

import java.net.InetAddress;
import java.net.ServerSocket;

import server.thread.AcceptThread;

public class ChatServer {
	
	public ServerSocket server;  // 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 ( 占쏙옙화占쏙옙 )
	
	public ChatServer() {
		try {
			server = new ServerSocket(8888);
			System.out.println("[ "+InetAddress.getLocalHost()+" ] - 서버 ON");
		}catch(Exception e) {
			System.out.println("::: 서버 오류 :::");
			System.exit(0);
		}
		new AcceptThread(this,server).start();
	}

	public static void main(String[] args) {
		new ChatServer();
	}

}
