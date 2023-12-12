package config;

import aop.Add;
import aop.Calculator;
import aop.CalculatorAdd;
import aop.Reg;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.Proxy;


@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true) // 구현체 기반으로 바뀜
public class AppCtx1 {
    @Bean
    public Reg reg(){
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
