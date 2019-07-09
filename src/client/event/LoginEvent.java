package client.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import client.form.LoginForm;

public class LoginEvent implements ActionListener{
	LoginForm loginF;
	public LoginEvent(LoginForm loginF) {
		this.loginF = loginF;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("로그인 이벤트");
		char[] pwC = loginF.pwF.getPassword();
		String pw = new String(pwC,0,pwC.length);
		if(loginF.idF.getText().length()==0) {
			JOptionPane.showMessageDialog(loginF, "아이디를 입력하세요.");
		}else if(pw.length()==0) {
			JOptionPane.showMessageDialog(loginF, "비밀번호를 입력하세요.");
		}
		HashMap<String,Object> request = new HashMap<String,Object>();
		request.put("protocol", 1101); // 로그인 요청
		request.put("id", loginF.idF.getText());
		request.put("pw", pw);
		try {
			System.out.println("[ 서버로 로그인 요청 보냄 ]");
			loginF.con.oout.writeObject(request);
		} catch (IOException e1) {
			System.out.println("[ 서버가 종료 되었습니다. ]");
			try {
				loginF.con.in.close();
				loginF.con.out.close();
				loginF.con.oin.close();
				loginF.con.oout.close();
				loginF.con.socket.close();
			} catch (IOException e2) {
			}
		}
	}

}
