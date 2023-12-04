package main;

import config.AppCtx;
import models.Greeter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main01 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppCtx.class);
        Greeter greeter1 = ac.getBean("greeter", Greeter.class);
        Greeter greeter2 = ac.getBean("greeter", Greeter.class);
        greeter1.hello("홍길동\n");
        greeter2.hello("이순신\n");
        System.out.println(greeter1);
        System.out.println(greeter2);
        ac.close();
    }
}
