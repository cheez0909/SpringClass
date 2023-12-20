package common;

import org.springframework.http.HttpStatus;

/**
 * 모든 예외를 이 클래스의 하위클래스로 정의할 것
 */
public class CommonException extends RuntimeException{
    private HttpStatus status;

    /**
     * 그냥 500에러
     * */
    public CommonException(String message){
        this(message, HttpStatus.INTERNAL_SERVER_ERROR); // 예외를 생성함
    }

    /**
     * 설정할 수 있음
     */
    public CommonException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return this.status;
    }
}
