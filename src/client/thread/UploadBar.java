package client.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

import client.form.ChattingForm;

public class UploadBar extends Thread{
	File file;
	JFrame frame;
	JProgressBar p;
	JLabel l1;
	ChattingForm chat;
	
	public UploadBar(File file,ChattingForm chat) {
		this.file = file;
		this.chat = chat;
		frame = new JFrame();
		p = new JProgressBar(0,(int)file.length());
		p.setStringPainted(true);
		p.setValue(0);
		JPanel p1= new JPanel();
		Border border = BorderFactory.createTitledBorder("Reading....");
		p.setBorder(border);
		p1.add(p);
		l1= new JLabel("가져오는 중...");
		JPanel p2 = new JPanel();
		p2.add(l1);
		
		frame.dispose();
		frame.add("South",p2);
		frame.add("Center",p1);
		frame.setSize(300,130);
		frame.setVisible(true);
	}
	
	@Override
	public void run() {
		int i=0;
		ArrayList<byte[]> fileInfo = new ArrayList<byte[]>();
		try {
			FileInputStream fin = new FileInputStream(file);
			while(true) {
				byte[] buff = new byte[65507];
				int len = fin.read(buff);
				if(len==-1) {
					break;
				}
				//System.out.println(file.length() + ":"+buff.length+"="+file.length()/buff.length);
				fileInfo.add(buff);
				i+=buff.length;
				p.setValue(i);
				Thread.sleep(1);
			}
			fin.close();
			l1.setText("파일 업로드 성공 !!!!");
			System.out.println("파일 가져오기 완료");
		} catch (Exception e) {
			System.out.println("파일 업로드 오류!!!");
			return;
		}
		HashMap<String,Object> request = new HashMap<String,Object>();
		request.put("protocol",1301 );
		request.put("file",fileInfo);
		request.put("fileName", file.getName());
		try {
			chat.con.oout.writeObject(request);
		} catch (IOException e) {
			System.out.println("파일 전송 오류!!!");
			try {
				chat.con.in.close();
				chat.con.out.close();
				chat.con.oin.close();
				chat.con.oout.close();
				chat.con.socket.close();
			} catch (IOException e1) {
			}
		}
	}

}
