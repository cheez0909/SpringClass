package member.service;

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
}
