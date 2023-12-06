package main;

import models.member.*;

import java.time.LocalDateTime;
import java.util.Date;

public class MemberMain {
    public static void main(String[] args) {
//        MemberDao memberDao = new MemberDao();
//        JoinValidator joinValidator = new JoinValidator(memberDao);
//        JoinService joinService = new JoinService(memberDao, joinValidator);
//        MemberListService memberListService = new MemberListService();
//        memberListService.setMemberDao(memberDao);

        JoinService joinService = ServiceManager.getInstance().joinService();
        MemberDao memberDao = ServiceManager.getInstance().memberDao();
        JoinValidator joinValidator = ServiceManager.getInstance().joinValidator();
        MemberListService memberListService = ServiceManager.getInstance().memberListService();

        Member member = new Member();
        member.setUserId("HKD11");
        member.setUserPw("1234");
        member.setConfirmPw("1234");
        member.setUserName("홍길동");
        member.setRegDt(LocalDateTime.now());

        joinService.join(member);
        memberListService.getList();
    }
}
