package main;

import config.AppCtx;
import models.Greeter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main01 {
    public static void main(String[] args) {
        // 애노테이션으로 설정하는 방식의 컨테이너(객체가 담겨있음)
        // ApplicationContext === pageContext와 유사
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
