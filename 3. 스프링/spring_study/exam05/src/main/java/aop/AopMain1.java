package aop;

import config.AppCtx;
import config.AppCtx1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopMain1 {
    public static void main(String[] args) {
        // 인터페이스가 아닌 구현체를 빈에 등록할 경우 오류가 발생함
        // 빈을 찾을 수 없다
        // 인터페이스 기반의 프록시를 만들고 거기에 코드를 추가함
        // 자바표준 프록시는 인터페이스 기반임

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppCtx1.class);
        Reg bean = ac.getBean(Reg.class);
        long factorial = bean.factorial(10L);
        System.out.println(factorial);

    }
}
