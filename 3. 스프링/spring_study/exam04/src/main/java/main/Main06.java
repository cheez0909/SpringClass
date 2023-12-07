package main;

import config.AppCtx2;
import models.Message;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main06 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppCtx2.class);

        Message message = ac.getBean("message", Message.class);
        Message message2 = ac.getBean("message", Message.class);

        System.out.println(message2==message);

        ac.close();
    }
}
