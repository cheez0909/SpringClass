package org.choongang.test;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.entites.BoardData;
import org.choongang.entites.Member;
import org.choongang.entites.QBoardData;
import org.choongang.repository.BoardDataRepository;
import org.choongang.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.springframework.data.domain.Sort.Order.desc;


@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.profiles.active=test")
// @TestPropertySource(properties = "spring.profiles.active=default")
public class test6 {
    @Autowired
    private BoardDataRepository boardDataRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void init(){
        Member member = new Member();
        member.setName("홍길동");
        member.setEmail("user01@test.org");
        member.setPassword("12345678");
        memberRepository.saveAndFlush(member);

        List<BoardData> items = new ArrayList<>();
        for(int i=1; i<=10; i++){
            BoardData item = new BoardData();
            item.setSubject("제목"+i);
            item.setContent("내용"+i);
            item.setMember(member);
            items.add(item);
        }
        boardDataRepository.saveAllAndFlush(items);
        em.clear(); // 엔티티비우기
    }

    @Test
    void Test1(){
        BoardData data = boardDataRepository.findById(1L).orElse(null);
        Member member = data.getMember();
        String email = member.getEmail();
        System.out.println(email);
    }

    @Test
    void Test2(){
        Member member = memberRepository.findByEmail("user01@test.org");
        // BoardData data = boardDataRepository.findById(member.getSeq()).orElse(null);
        System.out.println(member); // 회원조회는 됨
        List<BoardData> boardData = member.getBoardData();
        System.out.println(boardData);
        boardData.forEach(System.out::println); // boardData -> getter()
    }
    
    @Test
    void Test3(){
        List<BoardData> all = boardDataRepository.findAll(); // 1차 쿼리 실행
        /**
         * 목록 조회 시엔 쿼리가 많이 수행되기 때문에 성능저하가 일어날 수 있음
         * n+1문제가 생길 수 있다.
         */
        for(BoardData item : all){
            Member member = item.getMember();
            String email = member.getEmail(); // 2차 쿼리 실행
            System.out.println(email);
        }
    }

    /**
     * Fetch
     */
    @Test
    void Test4(){
        // 현재는 지연로딩
        // LEFT JOIN b.member 추가 후
        List<BoardData> items = boardDataRepository.getSubjects("목");
        items.forEach(System.out::println);
    }

    /**
     * @EntityGraph
     */
    @Test
    void Test5(){
        // @EntityGraph(attributePaths = {"member"}) 추가 후
        List<BoardData> items = boardDataRepository.findBySubjectContaining("목");
        items.forEach(System.out::println);
    }

    /**
     * querydsl
     *
     */
    @Test
    void test6(){
        QBoardData boardData = QBoardData.boardData;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        List<BoardData> boardData1 = jpaQueryFactory.selectFrom(boardData)
                .leftJoin(boardData.member)
                .fetchJoin() // 처음부터 조인이됨
                .where(boardData.subject.contains("목"))
                .fetch();
        // boardData1.forEach(System.out::println);
    }
}
