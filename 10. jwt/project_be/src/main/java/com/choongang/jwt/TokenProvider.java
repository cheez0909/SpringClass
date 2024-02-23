package com.choongang.jwt;

import com.choongang.member.constants.Authority;
import com.choongang.member.services.MemberInfo;
import com.choongang.member.services.MemberInfoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableConfigurationProperties(JwtProperties.class)
public class TokenProvider {


    private MemberInfoService memberInfoService;

    private JwtProperties props;
    private Key key; // 프로퍼티로 키값을 완성시킴

    /* 기본 생성자로 키값 완성 */
    // sighKey로 정상적인 토큰인지 체크
    public TokenProvider(MemberInfoService memberInfoService, JwtProperties props){
        this.memberInfoService = memberInfoService;
        this.props = props;

        byte[] keyBytes = Decoders.BASE64.decode(props.getSecret());
        key = Keys.hmacShaKeyFor(keyBytes);
    }


    public String createToken(Authentication authentication){
        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); // 권한이 여러개 일 때


        /* 문자열 토큰이 만들어짐 */
        long now =  (new Date()).getTime(); // EpochTime -> 1000분의 1초 단위의 현재시간
        Date validity = new Date(now + props.getAccessTokenValidityInSeconds() * 1000);

        return Jwts.builder()
                .setSubject(authentication.getName()) // 아이디값
                .claim("auth", authorities) // 권한
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build().parseClaimsJws(token)
                .getPayload();
        String _authorities = (String) claims.get("auth");
        _authorities = StringUtils.hasText(_authorities) ? _authorities : Authority.USER.name();

        /* 권한 가져오기 */
        List<SimpleGrantedAuthority> authorities = Arrays.stream(_authorities.split(","))
                .map(SimpleGrantedAuthority::new).toList();

        // 토큰안에 정보가 담겨 있기 때문에 따로 보관할 필요가 없음
        MemberInfo memberInfo = (MemberInfo) memberInfoService.loadUserByUsername(claims.getSubject());

        memberInfo.setAuthorities(authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(memberInfo, token, authorities);

        return authentication;
    }

    public boolean validateToken(String token){
        /* 변환 시 오류가 발생하면 이상이 있는 것 */
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
