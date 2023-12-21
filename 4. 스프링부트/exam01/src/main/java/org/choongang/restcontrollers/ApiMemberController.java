package org.choongang.restcontrollers;

import org.choongang.entites.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/member")
public class ApiMemberController {
    @GetMapping
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

    @GetMapping("/list")
    public List<Member> list(){
        List<Member> members = IntStream.rangeClosed(1, 10)
                .mapToObj(i-> Member.builder().userNo(Long.valueOf(i)).userId("user" + i)
                        .userPw("12345678")
                        .userName("사용자"+i)
                        .email("test"+i+"@test.org")
                        .regDt(LocalDateTime.now())
                        .modDt(LocalDateTime.now())
                        .build()).toList();
        return members;
    }

    @GetMapping("/message")
    @ResponseBody
    public String message(){
        return "안녕?";
    }
    /**
     * 반환값없이 상태코드만 내보내거나
     * 처리만 진행하는 메서드도 있음!
     */
    @GetMapping("/process")
    public void process(){
        System.out.println("처리...");
    }
}
