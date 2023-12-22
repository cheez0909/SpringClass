package org.choongang.commons;

import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException {
    private HttpStatus status;

    public CommonException(String msg){
        this(msg, HttpStatus.INTERNAL_SERVER_ERROR); // 기본 에러
    }
    public CommonException(String msg, HttpStatus status){
        super(msg);
        this.status=status;
    }

    public HttpStatus getStatus(){
        return this.status;
    }
}
