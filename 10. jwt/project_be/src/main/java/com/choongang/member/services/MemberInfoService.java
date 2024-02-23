package com.choongang.member.services;

import com.choongang.member.constants.Authority;
import com.choongang.member.entities.Member;
import com.choongang.member.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username));

        Authority authority = Objects.requireNonNullElse(member.getAuthority(), Authority.USER); // 타입이 없으면 유저

        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority(authority.name())
        );

        return MemberInfo.builder().email(member.getEmail())
                .password(member.getPassword())
                .enable(member.isEnable())
                .lock(member.isLocked())
                .authorities(authorities)
                .member(member)
                .build();
    }
}
