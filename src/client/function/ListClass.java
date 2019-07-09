package client.function;

import java.util.ArrayList;
import java.util.HashMap;

import client.form.ChattingForm;

public class ListClass {
	//ArrayList<String> list = new ArrayList<String>();
	HashMap<String,Object> response;
	ChattingForm chat;
	public ListClass(ChattingForm chat,HashMap<String,Object> response) {
		this.response = response;
		this.chat =chat;
	}
	
	public void setList() {
		chat.listBox.removeAllItems();
		ArrayList<String> list =(ArrayList<String>) response.get("fileList");
		for(int i = 0;i<list.size();i++) {
			chat.listBox.addItem(list.get(i));
		}
	}

}
