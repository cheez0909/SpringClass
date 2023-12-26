package org.choongang.test;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.entites.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.profiles.active=test")
public class test2 {

    // 의존성 자동주입
    @PersistenceContext
    private EntityManager em;
    @Test
    void Test(){
        Member member = new Member();
        member.setName("홍길동");
        member.setEmail("test@org.test");
        member.setPassword("123456");
        em.persist(member);
        em.flush();
        em.clear();

        member = em.find(Member.class, member.getSeq());
        System.out.println(member);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        member.setName("(수정)사용자01");
        em.flush();
        em.clear();

        member = em.find(Member.class, member.getSeq());
        System.out.println(member);
    }

    @Test
    void Test2(){
        Member member = new Member();
        member.setName("홍길동");
        member.setEmail("test@org.test");
        member.setPassword("123456");
        em.persist(member);
        em.flush(); // db에 반영
        System.out.println(member);
        /**
         * 3초 후 실행
         */
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        member.setName("(수정)사용자01");
        em.flush();
        System.out.println(member);
    }

    @Test
    void Test3(){
        Member member = new Member();
        member.setName("홍길동");
        member.setEmail("test@org.test");
        member.setPassword("123456");

        em.persist(member);
        em.flush(); // db에 반영

        System.out.printf("createdAt=%s, modifiedAt=%s%n",
                member.getCreatedAt(), member.getModifiedAt());

        /**
         * 3초 후 실행
         */
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        member.setName("(수정)사용자01");
        em.flush();

        System.out.printf("createdAt=%s, modifiedAt=%s%n",
                member.getCreatedAt(), member.getModifiedAt());
    }
}
