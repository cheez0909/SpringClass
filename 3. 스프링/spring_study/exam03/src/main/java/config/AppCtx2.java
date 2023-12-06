package config;


import models.member.JoinService;
import models.member.JoinValidator;
import models.member.MemberDao;
import models.member.MemberListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// 설정파일
@Configuration
// @Import(value = {AppCtx3.class})
public class AppCtx2 {


    @Bean
    public JoinService joinService(){

        return new JoinService();
    }
    @Bean
    public MemberListService memberListService(){

        return new MemberListService();
    }

}
