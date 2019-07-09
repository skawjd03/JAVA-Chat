package client.thread;

import java.io.File;
import java.util.HashMap;

import javax.swing.JOptionPane;

import client.ConnectClass;
import client.form.ChattingForm;
import client.form.DownloadForm;
import client.form.LoginForm;
import client.function.ListClass;
import client.function.LoginClass;

public class RecvThread extends Thread{
	ConnectClass con;
	LoginForm login;
	ChattingForm chat;
	public RecvThread(ConnectClass con,LoginForm login) {
		this.con = con;
		this.login = login;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				HashMap<String , Object> map = (HashMap<String , Object>)con.oin.readObject();
				if(map.isEmpty()) {
					break;
				}
				switch((int)map.get("protocol")) {
				
				case 1101:
					if(new LoginClass(login,map).loginProc()) {
						JOptionPane.showMessageDialog(login, "로그인성공");
						login.setVisible(false);
						chat = new ChattingForm(con);
					}else {
						JOptionPane.showMessageDialog(login, "로그인실패");
					}
					break;
				
				case 1201:
					chat.area.append((String)map.get("msg")+"\r\n");
					break;
				
				case 1301:
					if((boolean)map.get("isSuccess")==true) {
						JOptionPane.showMessageDialog(login, "파일업로드 성공");
					}else {
						JOptionPane.showMessageDialog(login, "파일업로드 실패");
					}
					break;
				
				case 1302:
					new ListClass(chat,map).setList();
					break;
			
				case 1303:
					File dir = new DownloadForm(chat).downloadForm();
					if(dir!=null) {
						new DownloadBar(dir,chat,map).start();
					}
					break;
				}
			}
		} catch (Exception e) {
			try {
				System.out.println("[ 서버응답 오류 ] ");
				System.out.println("[ 접속종료 ]");
				con.in.close();
				con.out.close();
				con.oin.close();
				con.oout.close();
				con.socket.close();
			} catch (Exception e1) {}
		}
	}

}
