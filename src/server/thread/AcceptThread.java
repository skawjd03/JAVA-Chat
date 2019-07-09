package server.thread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.ChatServer;

public class AcceptThread extends Thread{

	ChatServer main;
	ServerSocket server;
	public ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	// 채팅
	
	public AcceptThread(ChatServer main,ServerSocket server) {
		this.main = main;
		this.server = server;
	}
	
	@Override
	public void run() {
		System.out.println("::: 클라이언트 접속대기 시작 :::");
		while(true) {
			String ip="";
			try {
				Socket socket = main.server.accept();
				ip = socket.getRemoteSocketAddress().toString();
				System.out.println("[ 클라이언트 접속 ] - "+socket.getRemoteSocketAddress());
				ClientThread ct = new ClientThread(AcceptThread.this,socket);
				clients.add(ct);
				ct.start();
			} catch (Exception e) {
				System.out.println("[ "+ip+" ] 클라이언트 접속 오류");
			}
		}
	}
}
