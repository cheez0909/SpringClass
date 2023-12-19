package configs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.member.Member;
import org.springframework.web.servlet.HandlerInterceptor;

public class MemberOnlyInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        if(member!=null){
            return true;
        }
        // 401 -> 언오더라이즈
        // response.setStatus(401);
        // 응답 코드 설정
        // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 비회원 -> 로그인 페이지로 이동
        String url = request.getContextPath() + "/member/login";
        response.sendRedirect(url);
        return false; // false면 실행 안함
    }
}
