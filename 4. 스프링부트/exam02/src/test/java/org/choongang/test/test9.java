package org.choongang.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.entites.BoardData;
import org.choongang.entites.HashTag;
import org.choongang.entites.Member;
import org.choongang.repository.BoardDataRepository;
import org.choongang.repository.HashTagRepository;
import org.choongang.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class test9 {
    @Autowired
    private BoardDataRepository boardDataRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void init(){
        Member member = new Member();
        member.setEmail("user01@test.org");
        member.setPassword("12345678");
        member.setName("사용자01");
        memberRepository.saveAndFlush(member);

        List<BoardData> items = new ArrayList<>();
        for(int i=1; i<=3; i++){
            BoardData data = new BoardData();
            data.setSubject("제목" + i);
            data.setContent("내용" + i);
            data.setMember(member);
            items.add(data);
        }
        boardDataRepository.saveAllAndFlush(items);
    }

    /**
     * 직접 삭제가 불가능함
     * 엔티티를 가져온 후 제거 상태로 바꿔야함
     */
    @Test
    void Test1(){
        Member member = memberRepository.findByEmail("user01@test.org"); // 영속성으로 가져옴
        memberRepository.delete(member); // 제거 상태로 만듦
        memberRepository.flush(); // 쿼리가 수행됨
        /**
         * member에 cascade = CascadeType.REMOVE옵션을 주면 삭제됨
         * 옵션을 주지 않을 경우 오류 발생 (DataIntegrityViolationException)
         */
    }

    @Test
    void Test2(){

    }
}
