package config;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

// 쓰레드가 만들어져서 메모리를 소비함
// 서버가 캐시를 사용함 메모리를 덜 소비함
// 프록시를 이용해서 동일한 값이 있으면 담고
// 캐시를 사용함

// 프록시를 사용할 곳에 붙임
@Aspect
@Order(1)
public class ProxyCache {
    private Map<Object, Object> chachdata = new HashMap<>();


    // 공통범위
//    @Pointcut("execution(* aop..*(..))")
//    public void Target(){}

    // 공통 관심사이기때문에 반환값을 예측할 수 없다
    // 반환값 -> 오브젝트
    // ProceedingJoinPoint
    // -> 프록시(개발도중 호출되는 대상 객체에 대한 정보,
    // 실행되는 메서드에 대한 정보, 메서드를 호출할 때 전달된 인자에 대한 정보)에 대한 정보가 담겨있다
    // 스프링 빈으로 등록해야 스프링에서 관리가 가능함

    @Around("CommonPointcut.Target()")
    // @Around("Target()")
    public Object proess(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        Object[] args = proceedingJoinPoint.getArgs();
        if(chachdata.containsKey(args[0])){
            System.out.println("캐시사용");
            return chachdata.get(args[0]);
        }

        Object proceed = proceedingJoinPoint.proceed();
        chachdata.put(args[0], proceed);
        System.out.println("캐시저장");
        return proceed;
    }
}
