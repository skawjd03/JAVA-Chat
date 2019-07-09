package server.function;

import java.util.ArrayList;
import java.util.HashMap;

import client.form.ChattingForm;
import server.thread.*;

public class ChatClass  {
	ClientThread client;
	HashMap<String, Object> request;
	public ChatClass(ClientThread client,HashMap<String, Object> request) {
		this.client = client;
		this.request = request;
	}
	
	public HashMap<String,Object> chatProc() {
		
		String msg = "[ "+ client.name + " ] " + (String)request.get("msg");
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("protocol", 1201);
		map.put("msg", msg);
		
		return map;
	
	}

}
