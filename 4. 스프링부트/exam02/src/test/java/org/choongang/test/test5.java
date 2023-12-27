package org.choongang.test;


import com.querydsl.core.BooleanBuilder;
import org.choongang.entites.BoardData;
import org.choongang.entites.QBoardData;
import org.choongang.repository.BoardDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;


@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.profiles.active=test")
// @TestPropertySource(properties = "spring.profiles.active=default")
public class test5 {
    @Autowired
    private BoardDataRepository boardDataRepository;

    @BeforeEach
    void init(){
        List<BoardData> items = new ArrayList<>();
        for(int i = 1; i<=10; i++){
            BoardData item = new BoardData();
            item.setSubject("제목 : " + i);
            item.setContent("내용 : " + i);
            items.add(item);
        }
        boardDataRepository.saveAllAndFlush(items);
    }

    @Test
    void Test1(){
        List<BoardData> items = boardDataRepository.getSubjects("목");
        items.forEach(System.out::println);
    }

    @Test
    void Test2(){
        // 필드별로 변수가 존재하고, 변수내에 메서드가 존재함
        // 조건식을 메서드로 사용할 수 있음
        QBoardData boardData = QBoardData.boardData;
        // 반환값은 iterable (컬렉션의 상위 인터페이스) -> 형변환이 필요함
        List<BoardData> items = (List<BoardData>) boardDataRepository.findAll(boardData.subject.contains("목"));
        items.forEach(System.out::println);
    }

    @Test
    void Test3(){
        QBoardData boardData = QBoardData.boardData;
        List<BoardData> items = (List<BoardData>) boardDataRepository.findAll(boardData.subject.contains("목"), Sort.by(desc("createdAt")));
        items.forEach(System.out::println);
    }

    @Test
    void Test4(){
        QBoardData boardData = QBoardData.boardData;
        // 1 페이지에 3개, 카운트쿼리가 몇 개 더 수행됨
        Page<BoardData> data = (Page<BoardData>) boardDataRepository.findAll(
                boardData.subject.contains("목"), PageRequest.of(1, 3, Sort.by(desc("createdAt"))));
        data.forEach(System.out::println);
    }

    @Test
    void Test5(){
        // 제목과 내용중에 무엇이 포함되어 있는지
        QBoardData boardData = QBoardData.boardData;
        BooleanBuilder orBuilder = new BooleanBuilder();
        // this가 반환되면 메서드 체인을 의도하고 만들어 진 것
        orBuilder.or(boardData.subject.contains("목"))
                .or(boardData.content.contains("용"));
        // subject LIKE '%목%' OR content LIKE '%목%' AND seq IN (1,3,5)
        // -> 첫번째 조건이 참이면 뒤에 조건을 확인하지 않음
        // -> 괄호가 필요함 (subject LIKE '%목%' OR content LIKE '%목%') AND seq IN (1,3,5)
        // and빌더와 or빌더 둘다 만들어서  and에 or가 포함되게 만들면 된다.

        BooleanBuilder andBuilder = new BooleanBuilder();
        andBuilder.and(boardData.seq.in(1L, 3L, 5L));

        andBuilder.and(orBuilder);

        List<BoardData> items = (List<BoardData>) boardDataRepository.findAll(andBuilder);
        items.forEach(System.out::println);
    }
}
