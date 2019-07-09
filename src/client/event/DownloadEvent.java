package client.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import client.form.ChattingForm;

public class DownloadEvent implements ActionListener{
	ChattingForm chat;
	public DownloadEvent(ChattingForm chat) {
		this.chat = chat;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String fileName = (String)chat.listBox.getSelectedItem();
		HashMap<String,Object> request = new HashMap<String,Object>();
		request.put("protocol", 1303);
		request.put("fileName", fileName);
		try {
			chat.con.oout.writeObject(request);
		} catch (IOException e1) {
			System.out.println("다운로드 요청 오류!!!");
			try {
				chat.con.in.close();
				chat.con.out.close();
				chat.con.oin.close();
				chat.con.oout.close();
				chat.con.socket.close();
			} catch (IOException e2) {
			}
		}
	}

}
