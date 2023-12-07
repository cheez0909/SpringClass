package config;

import models.member.MemberDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.time.format.DateTimeFormatter;

@Configuration
@ComponentScan(value = "models")
// excludeFilters -> 제외시킨다
// @ComponentScan.Filter -> (타입, 클래스) -> 어노테이션으로 설정된 것을 제외시키겠다는 의미
//@ComponentScan(value = "models",
//excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
//        classes = Manual.class))

//@ComponentScan(value = "models", excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MemberDao.class))
// @ComponentScan(value = "models", excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "models ..*Dao")) // Dao로 끝나는 모든 클래스
public class AppCtx {

//    @Bean
//    public MemberDao memberDao(){
//        System.out.println("수동 등록 빈");
//        return new MemberDao();
//    }
    //

    @Bean
    public DateTimeFormatter dateTimeFormatter(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        return dateTimeFormatter;
    }
}
