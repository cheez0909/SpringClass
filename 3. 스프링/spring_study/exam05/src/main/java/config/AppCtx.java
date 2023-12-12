package config;

import aop.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;



@Configuration
@EnableAspectJAutoProxy // 기존 AppCtx를 냅두고 새로운 객체가 한 번 만들어지고 코드가 추가됨
// 프록시로 AppCtx를 만듦
// 한 번 컴파일 되면 못 바꿈
// 기존꺼에 + 알파 한 것을 프록시로 추가함
public class AppCtx {
    @Bean
    public Calculator calculator(){
        return new Reg();
    }

    @Bean
    public CalculatorAdd calculatorAdd(){
        return new Add();
    }
    @Bean
    public ProxyCalculator proxyCalculator(){
        return new ProxyCalculator();
    }
    @Bean
    public ProxyCache proxyCache(){
        return new ProxyCache();
    }
}
