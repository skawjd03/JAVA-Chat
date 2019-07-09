package server.function;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import server.thread.ClientThread;

public class ListClass {
	HashMap<String, Object> request;
	ClientThread clientT;
	public ListClass(HashMap<String, Object> request,ClientThread clientT) {
		this.request =request;
		this.clientT = clientT;
	}
	
	public HashMap<String,Object> listProc(){
		HashMap<String, Object> response = new HashMap<String, Object>();
		
		File file = new File("src/server/upload/");
		File[] files = file.listFiles();
		ArrayList<String> v = new ArrayList();
		for(int i = 0;i<files.length;i++) {
			v.add(files[i].getName());
		}
		System.out.println(clientT.socket.getRemoteSocketAddress()+" 클라이언트가 요청한 파일리스트 완성");
		response.put("protocol", 1302);
		response.put("fileList", v);
		
		return response;
	}

}
