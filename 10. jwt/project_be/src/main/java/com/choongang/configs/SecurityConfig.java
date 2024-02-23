package com.choongang.configs;

import com.choongang.jwt.JwtProcessFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity // @PreAuthorize("hasAuthority(...)")
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final JwtProcessFilter jwtProcessFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c->c.disable()) // 별도의 토큰을 발급받기때문에 csrf토큰을 사용하지 않음
                .sessionManagement(c-> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션을 사용하지 않음
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtProcessFilter, UsernamePasswordAuthenticationFilter.class);

        // 멤버로 가입 후 토큰 요청
        http.authorizeHttpRequests(c ->
                c.requestMatchers("/api/v1/member", "/api/v1/member/token").permitAll()
                        .anyRequest().authenticated());

        // ex. 로그인 하지 않았을 때 로그인이 필요한 페이지로 이동했을때
        // ex. 로그인했지만 권한없는 페이지로 이동할 때 ->
        http.exceptionHandling(c->
                c.authenticationEntryPoint((req, res, e)->res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                        .accessDeniedHandler((req, res, e)->res.sendError(HttpServletResponse.SC_UNAUTHORIZED)));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
