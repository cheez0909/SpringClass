package models;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;

public class Message implements InitializingBean, DisposableBean {
    public void send(String msg){
        System.out.println("전송메세지 : " + msg);
    }


    // 초기화 단계에서 호출됨
    @Override
    public void afterPropertiesSet() throws Exception{
        System.out.println("초기화");
    }

    // 스프링 컨테이너에 있는 빈 소멸 전
    @Override
    public void destroy() throws DestroyFailedException {
        System.out.println("자원 해제");
    }
}
