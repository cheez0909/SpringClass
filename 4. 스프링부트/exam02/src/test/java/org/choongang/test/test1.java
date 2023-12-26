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
public class test1 {
    @PersistenceContext
    private EntityManager em;

    @BeforeEach // 테스트 전
    void init(){
        Member member = new Member();
        member.setName("홍길동");
        member.setEmail("test@org.test");
        member.setPassword("123456");
        em.persist(member);
        em.flush();
//        em.clear(); // 영속상태 엔티티 모두 비우기
    }

    @Test
    void Test(){
        Member member = new Member();
        member.setName("홍길동");
        member.setEmail("test@org.test");
        member.setPassword("123456");
        em.persist(member);
        em.flush();

//        em.merge(member); // 분리된 영속상태
//        em.flush();

//        em.remove(member); // 제거 상태로 변경
//        em.flush();
    }

    @Test
    void test2(){
        // find() : 조회, 기본키로 조회, 이미 영속성에 엔티티가 있으면 db에서 조회X
        // -> 1차 캐시 성능상 이점
        // -> 기본키만 가지고 조회하므로 조회시에는 한계
        // JPQL -> 일반 sql과는 차이점 : 문법, 조회 결과시 엔티티-> 영속상태 : 변화감지 가능
        // 쿼리 :
        //
        Member member = em.find(Member.class, 1l);
        System.out.println(member);

        Member member2 = em.find(Member.class, 1l); // 영속상태 조회
        System.out.println(member2);
    }

    @Test
    void test3(){
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        members.forEach(System.out::println);
    }

}
