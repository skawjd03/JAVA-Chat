package DB;

import java.sql.*;
public class CJDBC {
   private Connection con;
   public CJDBC() {
      try {
    	 // 오라클용 DB드라이버 로딩
         Class.forName("oracle.jdbc.driver.OracleDriver");
         // 오라클 서버 url
         String url = "jdbc:oracle:thin:@localhost:1521:xe";
         // 오라클 접속할 id
         String user = "increpas";
         // 오라클 접속할 비밀번호
         String password = "increpas";
         
         // url로 접속후 세션 반환
         con = DriverManager.getConnection(url, user, password);
         
      } catch(Exception e) {
         System.out.println("※※※※※※※※※※※※※※ [DB 연결 실패] ※※※※※※※※※※※※※※※");
      }
   }
   
   // Statement 를 요구하면 반환해주는 함수 ( 김태형 아가리짝 1스택)
   public Statement getSTMT() {
      Statement stmt = null;
      try {
         stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      } catch(Exception e) {
         e.printStackTrace();
      }
      return stmt;
   }
   
   // PreparedStatement 를 요구하면 반환해주는 함수
   public PreparedStatement getPSTMT(String sql) {
      PreparedStatement pstmt = null;
      try {
         pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      } catch(Exception e) {
         e.printStackTrace();
      }
      return pstmt;
   }
   
   // 객체를 사용을 하면 언제가는 닫아줘야 할 것이다.
   // stmt 또는 pstmt 를 사용이 끝나면 닫아줘야 할 것 이고
   // 그 닫는 기능을 함수로 만들어 놓자.
   public void close(Object o) {
      // Connection, Statement, PreparedStatement 를 모두 담을 수 있는 타입은 Object 이므로
      // 매개변수는 Object 타입으로 한다 ==> 함수의 재사용성이 높아진다.
      try {
         if(o instanceof Connection) {
            ((Connection)o).close();
         } else if(o instanceof Statement) {
            ((Statement)o).close();
         } else if(o instanceof PreparedStatement) {
            ((PreparedStatement)o).close();
         } else if(o instanceof ResultSet) {
            ((ResultSet)o).close();
         }
      } catch(Exception e) {}
   }

   public Connection getCon() {
      return con;
   }

   
}
