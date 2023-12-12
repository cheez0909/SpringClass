package models.member;

import java.util.List;

public class JoinService {

    private MemberDao memberDao;
    private JoinValidator joinValidator;

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
