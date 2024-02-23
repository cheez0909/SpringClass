package com.choongang.member;


import com.choongang.commons.rests.JSONData;
import com.choongang.member.controllers.RequestJoin;
import com.choongang.member.controllers.RequestLogin;
import com.choongang.member.services.MemberSaveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
// @TestPropertySource(properties = "spring.profiles.active=test")
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private MemberSaveService memberSaveService;
    private RequestJoin form;
    @Autowired
    private ObjectMapper om;
    @BeforeEach
    void init(){
        form = new RequestJoin();
        form.setEmail("user02@test.org");
        form.setPassword("_aA123456");
        form.setConfirmPassword(form.getPassword());
        form.setName("사용자02");
        form.setAgree(true);

        memberSaveService.join(form);
    }

    @Test
    @DisplayName("[통합테스트]로그인 테스트")
    void loginTest() throws Exception {
        RequestLogin loginForm = new RequestLogin();
        loginForm.setEmail(form.getEmail());
        loginForm.setPassword(form.getPassword());
        String params = om.writeValueAsString(loginForm);
        String body = mockMvc.perform(post("/api/v1/member/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(params))
                .andDo(print())
                .andReturn().getResponse().getContentAsString(Charset.forName("UTF-8"));

        JSONData data = om.readValue(body, JSONData.class);
        String token = (String) data.getData();
        // System.out.println(token);

        // 토큰 담아서 보내기
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token);
        mockMvc.perform(get("/api/v1/member/member_only")
                        .headers(headers))
                .andDo(print());

    }
}
