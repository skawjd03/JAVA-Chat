package client.form;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.ConnectClass;
import client.event.LoginEvent;


public class LoginForm extends JFrame{
	public JTextField idF;
	public JPasswordField pwF;
	JButton loginBtn;
	JLabel logoL;
	public ConnectClass con;
	
	public LoginForm(ConnectClass con) {
		this.con = con;
		
		// 제이프레임창 레이아웃 지정
		this.setLayout(new BorderLayout());
		
		// 아이디랑 비밀번호 라벨 생성
		JLabel idL = new JLabel("아이디   : ");
		JLabel pwL = new JLabel("비밀번호 : ");
		
		// 아이디랑 비밀번호를 입력받을 창 생성
		idF = new JTextField();
		pwF = new JPasswordField();
		LoginEvent lEvt = new LoginEvent(this);
		pwF.addActionListener(lEvt);
		
		// 로그인 버튼 ( 이벤트처리 필요!~!~~~~~~~~~~!~!~!)
		loginBtn = new JButton("Login");
		loginBtn.addActionListener(lEvt);
		
		// 라벨을 세로로 정렬되도록 함
		JPanel p1 = new JPanel(new GridLayout(2,1));
		p1.add(idL);
		p1.add(pwL);
		// 입력창을 세로로 정렬되도록 함
		JPanel p2 = new JPanel(new GridLayout(2,1));
		p2.add(idF);
		p2.add(pwF);
		
		// 라벨패널과 입력창패널과 로그인을 가로로 나란히 정렬
		JPanel p3 = new JPanel(new BorderLayout());
		p3.add("West",p1);
		p3.add("Center",p2);
		p3.add("East",loginBtn);
		
		// 그림객체
		logoL = new JLabel(new ImageIcon("C:\\Form\\image\\kanye.jpg"));

		// 메인 제이프레임창에 위치 지정
		this.add("Center",logoL);
		this.add("South",p3);
		this.setSize(300,400);
		this.setVisible(true);
	}

}
