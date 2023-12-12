package aop;

import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class AopMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppCtx.class);
        Calculator bean = ac.getBean(Calculator.class);
        long factorial = bean.factorial(10L);
        System.out.println(factorial);

        long factorial1 = bean.factorial(10L);
        System.out.println(factorial1);
        long factorial2 = bean.factorial(3L);
        System.out.println(factorial2);
        long factorial3 = bean.factorial(4L);
        System.out.println(factorial3);
        System.out.println("=====================================");
        CalculatorAdd bean1 = ac.getBean(CalculatorAdd.class);
        int add1 = bean1.add(1, 2, 3, 4, 5);
        System.out.println(add1);

        int add2 = bean1.add(12, 15, 16, 17, 18);
        System.out.println(add2);

        int add3 = bean1.add(10, 19, 20, 55);
        System.out.println(add3);

        int add4 = bean1.add(1, 2, 3, 4, 5);
        System.out.println(add4);
        int add5 = bean1.add(1, 2, 3, 4, 5);
        System.out.println(add5);


        ac.close();
    }
}
