package server.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import DB.CJDBC;
import server.thread.ClientThread;

public class LoginClass {
	HashMap<String,Object> request;
	ClientThread clientT;
	
	public LoginClass(ClientThread clientT ,HashMap<String,Object> request) {
		this.request = request;
		this.clientT = clientT;
	}
	
	public HashMap<String,Object> loginProc() {
		HashMap<String,Object> response = new HashMap<String,Object>();
		CJDBC db = new CJDBC();
		Connection con = db.getCon();
		String sql = "select * from member where m_id = ? and m_pw = ? ";
		PreparedStatement pstmt = db.getPSTMT(sql);
		
		boolean isSuccess=false;
		try {
			pstmt.setString(1, (String)request.get("id"));
			pstmt.setString(2, (String)request.get("pw"));
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			if(rs.getString("m_name")==null) {
				isSuccess=false;
			}else {
				clientT.name = rs.getString("m_name");
				isSuccess = true;
			}
		} catch (Exception e) {
			isSuccess = false;
			System.out.println(clientT.socket.getRemoteSocketAddress()+" 클라이언트의 로그인 요청 오류 !!!");
		}
		
		response.put("protocol", 1101);
		response.put("isSuccess", isSuccess);
		
		return response;
	}

}
