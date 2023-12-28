package org.choongang.test;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.entites.Address;
import org.choongang.entites.BoardData;
import org.choongang.entites.Member;
import org.choongang.repository.AddressRepository;
import org.choongang.repository.BoardDataRepository;
import org.choongang.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.profiles.active=test")
// @TestPropertySource(properties = "spring.profiles.active=default")
public class test7 {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void init(){
        Address address = new Address();
        address.setAddr1("서울시");
        address.setAddr2("용산구");
        address.setZipcode("04332");
        addressRepository.saveAndFlush(address);

        Member member = new Member();
        member.setName("홍길동");
        member.setEmail("test@test.org");
        member.setPassword("123456");
        member.setAddress(address);
        memberRepository.saveAndFlush(member);

        em.clear();
    }
    @Test
    void Test(){
        Member member = memberRepository.findById(1L).orElse(null);
        Address address = member.getAddress();
        System.out.println(address);
    }
    @Test
    void Test1(){
        Address address = addressRepository.findById(1L).orElse(null);
        Member member = address.getMember();
        System.out.println(member);
    }
}
