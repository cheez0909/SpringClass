package common;

import jakarta.servlet.http.HttpSession;
import models.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class Utils {

    @Autowired
    private HttpSession session;

    /**
     * 로그인 상태인지
     */
    public boolean isLogin(){
        return getMember() != null;
    }

    /**
     * 멤버 정보 가져오기
     */
    public Member getMember(){
        Member member = (Member) session.getAttribute("member");
        return member;
    }
}
