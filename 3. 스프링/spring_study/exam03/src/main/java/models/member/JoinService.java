package models.member;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JoinService {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private JoinValidator joinValidator;

    public JoinService(){}
    public JoinService(MemberDao memberDao, JoinValidator joinValidator) {
        this.memberDao = memberDao;
        this.joinValidator = joinValidator;
    }

    // 회원가입
    public void join(Member member) {
        joinValidator.validate(member);
        memberDao.register(member);
    }

}
