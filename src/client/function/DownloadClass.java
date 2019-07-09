package client.function;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import client.form.ChattingForm;
import client.form.UploadForm;

public class DownloadClass {
	HashMap<String,Object> response;
	ChattingForm chat;
	public DownloadClass(HashMap<String,Object> response,ChattingForm chat) {
		this.response = response;
		this.chat = chat;
	}
	
//	public void downloadProc() {
//		String fileName = (String)response.get("fileName");
//		ArrayList<byte[]> fileInfo = (ArrayList<byte[]>)response.get("file");
//		File filePath = new UploadForm(chat).downloadForm();
//		try {
//			FileOutputStream fout = new FileOutputStream(filePath.getPath()+"\\"+fileName);
//			for(int i =0;i<fileInfo.size();i++) {
//				fout.write(fileInfo.get(i));
//			}
//			fout.close();
//			JOptionPane.showMessageDialog(chat, "파일다운로드 성공");
//		}catch(Exception e) {
//			
//		}
//	}
//
}
