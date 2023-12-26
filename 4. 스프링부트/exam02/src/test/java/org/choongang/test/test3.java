package org.choongang.test;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.entites.BoardData;
import org.choongang.entites.Member;
import org.choongang.repository.BoardDataRepository;
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
public class test3 {
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
    void Test3(){
        List<BoardData> items = boardDataRepository.findAll();
        items.forEach(System.out::println);
    }

    @Test
    void Test1(){
        BoardData data = new BoardData();
        data.setSubject("제목");
        data.setContent("내용");
//        boardDataRepository.save(data);
//        boardDataRepository.flush();
        data = boardDataRepository.saveAndFlush(data);
        // 영속상태에 있기 때문에 수정하거나 제거하면 쿼리문이 나간다.
        System.out.println(data);
        System.out.println("=========================");

        data.setSubject("(수정)제목");
        System.out.println(data);
        boardDataRepository.saveAndFlush(data); // update문이 나감
        System.out.println("=========================");

        BoardData data1 = boardDataRepository.findById(data.getSeq()).orElse(null); // select문이 나감
        // 조회시 flush가 자동으로 됨...
        System.out.println("=========================");
        System.out.println(data1);
        System.out.println(data);
    }

    @Test
    void Test2(){
        BoardData data = new BoardData();
        data.setSubject("제목");
        data.setContent("내용");
        data = boardDataRepository.saveAndFlush(data);
        // 영속상태에 있기 때문에 수정하거나 제거하면 쿼리문이 나간다.
        System.out.println(data);
        System.out.println("============첫번째 : saveAndFlush 완료=============");

        data.setSubject("(수정)제목");
        // boardDataRepository.save(data); // update 문 없이 수정이 반영됨....
        // boardDataRepository.flush();
        // 조회 전에는 flush를 하지 않아도 조회할 때 flush가 자동으로 수행됨
        // boardDataRepository.save(data); // update문 반영
        System.out.println("============수정 후 : save =============");
        System.out.println(data);

         // 조회시 flush가 자동으로 됨... 변화감지 메모리(영속성컨텍스트)
         BoardData data1 = boardDataRepository.findById(data.getSeq()).orElse(null);
         // select문이 나감
         System.out.println("=========================");
         System.out.println(data1); // flush시 수정 전 제목
         System.out.println(data); // flush 시 수정 후 제목 -> 조회를 하게 되면 flush가 자동으로 수행됨
    }

}
