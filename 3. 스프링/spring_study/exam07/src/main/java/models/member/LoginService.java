package models.member;

import controllers.member.RequestLogin;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberDao memberDao;
    /**
     * 로그인 정보를 세션에 저장해야함
     */
    private final HttpSession session;

    public void login(RequestLogin requestLogin){
        String userId = requestLogin.userId();
        Member member = memberDao.get(userId);
        session.setAttribute("member", member); // 세션에 "member"가 있으면 로그인 상태
    }
}
