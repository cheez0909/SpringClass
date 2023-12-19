package tests;

import configs.ControllerConfig;
import configs.DbConfig;
import configs.MvcConfig;
import controllers.member.RequestJoin;
import models.member.JoinService;
import models.member.Member;
import models.member.MemberDao;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertTrue;

// 통합테스트(MockMVC) 설정 시 필요함
// MOCK MVC 통합테스트도 가능하게 세팅되어있음
@Transactional // 테스트 후 롤백됨
@SpringJUnitWebConfig // 톰캣서버를 열지 않아도 테스트가 가능함
// 웹환경이라 웹으로 설정!
// @ExtendWith(SpringExtension.class) 확장기능
@ContextConfiguration(classes = {MvcConfig.class, ControllerConfig.class})
public class JoinServiceTest {

    /**
     * 웹 컨테이너가 필요함
     */
    @Autowired
    private WebApplicationContext ctx;

    /**
     * 테스트 하기 직전에 생성 : MockMvc / @BeforeEach
     * 컨트롤러를 테스트 하는 데 사용함
     * 통과돼야 빌드가 됨
     *  MockMvc는 실제 HTTP 요청을 보내지 않고도
     *  컨트롤러 클래스를 테스트할 수 있도록 모의(mock) HTTP 요청 및 응답 객체를 제공
     */
    private MockMvc mockmvc;
    @BeforeEach
    void setup(){
        mockmvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }



    @Autowired
    private DataSource dataSource;
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private JoinService joinService;
    @Test
    @DisplayName("데이터베이스 연결 테스트")
    void connectionTest(){
        try(Connection conn = dataSource.getConnection()) {
            System.out.println(conn);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @DisplayName("MemberDao - exist 메서드 테스트")
    @Test
    void existsTest(){
        String userId="USER01";
        boolean result = memberDao.exist(userId);
        assertTrue(result);
    }

    @DisplayName("joinservice - join 메서드 테스트")
    @Test
    void joinTest(){
        RequestJoin form = new RequestJoin();
        form.setId("user01");
        form.setPw("user01");
        form.setName("홍길동");
        form.setEmail("hong@test.org");
        form.setPwcheck("user01");
        form.setAgree(true);
        joinService.join(form);

        Member member = memberDao.get(form.getId());
        System.out.println(member);
    }


    /**
     * andDo -> 추가 요청
     * andExpect -> 기대하는 게 맞는지
     * andExpectAll
     * @throws Exception
     */
    @Test
    @DisplayName("회원가입 통합 테스트")
    void joinTest2() throws Exception {
        mockmvc.perform(post("/member/join")
                .param("id", "test0606")) // 서버에서 넘어오는 값이기 때문에 필드명은 name값을 입력해야함
                .andDo(print());
    }
}
