package org.choongang.repositories;

import lombok.extern.slf4j.Slf4j;
import org.choongang.entites.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberRepositoryTest {

    // 스프링컨테이너 안에 이미 담겨있음
    // 빈으로 등록할 필요가 없다.
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 전체회원조회하기(){
        List<Member> memberList = (List<Member>) memberRepository.findAll();
        memberList.forEach(System.out::println);
    }

    @Test
    void 수정하기(){
        Member member = memberRepository.findById(30L).orElse(null);
        member.setUserName("(수정)사용자01");
        member.setModDt(LocalDateTime.now());
        memberRepository.save(member);
    }

    @Test
    void 사용자아이디로조회하기(){
        Member member = memberRepository.findByUserId("test0606");
        log.info(member.toString());
    }

    @Test
    void 사용자이름키워드로조회하기(){
        List<Member> member = memberRepository.findByUserNameContaining("용");
        member.forEach(System.out::println);
    }

    @Test
    void 사용자이름키워드로조회하고정렬(){
        List<Member> member = memberRepository.findByUserNameContainingOrderByRegDtDesc("용");
        member.forEach(System.out::println);
    }

    @Test
    void 사용자이름or사용자id(){
        List<Member> member = memberRepository.findByUserNameContainingOrUserIdContainingOrderByRegDtDesc("용", "r");
        member.forEach(System.out::println);
    }

    @Test
    void 사용자조회리스트(){
        List<Member> member = memberRepository.getMembers("용", "Id");
        member.forEach(System.out::println);
    }

    @Test
    void 페이지(){
        Pageable pageable = PageRequest.of(3, 10,
                Sort.by(Sort.Order.asc("regDt"), Sort.Order.desc("userId")));
        Page<Member> data = memberRepository.findByUserNameContaining("용", pageable);
        List<Member> members = data.getContent();
        long totalElements = data.getTotalElements();
        int totalPages = data.getTotalPages();
    }
}