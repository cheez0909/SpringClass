package org.choongang.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.choongang.entites.Member;
import org.choongang.restcontrollers.RequestJoin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {

    // 서버를 켜지 않아도 가능함
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("회원 가입 성공 시 응답 코드 201")
    void joinTest() throws Exception {
//        MediaType.APPLICATION_JSON == application/json

        /**
         * form은 자바 객체
         */
        RequestJoin form = new RequestJoin();
        form.setUserId("user01");
        form.setUserNm("사용자01");
        form.setUserPw("12345678");
//        form.setConfirmPw("12345678"); 메세지가 떠야 하는데 나는 null이 뜬다.. 코드확인 필요
        form.setEmail("uesr01@test.org");
        form.setRegDt(LocalDateTime.now()); //자바 타임패키지는 적용되지 않음


        /**
         * 자바 객체를 제이슨으로 변환해주자!
         */
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        String body = om.writeValueAsString(form);
        System.out.println(body);

        mockMvc.perform(post("/api/member")
                        // .header("Content-Type", "application/json"))
                        .header("Content-Type", MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(body))
                // 타입을 상수로 정의해 놓았다.
                .andDo(print())
                .andExpect(status().isCreated()); // 201이 맞는지 확인
    }

    @Test
    @DisplayName("제이슨문자열을 자바객체로 변환")
    void infoTest() throws Exception{
        String body = mockMvc.perform(get("/api/member"))
                .andDo(print()) // 자세한 정보를 확인할 때
                .andReturn()// 응답 데이터를 반환 받을 때(body)
                .getResponse() // 응답 객체
                .getContentAsString(Charset.forName("UTF-8"));
                // 바디데이터를 가져 올 때, 한글깨짐이 있을수 있어서 인코딩해줘야함

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        Member member = om.readValue(body, Member.class);
        System.out.println(member);
    }

    /**
     * 반환값이 List<Member>로 객체가 아닌 경우
     * 안에 있는 상태를 확인해볼때도 사용함
     */
    @Test
    @DisplayName("제이슨 문자열을 자바 객체로 -1 ")
    void listTest() throws Exception{
        String body = mockMvc.perform(get("/api/member/list"))
                    .andDo(print())
                    .andReturn().getResponse() // 반환값이 필요할 때
                    .getContentAsString(Charset.forName("UTF-8"));
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        List<Member> memberList = om.readValue(body, new TypeReference<>() {});
        memberList.forEach(System.out::println);
    }
}
