package main;

import config.AppCtx;
import models.Greeter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main02 {
    public static void main(String[] args) {
        Class greeterClass = Greeter.class;
        // 모든 클래스는 정적변수로 .class 파일이 존재함
        // 클래스 정보가 담겨 있음

        Method[] methods = greeterClass.getMethods();
        for (Method mts : methods) {
            System.out.println(mts);
        }

    }
}
