package server.function;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import server.thread.ClientThread;

public class UploadClass {
	HashMap<String,Object> request = new HashMap<String,Object>();
	ClientThread clientT;
	
	public UploadClass(ClientThread clientT ,HashMap<String,Object> request) {
		this.request = request;
		this.clientT = clientT;
	}
	
	public HashMap<String,Object> uploadProc() {
		HashMap<String,Object> response = new HashMap<String,Object>();
		ArrayList<byte[]> fileInfo = (ArrayList<byte[]>)request.get("file");
		String fileName = (String)request.get("fileName");
		boolean isSuccess = false;
		
		try {
			FileOutputStream fout = new FileOutputStream("src/server/upload/"+fileName);
			int num =0;
			// 5000/100=50;
			for(int i = 0;i<fileInfo.size();i++) {
				fout.write(fileInfo.get(i));
				if(i%(int)(fileInfo.size()/100)==0) {
					if(num==100) {	
						System.out.println("파일 업로드 "+100+"% ...");
					}else if(num<100) {
						System.out.println("파일 업로드 "+(++num)+"% ...");		
					}else {
						
					}
				}
			}
			System.out.println("파일 업로드 성공");
			isSuccess = true;
			fout.close();
		} catch (Exception e) {
			isSuccess = false;
			System.out.println(clientT.socket.getRemoteSocketAddress()+" 클라이언트가 보낸 파일저장 오류 !!!");
		}
		response.put("protocol", 1301);
		response.put("isSuccess", isSuccess);
		return response;
	}

}
