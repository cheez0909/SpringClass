package main;

import config.AppCtx2;
import config.AppCtx3;
import models.member.JoinService;
import models.member.Member;
import models.member.MemberListService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main03 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppCtx3.class);
        JoinService joinService = ac.getBean("joinService", JoinService.class);
        MemberListService memberListService = ac.getBean("memberListService", MemberListService.class);


        Member member = new Member();

        member.setUserId("HKD0909");
        member.setUserName("홍길동");
        member.setUserPw("1234");
        member.setConfirmPw("1234");
        member.setRegDt(LocalDateTime.now());
        joinService.join(member);

        Member member1 = new Member();
        member1.setUserId("HKD0808");
        member1.setUserName("홍길동");
        member1.setUserPw("1234");
        member1.setConfirmPw("1234");
        member1.setRegDt(LocalDateTime.now());
        joinService.join(member1);

        memberListService.getList();
        System.out.println(memberListService.getOne(member1));

        ac.close();
    }
}
