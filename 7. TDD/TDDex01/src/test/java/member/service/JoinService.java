package member.service;

import jakarta.servlet.http.HttpServletRequest;
import member.commons.Validator;
import member.controllers.JoinValidator;
import member.controllers.Member;

public class JoinService {
    private Validator<Member> joinValidator;
    public JoinService(Validator<Member> joinValidator){
        this.joinValidator = joinValidator;
    }
    public void join(Member member){
        joinValidator.check(member);
    }

    public void join(HttpServletRequest request){
        Member member = Member.builder()
                .userId(request.getParameter("userId"))
                .userPw(request.getParameter("userPw"))
                .confirmPw(request.getParameter("confirmPw"))
                .userNm(request.getParameter("userNm"))
                .build();
        join(member);
    }
}
