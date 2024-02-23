package com.choongang.member.services;

import com.choongang.jwt.TokenProvider;
import com.choongang.member.controllers.RequestLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

    // 로그인 후 토큰을 발급 해야 함
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public String login(RequestLogin form){
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword()));
        String accessToken = tokenProvider.createToken(authentication);
        return accessToken;
    }
}
