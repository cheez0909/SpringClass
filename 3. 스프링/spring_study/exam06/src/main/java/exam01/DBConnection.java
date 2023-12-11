package exam01;

import java.sql.*;

public class DBConnection {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String id = "spring6";
        String pw = "_aA123456";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, id, pw);

        } catch (SQLException e) {
            System.out.println("DB연결에 실패했습니다");
        } finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("자원해제에 실패했습니다.");
                }
            }
        }
    }
}
