package models.member;

import java.util.Arrays;
import java.util.List;

public class MemberListService {
    private MemberDao memberDao;


    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    // 회원 목록 조회
    public void getList(){
        List<Member> list = memberDao.getList();
        list.stream().forEach(x -> System.out.println(x));
    }
}
