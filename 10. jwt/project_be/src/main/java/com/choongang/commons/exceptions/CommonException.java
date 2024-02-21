package com.choongang.commons.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public class CommonException extends RuntimeException {

    @Getter
    @Setter // 상태값을 변경하는 경우가 있을 수 있음
    private HttpStatus status;
    private Map<String, List<String>> messages; // 에러메세지가 나올 경우 가공해서 넣기

    public CommonException(String message){
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public CommonException(String message, HttpStatus status){
        super(message.toString());
        this.status = status;
    }

    /* 커맨드객체에서 에러가 발생할 수 있음 */
    public CommonException(Errors errors, HttpStatus status){
        this.status = status;

        /* 커맨드 객체 검증 실패 -> Map<String, List<String>> -> messages */

    }
}
