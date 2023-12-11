package exam01;

import java.sql.*;

public class DBConnection02 {
    public static void main(String[] args) throws ClassNotFoundException{
        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String id = "spring6";
        String pw = "_aA123456";
        String sql = "INSERT INTO MEMBER " +
                "(USER_NO,USER_ID,USER_PW,USER_NAME,EMAIL) " +
                "VALUES (SEQ_MEMBER.nextval, ?, ?, ?, ?)";

        // PreparedStatement 동적 쿼리 처리
        // 캐싱 함
        try(Connection conn = DriverManager.getConnection(url, id, pw);
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"USER_NO"})) {
            pstmt.setString(1, "USER03");
            pstmt.setString(2, "123456");
            pstmt.setString(3, "사용자03");
            pstmt.setString(4, "USER03@test.org");
            int i = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                long aLong = rs.getLong(1);
                System.out.println(aLong);
            }

            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

