package config;


import models.member.JoinService;
import models.member.JoinValidator;
import models.member.MemberDao;
import models.member.MemberListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.format.DateTimeFormatter;


@Configuration
// 설정 정보를 추가
@Import(value = {AppCtx2.class})
public class AppCtx3 {

    @Bean
    public MemberDao memberDao(){

        return new MemberDao();
    }

    @Bean
    public JoinValidator joinValidator(){

        return new JoinValidator();
    }

    @Bean
    // 빈으로 설정하지 않고 MemberListService에 @Autowired도 하지 않으면 NPE발생
    // 빈으로 설정 후 @Autowired도 하지 않으면 NPE발생
    // 빈으로 설정하지 않고 @Autowired만 할 경우 NoSuchElementException: No value present 발생
    public DateTimeFormatter dateTimeFormatter(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

}
