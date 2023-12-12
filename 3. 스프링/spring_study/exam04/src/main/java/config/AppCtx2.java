package config;


import models.Message;
import models.Message2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Configuration
public class AppCtx2 {
    @Bean
    @Scope("prototype")
    public Message message(){
        return new Message();
    }


    // 메서드에 매개변수가 있으면 안됨
//    @Bean(initMethod = "init", destroyMethod = "close")
//    public Message2 message2(){
//        return new Message2();
//    }
}
