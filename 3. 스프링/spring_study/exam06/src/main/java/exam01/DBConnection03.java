package exam01;

import java.sql.*;
import java.time.LocalDateTime;

public class DBConnection03 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String id = "spring6";
        String pw = "_aA123456";
        String sql = "SELECT * FROM MEMBER";


        try(Connection conn = DriverManager.getConnection(url, id, pw);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()){
                long userNo = rs.getLong("USER_NO");
                String userId = rs.getString("USER_ID");
                String userName = rs.getString("USER_NAME");
                LocalDateTime regDt = rs.getTimestamp("REG_DT").toLocalDateTime();
                System.out.println(userNo+" "+userId+" "+userName+" "+regDt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*
* ResultSet
*   - getDate(...) : 날짜
*   - getTime(...) : 시간
*   - getTimestamp(...) : 날짜와 시간
* */