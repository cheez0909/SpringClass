/**
 * 1. 작성자 : Dani
 * 2. 작성일 : 12월 11일
 * 3. DataSource 객체를 이용해서 DB연결 테스트 하기
 */
package config;

import models.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.beans.Transient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// 테스트 환경에서 환경설정

//  Spring Test Context 프레임워크와 Junit5와 통합해 사용
@ExtendWith(SpringExtension.class)

// AppConfig클래스는 테스트를 위한
// 애플리케이션 컨텍스트 구성을 담당합니다.
// 설정 파일을 테스트파일에도 사용하겠다는 의미
// get빈으로 컨테이너를 만들지 않아도됨
@ContextConfiguration(classes = AppCtx.class)
class DbConfigTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void 연결테스트(){
        try {
        Connection connection = dataSource.getConnection();
            System.out.println(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("추가 테스트")
    void insertTest(){
        String sql = "INSERT INTO MEMBER (USER_NO, USER_ID, USER_PW, USER_NAME, EMAIL)"
                +" VALUES(SEQ_MEMBER.nextval, ?, ?, ?, ?)";

        int affectedRows = jdbcTemplate.update(sql, "USER04", "123456", "사용자04", "test@org.test");
        System.out.println(affectedRows);
    }

    @Test
    @DisplayName("목록 출력 테스트")
    void selectTest(){
        String sql = "SELECT * FROM MEMBER";

        // 람다식 : 함수명 생략 매개변수타입 생략
        // 매개변수, 리턴값만 있으면 됨
        List<Member> members = jdbcTemplate.query(sql, this::mapper);
        members.stream().forEach(System.out::println);
    }
        private Member mapper(ResultSet rs, int i) throws SQLException{
           return Member.builder()
                    .userNo(rs.getLong("USER_NO"))
                    .userId(rs.getString("USER_ID"))
                    .userPw(rs.getString("USER_PW"))
                    .userName(rs.getString("USER_NAME"))
                    .email(rs.getString("EMAIL"))
                    .regDt(rs.getTimestamp("REG_DT").toLocalDateTime())
                    .build();
        }


    @Test
    @DisplayName("INSERT 후 시퀀스 번호 추출")
    void insertTest2(){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRows = jdbcTemplate.update( con -> {
                String sql = "INSERT INTO MEMBER (USER_NO, USER_ID, USER_PW, USER_NAME, EMAIL) \n" +
                        "VALUES (SEQ_MEMBER.nextval, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"USER_NO"});
                preparedStatement.setString(1, "USER201");
                preparedStatement.setString(2, "123456");
                preparedStatement.setString(3, "사용자201");
                preparedStatement.setString(4, "USER199@test.org");
                return preparedStatement;
            }
        , keyHolder);
//        keyHolder.getKey();  반환 타입이 NUMBER임
        Long key = keyHolder.getKey().longValue();
        System.out.println(key);
    }


    @Test
    @Transactional
    void 데이터_1건_조회(){
        String userId = "USER20100";
        String sql = "SELECT * FROM MEMBER \n" +
                "WHERE USER_ID = ?";
        try {
            Member member = jdbcTemplate.queryForObject(sql, this::mapper, userId);
            System.out.println(member);
        } catch (DataAccessException e){
            e.printStackTrace();
        }
    }

    /**
     * 수치가 있는 데이터를 조회할 때
     */
    @Test
    @DisplayName("통계 데이터 조회")
    void selectTest3(){
        String sql = "SELECT count(*)\n" +
                "FROM MEMBER";
        Long l = jdbcTemplate.queryForObject(sql, long.class);
        System.out.println(l);
    }
}