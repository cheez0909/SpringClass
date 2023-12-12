package configs;

import controllers.HelloController;
import controllers.member.MemberController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("controllers")
public class ControllerConfig {

    /*
    @Bean
    public HelloController helloController(){

        return new HelloController();
    }

    @Bean
    public MemberController memberController(){
        return new MemberController();
    }
     */


}
