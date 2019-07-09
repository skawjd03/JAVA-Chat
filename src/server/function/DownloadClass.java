package server.function;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import server.thread.ClientThread;

public class DownloadClass {
	HashMap<String,Object> request;
	ClientThread clientT;
	public DownloadClass(ClientThread clientT,HashMap<String,Object> request) {
		this.clientT=clientT;
		this.request=request;
	}
	
	public HashMap<String,Object> downloadProc() {
		HashMap<String,Object> response = new HashMap<String,Object>();
		response.put("protocol", 1303);
		ArrayList<byte[]> fileInfo = new ArrayList<byte[]>();
		boolean isSuccess = false;
		try {
			FileInputStream fin = new FileInputStream("src/server/upload/"+(String)request.get("fileName"));
			while(true) {
				byte[] buff = new byte[65507];
				int len = fin.read(buff);
				if(len == -1) {
					break;
				}
				fileInfo.add(buff);
			}
			System.out.println(clientT.socket.getRemoteSocketAddress()+" 클라이언트가 요청한 파일 가져오기 성공");
			fin.close();
			response.put("file", fileInfo);
			response.put("fileName", request.get("fileName"));
			isSuccess =true;
		} catch (Exception e) {
			isSuccess = false;
			System.out.println("파일 가져오기 오류!!!");
		}
		response.put("isSuccess", isSuccess);
		return response;
	}

}
