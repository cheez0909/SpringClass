package controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {


    @GetMapping("/cookie/test1")
    public String test1(HttpServletResponse response, HttpServletRequest request){
        // 응답객체에 쿠키를 추가함
        // 경로를 설정해주지 않으면 기본경로로 쿠키가 저장됨
        Cookie cookie = new Cookie("key1", "value1");
        response.addCookie(cookie);

        Cookie cookie2 = new Cookie("key2", "value2");
        // 경로를 설정해준다.
        // 설정된 경로 이하의 경로에 전부 쿠키가 유지된다.
        cookie2.setPath(request.getContextPath());
        // 쿠키의 만료시간을 설정
//        cookie2.setMaxAge(60*60*24*7);
        // cookie2.setMaxAge(0); // 1970.1.1 자정으로 입력됨 = 즉 이미 만료된 시간으로 지정, 삭제된다~~!
        cookie2.setHttpOnly(true);
        // false일 경우 자바 스크립트로 브라우저에서 조회 가능
        // true일 경우 자바스크립트 조회X, 서버쪽에서 요청을 통한 조회만 가능
        response.addCookie(cookie2);

        return "test";
    }

    // PATH가 달라지면 쿠키가 유지되지 않음
    // 만약 유지하고 싶으면 session에 저장
    @GetMapping("/cookie1/test2")
    public String test2(@CookieValue(name="key2", required = false, defaultValue = "기본값") String key2){
        System.out.printf("key2 = %s", key2);
        return "test";
    }

    // 세션에 값을 추가
    @GetMapping("/session/test1")
    public String test3(HttpSession session){
        session.setAttribute("key1", "value");
        return "test";
    }

    @GetMapping("/session/test2")
    public String test4(HttpServletRequest request){
        HttpSession session = request.getSession();
        String key1 = (String) session.getAttribute("key1");
        System.out.printf("value = %s", key1);
        return "test";
    }
    
}
