package com.choongang.jwt;

import com.choongang.member.MemberUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtProcessFilter extends GenericFilterBean {

    private final MemberUtil memberUtil;
    private final TokenProvider tokenProvider;

    // web.xml파일에서 처리할 필요 없이 처리할 수 있음
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = memberUtil.getToken();
        if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication); // 정보를 넣어주면 로그인이 됨
        }
        chain.doFilter(request, response);
    }
}
