package com.choongang.member;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class MemberUtil {

    private final HttpServletRequest request;

    /**
     * Authorization: Bearer 토큰 값
     * */
    public String getToken(){
        String authorization = request.getHeader("Authorization");
        // 로그인하지 않으면 토큰이 없음

        if(StringUtils.hasText(authorization)) {
            return authorization.substring(7);
        }

        return null;
    }
}
