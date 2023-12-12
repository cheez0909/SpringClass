package config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;

@Aspect
@Order(2)
public class ProxyCalculator {

//    @Pointcut("execution(long aop..*(long))")
//    //@Pointcut("execution(* aop..*(..))")
//    public void Target(){}

    @Around("config.CommonPointcut.Target()")
    // @Around("execution(* aop..*(..))")
    //@Around("Target()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable{

//        Signature sig = joinPoint.getArgs();
//        String longString = sig.toLongString();
//        System.out.println(longString);
        Object[] args = joinPoint.getArgs();
//        long num = (Long) args[0];
//        System.out.println(num);

        long stime = System.nanoTime();
        try {
            Object proceed = joinPoint.proceed();
            // 핵심기능을 대신 수행하는 메서드
            // factorial(..)
            return proceed;
        } finally {
            long etime = System.nanoTime();
            System.out.println("걸린 시간 : " + (etime-stime));
        }


    }
}
