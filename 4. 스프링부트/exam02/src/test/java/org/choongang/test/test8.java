package org.choongang.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.entites.BoardData;
import org.choongang.entites.HashTag;
import org.choongang.repository.BoardDataRepository;
import org.choongang.repository.HashTagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class test8 {

    @Autowired
    private BoardDataRepository boardDataRepository;
    @Autowired
    private HashTagRepository hashTagRepository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void init(){

        List<HashTag> tagList = new ArrayList<>();

        for(int i=1; i<=5; i++){
            HashTag tag = new HashTag();
            tag.setTag("태그"+i);
            tagList.add(tag);
        }

        hashTagRepository.saveAllAndFlush(tagList);

        List<BoardData> items = new ArrayList<>();

        for(int i=1; i<=5; i++){
            BoardData boardData = new BoardData();
            boardData.setSubject("제목"+i);
            boardData.setContent("내용"+i);
            boardData.setTags(tagList);
            items.add(boardData);
        }

        boardDataRepository.saveAllAndFlush(items);
        // 영속성 컨텍스트 안에 있음
        // 영속성 안에 있으면 쿼리를 하지 않음

        em.clear(); // 영속성 비우기
    }

    @Test
    void Test1(){
        // em.clear()를 하지 않으면
        // 쿼리문을 보내지 않음

        BoardData item = boardDataRepository.findById(1L).orElse(null);
        List<HashTag> tags = item.getTags();
        System.out.println(tags);
    }

    @Test
    void Test2(){
        HashTag tag = hashTagRepository.findById("태그1").orElse(null);
        List<BoardData> items = tag.getItems();
        System.out.println(items);
    }
}
