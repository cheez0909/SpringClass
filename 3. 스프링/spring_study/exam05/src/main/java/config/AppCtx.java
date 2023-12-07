package config;

import aop.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proxy.Impl;


@Configuration
public class AppCtx {
    @Bean
    public Calculator calculator(){
        return new Reg();
    }
}
