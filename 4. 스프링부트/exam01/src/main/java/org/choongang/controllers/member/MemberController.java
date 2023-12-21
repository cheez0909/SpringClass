package org.choongang.controllers.member;

import lombok.extern.slf4j.Slf4j;
import org.choongang.entites.Member;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * 회원 관련 컨드롤러
 */
@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
    @GetMapping("/join")
    public String join(){

        log.info("로그메세지~!!");
        // 중괄호로 로그에 값을 따로 넣을 수 있다.
        log.info("로그 {}, {}", "값1", "값2");
        return "member/join";
    }

    @GetMapping("/info")
    @ResponseBody // 제이슨 객체로 반환함
    public Member info(){
        Member member = Member.builder()
                .userNo(1L)
                .userPw("1234567")
                .userId("user09")
                .userName("사용자01")
                .email("user09@test.org")
                .regDt(LocalDateTime.now())
                .modDt(LocalDateTime.now())
                .build();
        return member;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "안녕하세요";
    }
}
