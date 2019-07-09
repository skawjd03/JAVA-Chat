package client.function;

import java.util.HashMap;

import javax.swing.JOptionPane;

import client.form.LoginForm;

public class LoginClass {
	LoginForm login;
	HashMap<String,Object> response;
	public LoginClass(LoginForm login,HashMap<String,Object> response) {
		this.login = login;
		this.response = response;
	}
	
	public boolean loginProc() {
		boolean bool = false;
		if((boolean)response.get("isSuccess")==true) {	
			bool = true;
			System.out.println("로그인 성공");
		}else {
			bool = false;
		}
		
		return bool;
	}

}
