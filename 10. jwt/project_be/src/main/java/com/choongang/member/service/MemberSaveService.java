package com.choongang.member.service;

import com.choongang.member.constants.Authority;
import com.choongang.member.controllers.RequestJoin;
import com.choongang.member.entities.Member;
import com.choongang.member.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSaveService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;



    public void join(RequestJoin form){
        String hash = passwordEncoder.encode(form.getPassword());
        Member member = Member.builder()
                .email(form.getEmail())
                .name(form.getName())
                .password(hash)
                .locked(false)
                .enable(true)
                .authority(Authority.USER).build();
    }


    public void save(Member member){
        memberRepository.saveAndFlush(member);
    }
}
