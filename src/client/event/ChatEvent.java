package client.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import client.form.ChattingForm;

public class ChatEvent implements ActionListener{

	ChattingForm chatF;
	
	public ChatEvent(ChattingForm chatF) {
		this.chatF = chatF;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = chatF.field.getText();
		if(msg == null || msg.length() == 0) {
			return;
		}
		
		// map에 담에서 서버에 보내기.
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("protocol", 1201);
		map.put("msg", msg);
		try {
			chatF.con.oout.writeObject(map);
			System.out.println(" 메세지출력확인창 :" +msg);
		} catch (Exception e1) {}
		
		chatF.field.setText("");
	}
	
}
