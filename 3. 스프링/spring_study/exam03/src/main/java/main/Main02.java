package main;

import config.AppCtx2;
import models.member.JoinService;
import models.member.Member;
import models.member.MemberListService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class Main02 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppCtx2.class);
        JoinService joinService = ac.getBean("joinService", JoinService.class);
        MemberListService memberListService = ac.getBean("memberListService", MemberListService.class);

        Member member = new Member();

        member.setUserId("HKD0909");
        member.setUserName("홍길동");
        member.setUserPw("1234");
        member.setConfirmPw("1234");
        member.setRegDt(LocalDateTime.now());
        joinService.join(member);

        memberListService.getList();

        ac.close();
    }
}
