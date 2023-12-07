package main;

import config.AppCtx2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main05 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppCtx2.class);
        ac.close();
    }
}
