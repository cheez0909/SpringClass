package org.choongang.test;


import org.choongang.entites.BoardData;
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
public class test4 {
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
//        List<BoardData> lists = boardDataRepository.findBySubjectContaining("4");
//        lists.forEach(System.out::println);
        List<BoardData> lists1 = boardDataRepository.findBySubjectContainingOrderBySeqDesc("목");
        lists1.forEach(System.out::println);
    }

    @Test
    void Test2(){
        // PageRequest -> 클래스, 즉 구현체
        // 엔티티의 속성명을 입력해야함 테이블명 아님!
//        Pageable pageable =
//                PageRequest.of(0, 3,
//                        Sort.by(Sort.Order.desc("createdAt")));

        Pageable pageable =
                PageRequest.of(0, 3,
                        Sort.by(desc("createdAt"), asc("seq")));
        Page<BoardData> data =  boardDataRepository.findBySubjectContaining("목", pageable);
        List<BoardData> content = data.getContent();
        content.forEach(System.out::println);
    }
}
