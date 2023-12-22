package org.choongang.commons;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CommonException{
    public BadRequestException(String msg){
        super(msg, HttpStatus.BAD_REQUEST);

    }
}
