package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
* 자동으로 자원을 해제해준다
* 해제할 자원이 AutoCloseable 인터페이스의 구현체만 가능하다.
* */
public class DBConnection01 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String id = "spring6";
        String pw = "_aA123456";

        try(Connection conn = DriverManager.getConnection(url, id, pw);
       Statement stmt = conn.createStatement()) {
            // 해킹에 굉장히 취약함
            // sql이 주입되면서 악의적인 코드가 유입될 수 있음
            // 잘 사용하지 않음
            String sql = "INSERT INTO MEMBER " +
                    "(USER_NO,USER_ID,USER_PW,USER_NAME,EMAIL) " +
                    "VALUES (SEQ_MEMBER.nextval, 'USER01', '123456', " +
                    "'사용자1', 'user01@test.org')";
            int i = stmt.executeUpdate(sql);
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
