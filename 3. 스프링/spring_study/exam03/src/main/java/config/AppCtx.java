package config;


import models.member.JoinService;
import models.member.JoinValidator;
import models.member.MemberDao;
import models.member.MemberListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 설정파일
@Configuration
public class AppCtx {

    @Bean
    public MemberDao memberDao(){

        return new MemberDao();
    }

    @Bean
    public JoinValidator joinValidator(){

        return new JoinValidator(memberDao());
    }
    @Bean
    public JoinService joinService(){

        return new JoinService(memberDao(), joinValidator());
    }

    @Bean
    public MemberListService memberListService(){
        MemberListService list = new MemberListService();
        list.setMemberDao(memberDao());
        return list;
    }

}
