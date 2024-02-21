package com.choongang.member;


import com.choongang.member.controllers.RequestJoin;
import com.choongang.member.entities.Member;
import com.choongang.member.repositories.MemberRepository;
import com.choongang.member.service.MemberSaveService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.profiles.active=test")
public class JoinTest {
    @Autowired
    private MemberSaveService saveService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 기능 테스트")
    void saveServiceTest() {
        RequestJoin form = new RequestJoin();
        form.setEmail("user01@test.org");
        form.setName("사용자01");
        form.setPassword("_aA123456");

        saveService.join(form);

        Member member = memberRepository.findByEmail(form.getEmail()).orElse(null);
        System.out.println(member);
    }
}
