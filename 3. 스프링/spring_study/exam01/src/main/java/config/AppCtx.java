package config;

import models.Greeter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppCtx {

    @Bean
    // 스프링이 관리하는 빈 객체로 등록.
    // 스프링 빈으로 등록
    public Greeter greeter(){
        return new Greeter();
    }

}
